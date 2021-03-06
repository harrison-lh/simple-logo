package slogo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import slogo.controller.CharacterReader;

public class CharacterReaderTests {

  private static final String EMPTY = "";
  private static final String HELLO_SLOGO = "Hello, SLogo!!";
  private static final String HELLO_SLOGO_ODD = "Hello, SLogo!";

  @Test
  public void testPeekSingleCharacterSimpleString() {
    CharacterReader reader = new CharacterReader(HELLO_SLOGO);
    for (int i = 0; i < HELLO_SLOGO.length(); i++) {
      // Multiple calls to peek should return the same character
      assertEquals(reader.peekSingleCharacter(), HELLO_SLOGO.substring(0, 1));
    }
  }

  @Test
  public void testConsumeSingleCharacterSimpleString() {
    CharacterReader reader = new CharacterReader(HELLO_SLOGO);
    int curIndex = 0;
    while (!reader.isEOF()) {
      // Multiple calls to consumeSingleCharacter should consume the String one character at a time
      assertEquals(reader.consumeSingleCharacter(), HELLO_SLOGO.substring(curIndex, ++curIndex));
    }
  }

  @Test
  public void testPeekMultipleCharactersSimpleString() {
    CharacterReader reader = new CharacterReader(HELLO_SLOGO);
    int numCharsToPeek = 2;
    for (int i = 0; i < HELLO_SLOGO.length(); i++) {
      // Multiple calls to peek should return the same characters
      assertEquals(reader.peekMultipleCharacters(numCharsToPeek),
          HELLO_SLOGO.substring(0, numCharsToPeek));
    }
  }

  @Test
  public void testConsumeMultipleCharactersSimpleString() {
    CharacterReader reader = new CharacterReader(HELLO_SLOGO);
    int curIndex = 0;
    int numCharsToConsume = 2;
    while (!reader.isEOF()) {
      // Multiple calls to consumeMultipleCharacters should consume the String k characters at a time
      assertEquals(reader.consumeMultipleCharacters(numCharsToConsume),
          HELLO_SLOGO.substring(curIndex, curIndex + numCharsToConsume));
      curIndex += numCharsToConsume;
    }
  }

  @Test
  public void testEmptyString() {
    CharacterReader reader = new CharacterReader(EMPTY);
    // Calling any of these methods on "" should throw StringIndexOutOfBoundsExceptions
    assertThrows(StringIndexOutOfBoundsException.class, reader::peekSingleCharacter);
    assertThrows(StringIndexOutOfBoundsException.class, reader::consumeSingleCharacter);
    assertThrows(StringIndexOutOfBoundsException.class, () -> {
      reader.peekMultipleCharacters(2);
    });
    assertThrows(StringIndexOutOfBoundsException.class, () -> {
      reader.consumeMultipleCharacters(2);
    });
  }

  @Test
  public void testStringOverrun() {
    CharacterReader reader = new CharacterReader(HELLO_SLOGO_ODD);
    int curIndex = 0;
    int numCharsToConsume = 2;
    try {
      while (!reader.isEOF()) {
        // Multiple calls to consumeMultipleCharacters should consume the String k characters at a time
        String substr = HELLO_SLOGO_ODD.substring(curIndex, curIndex + numCharsToConsume);
        assertEquals(reader.consumeMultipleCharacters(numCharsToConsume), substr);
        curIndex += numCharsToConsume;
      }
    }
    catch (StringIndexOutOfBoundsException e) {
      // Catch the StringIndexOutOfBoundsException, and finish consuming the String in single char bits
      while(!reader.isEOF()) {
        // Multiple calls to consumeSingleCharacter should consume the String one character at a time
        System.out.println("Ranging error caught!!!");
        assertEquals(reader.consumeSingleCharacter(), HELLO_SLOGO_ODD.substring(curIndex, ++curIndex));
      }
    }
  }
}
