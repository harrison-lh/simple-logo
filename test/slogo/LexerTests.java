package slogo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import slogo.controller.Lexer;

public class LexerTests {

  @Test
  public void testEnglishSymbolsLoad() {
    Lexer lexer = new Lexer("English");
    // Make sure we've loaded all of the expected symbols
    assertEquals(46, lexer.getSymbols().size());
  }

  @Test
  public void testAltSymbolsReload() {
    Lexer lexer = new Lexer("English");
    // Make sure we've loaded all of the expected symbols
    assertEquals(46, lexer.getSymbols().size());
    lexer.setSymbols("Spanish");
    assertEquals(46, lexer.getSymbols().size());
  }
}
