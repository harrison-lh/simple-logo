package slogo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
  private List<Token> tokenizedText;

  public Parser(TurtleController controller, String syntaxLang) {
    this.controller = controller;
    this.lexer = new Lexer(syntaxLang);
    this.root = null;
    this.splitText = new ArrayList<>();
    this.tokenizedText = new ArrayList<>();
  }

  private void splitText(String text) {
    splitText = new ArrayList<>(Arrays.asList(text.split("\\s+")));
  }

  private void tokenizeText(String text) {
    List<Token> tokenList = new ArrayList<>();
    for(String curString : splitText) {
      tokenList.add(lexer.tokenize(curString));
    }
    tokenizedText = tokenList;
  }

  public void setSyntaxLang(String syntaxLang) {
    lexer.setLangSymbols(syntaxLang);
  }
}
