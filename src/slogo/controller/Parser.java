package slogo.controller;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Stack;
import java.util.function.Consumer;
import slogo.view.SelectorTarget;

/**
 * Parser is the meat-and-potatoes of the SLogo Control layer. This class takes in a String of SLogo
 * code, breaks it up by whitespace, and then utilizes several other classes to build an AST, and
 * then pass it to a TurtleController for consumption.
 *
 * @author Marc Chmielewski
 */
public class Parser implements SelectorTarget<String> {

  private final TurtleController controller;
  private final Lexer lexer;
  private final Queue<Command> parsedCommandQueue;
  private final Queue<Command> assembledCommandQueue;
  private Queue<String> splitText;
  private Queue<Token> tokenizedText;


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
      tokenList.add(lexer.tokenize(curString));
    }
    this.tokenizedText = tokenList;
  }

  private void mapTokensToCommands() {
    while (!tokenizedText.isEmpty()) {
      parsedCommandQueue.add(patternMatchToken(tokenizedText.poll(), splitText.poll()));
    }
  }

  private Command patternMatchToken(Token token, String text) throws IllegalArgumentException {
    switch (token) {
      case COMMAND -> {
        String commandType = lexer.lexLangDefinedCommands(text);
        System.out.println(commandType);
        try {
          Class<?> commandClass = Class
              .forName("slogo.controller.commands." + commandType + "Command");
          return (Command) commandClass.getConstructor().newInstance();
        } catch (Exception e) {
          System.err.println("LOOKUP FAILED!!!");
          // TODO: Might be a user-defined command, so we must check those!
          throw new IllegalArgumentException("ILLEGAL ARGUMENT EXCEPTION: COMMAND UNDEFINED!");
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
        ListCommandHead listStartCommand = new ListCommandHead();
        listStartCommand.setNumParams(0);
        if (tokenizedText.isEmpty() || splitText.isEmpty()) {
          throw new IllegalArgumentException(
              "ILLEGAL ARGUMENT EXCEPTION: OPEN LIST WITH NO ARGUMENTS");
        }
        Command innerChild = patternMatchToken(tokenizedText.poll(), splitText.poll());
        fillList(listStartCommand, innerChild);
        return listStartCommand;
      }
      case LIST_END -> {
        // this case is never called in new implementation
        //System.out.println("IN LIST END: SHOULD NEVER APPEAR");

        return new ListCommandTail();
      }
    }
    throw new IllegalArgumentException(
        "ILLEGAL ARGUMENT EXCEPTION: UNABLE TO TOKENIZE ARGUMENT! PLEASE VERIFY SYNTAX!");
  }

  private void fillList(ListCommandHead listHead, Command innerCommand)
      throws IllegalArgumentException {
    //Start Base Case
    if (innerCommand.getIsListEnd()) {
      return;
    }

    //End Base Case

    listHead.addInnerChild(innerCommand);

    grandChildHandler(innerCommand);

    if (tokenizedText.isEmpty()) {
      throw new IllegalArgumentException(
          "ILLEGAL ARGUMENT EXCEPTION: THE COMMAND LIST IS EMPTY! CHECK FOR MISMATCHED []!");
    }

    Command nextChild = patternMatchToken(tokenizedText.poll(), splitText.poll());

    fillList(listHead, nextChild);

    //should never reach here
    throw new IllegalArgumentException(
        "ILLEGAL ARGUMENT EXCEPTION: THE COMMAND LIST IS EMPTY! CHECK FOR MISMATCHED [] AND ILLEGAL SYNTAX!");
  }

  private void grandChildHandler(Command innerCommand) {
    int numInnerGrandChildren = innerCommand.getNumParams();

    for (int i = 0; i < numInnerGrandChildren; i++) {

      Command grandChild = patternMatchToken(Objects.requireNonNull(tokenizedText.poll()),
          splitText.poll());
      innerCommand.addChild(grandChild);
      if (grandChild.getNumParams() > 0) {
        grandChildHandler(grandChild);
      }
    }
  }

  private boolean handleCommentsAndBlankLines() {
    return (tokenizedText.contains(Token.COMMENT) || tokenizedText.isEmpty());
  }

  private void assembleCommandQueue() {
    while (!parsedCommandQueue.isEmpty()) {
      Stack<Command> pendingFilledArgs = new Stack<>();
      Command rootCommand = parsedCommandQueue.poll();
      Command curCommand = rootCommand;
      dfsHelper(pendingFilledArgs, curCommand);

      while (!pendingFilledArgs.isEmpty()) {
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
      assert childCommand != null;
      if (childCommand.getNumParams() > 0 && curCommand.getNumParams() > curCommand.getChildren()
          .size()) {
        pendingFilledArgs.push(curCommand);
        curCommand = childCommand;
      } else if (childCommand.getNumParams() > 0) {
        curCommand = childCommand;
      }
    }
  }


  public void setSyntaxLang(String syntaxLang) {
    lexer.setLangSymbols(syntaxLang);
  }

  public void parseCommandString(String text) throws IllegalArgumentException {
    splitText(text);
    tokenizeText();
    if (handleCommentsAndBlankLines()) {
      return; // If it's a comment line, return early. Comments have no commands.
    }
    mapTokensToCommands();
    assembleCommandQueue();
    for (Command command : assembledCommandQueue) {
      System.out.println(command);
    }
    controller.pushCommands(assembledCommandQueue);
    assembledCommandQueue.clear();
    // Clean up after we're done
  }

  public Consumer<String> receiveInputAction() throws IllegalArgumentException {
    return command -> {
      parseCommandString(command);
      controller.setIsAllowedToExecute(true);
      controller.runCommands();
    };
  }

  @Override
  public Consumer<String> updateAction() {
    return this::setSyntaxLang;
  }
}
