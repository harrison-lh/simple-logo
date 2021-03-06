package slogo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import slogo.controller.CharacterReader;

public class CharacterReaderTest {

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
    for (int i = 0; i < HELLO_SLOGO.length(); i++) {
      // Multiple calls to peek should return the same character
      assertEquals(reader.consumeSingleCharacter(), HELLO_SLOGO.substring(i, i + 1));
    }
  }

  @Test
  public void testEmptyString() {

  }
}
