package slogo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import slogo.controller.Lexer;
import slogo.controller.Token;

/**
 * A suite of tests to put the Lexer through its paces.
 *
 * @author Marc Chmielewski
 */
public class LexerTests {

  @Test
  public void testEmptyLexer() {
    Lexer lexer = new Lexer();
    assertEquals(8, lexer.getSyntaxSymbols().size());
    assertEquals(0, lexer.getLangSymbols().size());
  }

  @Test
  public void testEnglishSymbolsLoad() {
    Lexer lexer = new Lexer("English");
    // Make sure we've loaded all of the expected symbols
    assertEquals(8, lexer.getSyntaxSymbols().size());
    assertEquals(60, lexer.getLangSymbols().size());
  }

  @Test
  public void testAltSymbolsReload() {
    Lexer lexer = new Lexer("English");
    // Make sure we've loaded all of the expected symbols
    assertEquals(8, lexer.getSyntaxSymbols().size());
    assertEquals(60, lexer.getLangSymbols().size());
    lexer.setLangSymbols("Spanish");
    assertEquals(8, lexer.getSyntaxSymbols().size());
    assertEquals(60, lexer.getLangSymbols().size());
  }

  @Test
  public void testSimpleLexerTokenization() {
    Lexer lexer = new Lexer();
    assertEquals(Token.COMMENT, lexer.tokenize("#"));
    assertEquals(Token.COMMAND, lexer.tokenize("fd"));
    assertEquals(Token.CONSTANT, lexer.tokenize("50"));
    assertEquals(Token.VARIABLE, lexer.tokenize(":RipEmUpTearEmUpGiveEmHellDuke"));
    assertEquals(Token.LIST_START, lexer.tokenize("["));
    assertEquals(Token.LIST_END, lexer.tokenize("]"));
    assertThrows(IllegalArgumentException.class, () -> {
          lexer.tokenize("fd 50");
        }
    );
  }

  @Test
  public void testSimpleLexLangDefinedCommands() {
    Lexer lexer = new Lexer("English");
    assertEquals("Forward", lexer.lexLangDefinedCommands("fd"));
    assertEquals("Sum", lexer.lexLangDefinedCommands("+"));
    assertEquals("DoTimes", lexer.lexLangDefinedCommands("dotimes"));
    assertThrows(IllegalArgumentException.class, () -> {
          lexer.lexLangDefinedCommands("fd 50");
        }
    );
    assertThrows(IllegalArgumentException.class, () -> {
          lexer.lexLangDefinedCommands("# This is a comment");
        }
    );
  }

  @Test
  public void testComplexLexLangDefinedCommands() {
    Lexer lexer = new Lexer("English");
    assertEquals("Forward", lexer.lexLangDefinedCommands("fd"));
    assertEquals("Sum", lexer.lexLangDefinedCommands("+"));
    assertEquals("DoTimes", lexer.lexLangDefinedCommands("dotimes"));
    assertThrows(IllegalArgumentException.class, () -> {
          lexer.lexLangDefinedCommands("fd 50");
        }
    );
    assertThrows(IllegalArgumentException.class, () -> {
          lexer.lexLangDefinedCommands("# This is a comment");
        }
    );
    lexer.setLangSymbols("Spanish");
    assertEquals("Forward", lexer.lexLangDefinedCommands("ava"));
    assertEquals("Sum", lexer.lexLangDefinedCommands("+"));
    assertEquals("DoTimes", lexer.lexLangDefinedCommands("haz"));
    assertThrows(IllegalArgumentException.class, () -> {
          lexer.lexLangDefinedCommands("fd 50");
        }
    );
    assertThrows(IllegalArgumentException.class, () -> {
          lexer.lexLangDefinedCommands("# This is a comment");
        }
    );
  }
}
