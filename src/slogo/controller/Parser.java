package slogo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Parser is the meat-and-potatoes of the SLogo Control layer. This class takes in a String of SLogo
 * code, breaks it up by whitespace, and then utilizes several other classes to build an AST, and
 * then pass it to a TurtleController for consumption.
 *
 * @author Marc Chmielewski
 */
public class Parser {

  private TurtleController controller;
  private Lexer lexer;
  private Queue<String> splitText;
  private Queue<Token> tokenizedText;
  private List<VariableNode> variables;
  private Queue<Node> parsedNodeQueue;
  private Queue<Command> assembledCommandQueue;

  public Parser(TurtleController controller, String syntaxLang) {
    this.controller = controller;
    this.lexer = new Lexer(syntaxLang);
    this.splitText = new LinkedList<>();
    this.tokenizedText = new LinkedList<>();
    this.variables = new ArrayList<>();
    this.parsedNodeQueue = new LinkedList<>();
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

  private void mapTokensToNodes() {
    while (!tokenizedText.isEmpty()) {
      parsedNodeQueue.add(patternMatchToken(tokenizedText.poll(), splitText.poll()));
    }
  }

  private Node patternMatchToken(Token token, String text) {
    switch (token) {
      case COMMAND -> {
        String commandType = lexer.lexLangDefinedCommands(text);
        try {
          Class<?> commandClass = Class.forName(commandType + "Command");
          return (Node) commandClass.getConstructor().newInstance();
        } catch (Exception e) {
          // TODO: Might be a user-defined command, so we must check those!
        }
      }
      case CONSTANT -> {
        return new ConstantNode(Double.parseDouble(text));
      }
      case VARIABLE -> {
        for (VariableNode curVarNode : variables) {
          if (curVarNode.getName().equals(text)) {
            return curVarNode;
          }
        }
        VariableNode newNode = new VariableNode(text);
        variables.add(newNode);
        return newNode;
      }
      case LIST_START -> {
        // TODO: Create ListStartNode
      }
      case LIST_END -> {
        // TODO: Create ListEndNode
      }
    }
    return null;
  }

  private boolean handleCommentsAndBlankLines() {
    return (tokenizedText.contains(Token.COMMENT) || tokenizedText.isEmpty());
  }

  private void assembleCommandQueue() {

  }

  public void setSyntaxLang(String syntaxLang) {
    lexer.setLangSymbols(syntaxLang);
  }

  public void createParseTree(String text) {
    splitText(text);
    tokenizeText();
    if (handleCommentsAndBlankLines()) {
      return; // If it's a comment line, return early. Comments have no commands.
    }
    mapTokensToNodes();
    assembleCommandQueue();
    controller.pushCommands(assembledCommandQueue);
  }
}
