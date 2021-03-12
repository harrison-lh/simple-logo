package slogo.controller;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.function.Consumer;

/**
 * Parser is the meat-and-potatoes of the SLogo Control layer. This class takes in a String of SLogo
 * code, breaks it up by whitespace, and then utilizes several other classes to build an AST, and
 * then pass it to a TurtleController for consumption.
 *
 * @author Marc Chmielewski
 */
public class Parser {

  private final TurtleController controller;
  private final Lexer lexer;
  private Queue<String> splitText;
  private Queue<Token> tokenizedText;
  private final Queue<Command> parsedCommandQueue;
  private final Queue<Command> assembledCommandQueue;

  public Parser(TurtleController controller, String syntaxLang) {
    this.controller = controller;
    this.lexer = new Lexer(syntaxLang);
    this.splitText = new LinkedList<>();
    this.tokenizedText = new LinkedList<>();
    this.parsedCommandQueue = new LinkedList<>();
    this.assembledCommandQueue = new LinkedList<>();
  }

  private void splitText(String text) {
    splitText = new LinkedList<>(Arrays.asList(text.split("\\s+")));
  }

  private void tokenizeText() throws IllegalArgumentException {
    Queue<Token> tokenList = new LinkedList<>();
    for (String curString : splitText) {
      try {
        tokenList.add(lexer.tokenize(curString));
      } catch (IllegalArgumentException e) {
        throw e;
      }
    }
    this.tokenizedText = tokenList;
  }

  private void mapTokensToCommands() {
    while (!tokenizedText.isEmpty()) {
      parsedCommandQueue.add(patternMatchToken(tokenizedText.poll(), splitText.poll()));
    }
  }

  private Command patternMatchToken(Token token, String text) {
    switch (token) {
      case COMMAND -> {
        String commandType = lexer.lexLangDefinedCommands(text);
        System.out.println(commandType);
        try {
          Class<?> commandClass = Class.forName("slogo.controller.commands."+commandType + "Command");
          return (Command) commandClass.getConstructor().newInstance();
        } catch (Exception e) {
          System.err.println("LOOKUP NO WORK!!!");
          // TODO: Might be a user-defined command, so we must check those!
        }
      }
      case CONSTANT -> {
        return new ConstantCommand(Double.parseDouble(text));
      }
      case VARIABLE -> {
        if (!controller.getTurtle().getVars().containsKey(text)) {
          controller.getTurtle().getVars().setValue(text, 0);
        }
        return new VariableCommand(text);
      }
      case LIST_START -> {
        // TODO: Create ListStartCommand
      }
      case LIST_END -> {
        // TODO: Create ListEndCommand
      }
    }
    return null;
  }

  private boolean handleCommentsAndBlankLines() {
    return (tokenizedText.contains(Token.COMMENT) || tokenizedText.isEmpty());
  }

  private void assembleCommandQueue() {
    while(!parsedCommandQueue.isEmpty()) {
      Stack<Command> pendingFilledArgs = new Stack<>();
      Command rootCommand = parsedCommandQueue.poll();
      Command curCommand = rootCommand;
      dfsHelper(pendingFilledArgs, curCommand);
      while(!pendingFilledArgs.isEmpty()) {
        curCommand = pendingFilledArgs.pop();
        dfsHelper(pendingFilledArgs, curCommand);
      }
      assembledCommandQueue.add(rootCommand);
    }
  }

  private void dfsHelper(Stack<Command> pendingFilledArgs, Command curCommand) {
    while (curCommand.getNumParams() > curCommand.getChildren().size()) {
      Command childCommand = parsedCommandQueue.poll();
      curCommand.addChild(childCommand);
      if (childCommand.getNumParams() > 0 && curCommand.getNumParams() > curCommand.getChildren().size()) {
        pendingFilledArgs.push(curCommand);
        curCommand = childCommand;
      }
      else if (childCommand.getNumParams() > 0) {
        curCommand = childCommand;
      }
    }
  }

  public void setSyntaxLang(String syntaxLang) {
    lexer.setLangSymbols(syntaxLang);
  }

  public void parseCommandString(String text) {
    splitText(text);
    tokenizeText();
    if (handleCommentsAndBlankLines()) {
      return; // If it's a comment line, return early. Comments have no commands.
    }
    mapTokensToCommands();
    assembleCommandQueue();
    for(Command command : assembledCommandQueue) {
      System.out.println(command);
    }
    controller.pushCommands(assembledCommandQueue);
    assembledCommandQueue.clear();
    // Clean up after we're done
  }

  public Consumer<String> receiveInputAction() {
    return command -> {
      parseCommandString(command);
      controller.setIsAllowedToExecute(true);
      controller.runCommands();
    };
  }
}
