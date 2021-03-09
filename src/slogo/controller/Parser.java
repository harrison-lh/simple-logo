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
  private List<String> splitText;
  private Queue<Token> tokenizedText;

  public Parser(TurtleController controller, String syntaxLang) {
    this.controller = controller;
    this.lexer = new Lexer(syntaxLang);
    this.root = null;
    this.splitText = new ArrayList<>();
    this.tokenizedText = new LinkedList<>();
  }

  private void splitText(String text) {
    splitText = new ArrayList<>(Arrays.asList(text.split("\\s+")));
  }

  private void tokenizeText() {
    Queue<Token> tokenList = new LinkedList<>();
    for(String curString : splitText) {
      tokenList.add(lexer.tokenize(curString));
    }
    this.tokenizedText = tokenList;
  }

  private void mapTokensToNodes() {
    for(Token curToken : tokenizedText) {
      switch(curToken) {
        // TODO: Implement this stuff
      }
    }
  }

  public void setSyntaxLang(String syntaxLang) {
    lexer.setLangSymbols(syntaxLang);
  }

  public void createParseTree(String text) {
    splitText(text);
    tokenizeText();
    mapTokensToNodes();
  }
}
