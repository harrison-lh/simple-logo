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
  private Node root;
  private Queue<Node> parsedNodeQueue;
  private Queue<String> splitText;
  private Queue<Token> tokenizedText;

  public Parser(TurtleController controller, String syntaxLang) {
    this.controller = controller;
    this.lexer = new Lexer(syntaxLang);
    this.root = null;
    this.splitText = new LinkedList<>();
    this.tokenizedText = new LinkedList<>();
  }

  private void splitText(String text) {
    splitText = new LinkedList<>(Arrays.asList(text.split("\\s+")));
  }

  private void tokenizeText() {
    Queue<Token> tokenList = new LinkedList<>();
    for(String curString : splitText) {
      tokenList.add(lexer.tokenize(curString));
    }
    this.tokenizedText = tokenList;
  }

  private void mapTokensToNodes() {
    while(!tokenizedText.isEmpty()) {
      parsedNodeQueue.add(patternMatchToken(tokenizedText.poll(), splitText.poll()));
    }
  }

  private Node patternMatchToken(Token token, String text) {
    switch(token) {
      case COMMAND -> {
        // TODO: Create __Command
      }
      case CONSTANT -> {
        // TODO: Create __Constant
      }
      case VARIABLE -> {
        // TODO: Create __Variable
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

  public void setSyntaxLang(String syntaxLang) {
    lexer.setLangSymbols(syntaxLang);
  }

  public void createParseTree(String text) {
    splitText(text);
    tokenizeText();
    if(handleCommentsAndBlankLines()) {
      return; // If it's a comment line, return early. Comments have no commands.
    }
    mapTokensToNodes();
  }

  private boolean handleCommentsAndBlankLines() {
    return (tokenizedText.contains(Token.COMMENT) || tokenizedText.isEmpty());
  }
}
