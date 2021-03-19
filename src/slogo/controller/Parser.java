package slogo.controller;

import java.beans.PropertyChangeListener;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Stack;
import java.util.function.Consumer;
import slogo.controller.commands.MakeUserInstructionCommand;
import slogo.controller.commands.UserCommand;
import slogo.view.SelectorTarget;

/**
 * Parser is the meat-and-potatoes of the SLogo Control layer. This class takes in a String of SLogo
 * code, breaks it up by whitespace, and then utilizes several other classes to build an AST, and
 * then pass it to a TurtleController for consumption.
 *
 * @author Marc Chmielewski
 */
public class Parser implements SelectorTarget<String> {

  private final TurtleGeneral turtleGeneral;
  private final Lexer lexer;
  private final Queue<Command> parsedCommandQueue;
  private final Queue<Command> assembledCommandQueue;
  private Queue<String> splitText;
  private Queue<Token> tokenizedText;

  /**
   * Calls main constructor, passing in an empty command listener
   */
  public Parser(TurtleGeneral turtleGeneral, String syntaxLang) {
    this(turtleGeneral, syntaxLang, evt -> {
    });
  }

  /**
   * Constructor for the Parser. Takes in a TurtleController to execute Commands on, and an initial
   * syntaxLang to be constructed with.
   *
   * @param turtleGeneral       The TurtleController upon which this Parser acts
   * @param syntaxLang       The initial language for which this Parser is configured.
   * @param commandsListener
   */
  public Parser(TurtleGeneral turtleGeneral, String syntaxLang,
      PropertyChangeListener commandsListener) {
    this.turtleGeneral = turtleGeneral;
    this.lexer = new Lexer(syntaxLang, commandsListener);
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
        return patternMatchCommand(text);
      }
      case CONSTANT -> {
        return new ConstantCommand(Double.parseDouble(text));
      }
      case VARIABLE -> {
        return patternMatchVariable(text);
      }
      case LIST_START -> {
        return patternMatchListStart();
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

  private ListCommandHead patternMatchListStart() {
    ListCommandHead listStartCommand = new ListCommandHead();
    listStartCommand.setNumParams(0);
    if (tokenizedText.isEmpty() || splitText.isEmpty()) {
      throw new IllegalArgumentException(
          "ILLEGAL ARGUMENT EXCEPTION: OPEN LIST WITHOUT CLOSURE!");
    }
    Command innerChild = patternMatchToken(tokenizedText.poll(), splitText.poll());
    fillList(listStartCommand, innerChild);
    return listStartCommand;
  }

  private VariableCommand patternMatchVariable(String text) {
    for(TurtleController curController : turtleGeneral.getTurtleArmy()) {
      if (!curController.getTurtle().getVars().containsKey(text)) {
        curController.getTurtle().getVars().setValue(text, 0);
      }
      return new VariableCommand(text);
    }
    return null;
  }

  private Command patternMatchCommand(String text) {
    String commandType = lexer.lexLangDefinedCommands(text);
    System.out.println(commandType);
    if (commandType.equals("MakeUserInstruction") && tokenizedText.peek() == Token.COMMAND) {
      tokenizedText.poll();
      return new MakeUserInstructionCommand(splitText.poll(), lexer);
    } else {
      try {
        if (lexer.containsUserCommand(text)) {
          return new UserCommand(lexer.getUserCommand(text));
        }
        Class<?> commandClass = Class
            .forName("slogo.controller.commands." + commandType + "Command");
        return (Command) commandClass.getConstructor().newInstance();
      } catch (Exception e) {

        System.err.println("LOOKUP FAILED!!!");
        // TODO: Might be a user-defined command, so we must check those!
        throw new IllegalArgumentException("ILLEGAL ARGUMENT EXCEPTION: COMMAND UNDEFINED!");
      }
    }
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
      return;
    }

    Command nextChild = patternMatchToken(tokenizedText.poll(), splitText.poll());

    fillList(listHead, nextChild);

    //should never reach here
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
      commandQueueAssemblyHelper(pendingFilledArgs, curCommand);

      while (!pendingFilledArgs.isEmpty()) {
        curCommand = pendingFilledArgs.pop();
        commandQueueAssemblyHelper(pendingFilledArgs, curCommand);
      }
      assembledCommandQueue.add(rootCommand);
    }
  }

  private void commandQueueAssemblyHelper(Stack<Command> pendingFilledArgs, Command curCommand)
      throws NullPointerException {
    while (curCommand.getNumParams() > curCommand.getChildren().size()) {
      Command childCommand = parsedCommandQueue.poll();
      curCommand.addChild(childCommand);
      if (childCommand != null) {
        if (childCommand.getNumParams() > 0 && curCommand.getNumParams() > curCommand.getChildren()
            .size()) {
          pendingFilledArgs.push(curCommand);
          curCommand = childCommand;
        } else if (childCommand.getNumParams() > 0) {
          curCommand = childCommand;
        }
      } else {
        throw new NullPointerException("NULL POINTER EXCEPTION: CHECK YOUR ARGUMENT COUNT!!!");
      }
    }
  }


  /**
   * Change the syntaxLang of the Parser by modifying the syntaxLang of the attached Lexer.
   *
   * @param syntaxLang The new language for the Parser
   */
  public void setSyntaxLang(String syntaxLang) {
    lexer.setLangSymbols(syntaxLang);
  }

  /**
   * The primary parsing method for this Parser, parseCommandString is the core engine behind SLogo.
   * This method unifies the Lexer and the parser into one unit and creates an AST which is then
   * traversed to create a Queue of Commands for the Turtle to execute.
   *
   * @param text The command String to parse
   * @throws IllegalArgumentException If the provided command String is not syntactically correct.
   *                                  The error message elucidates more detail about this.
   * @throws NullPointerException     If the any of the commands within the provided command String
   *                                  has the wrong number of args!
   */
  public void parseCommandString(String text)
      throws IllegalArgumentException, NullPointerException {
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
    List<Integer> curActiveTurtleIds = turtleGeneral.getActiveTurtleIds();
    for(TurtleController controller : turtleGeneral.getTurtleArmy()) {
      if(curActiveTurtleIds.contains(controller.getTurtle().getId()))
        controller.pushCommands(assembledCommandQueue);
    }
    assembledCommandQueue.clear();
    // Clean up after we're done
  }

  /**
   * A simple hook for the Consumer Interface that allows the Parser to be fed a new command String
   * from the front-end.
   *
   * @return A Consumer of Strings that calls the requisite methods to have the parser parse the
   * command String and move the Turtle.
   * @throws IllegalArgumentException If the provided command String is not syntactically correct.
   *                                  The error message elucidates more detail about this.
   * @throws NullPointerException     If the any of the commands within the provided command String
   *                                  has * the wrong number of args!
   */
  public Consumer<String> receiveInputAction()
      throws IllegalArgumentException, NullPointerException {
    return command -> {
      parseCommandString(command);
      List<Integer> curActiveTurtleIds = turtleGeneral.getActiveTurtleIds();
      for(TurtleController controller : turtleGeneral.getTurtleArmy()) {
        if(curActiveTurtleIds.contains(controller.getTurtle().getId())) {
          controller.setIsAllowedToExecute(true);
          controller.runCommands();
        }
      }
    };
  }

  /**
   * A simple hook for the Consumer Interface that allows the language of this Parser to be updated
   * by the front-end.
   *
   * @return A Consumer of strings that contains the method to call to update the syntax language of
   * the Parser
   */
  @Override
  public Consumer<String> updateAction() {
    return this::setSyntaxLang;
  }
}
