package slogo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import slogo.controller.Lexer;

public class LexerTests {

  @Test
  public void testEmptyLexer() {
    Lexer lexer = new Lexer();
    assertEquals(6, lexer.getSyntaxSymbols().size());
    assertEquals(0, lexer.getLangSymbols().size());
  }

  @Test
  public void testEnglishSymbolsLoad() {
    Lexer lexer = new Lexer("English");
    // Make sure we've loaded all of the expected symbols
    assertEquals(6, lexer.getSyntaxSymbols().size());
    assertEquals(46, lexer.getLangSymbols().size());
  }

  @Test
  public void testAltSymbolsReload() {
    Lexer lexer = new Lexer("English");
    // Make sure we've loaded all of the expected symbols
    assertEquals(6, lexer.getSyntaxSymbols().size());
    assertEquals(46, lexer.getLangSymbols().size());
    lexer.setLangSymbols("Spanish");
    assertEquals(6, lexer.getSyntaxSymbols().size());
    assertEquals(46, lexer.getLangSymbols().size());
  }
}
