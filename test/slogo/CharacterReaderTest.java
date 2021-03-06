package slogo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import slogo.controller.CharacterReader;

public class CharacterReaderTest {

  @Test
  public void testPeekSimpleString() {
    String str = "Hello, SLogo!";
    CharacterReader reader = new CharacterReader(str);
    for (int i = 0; i < str.length(); i++) {
      // Multiple calls to peek should return the same character
      assertEquals(reader.peekSingleCharacter(), "H");
    }
  }

  @Test
  public void testEmptyString() {

  }
}
