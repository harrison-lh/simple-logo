package slogo.controller;

/**
 * CharacterReader implements a basic character reader, which implements the InputConsumer
 * interface. The purpose of this class is to take in an input String and then consume it in
 * increments to feed input to the Lexer, which will then create Tokens that can be used to
 * construct an AST.
 *
 * @author Marc Chmielewski
 */
public class CharacterReader implements InputConsumer {

  private String inputString;
  private int curIndex;

  /**
   * Basic constructor for CharacterReader, that takes in a String, inputString and prepares it to
   * be indexed.
   *
   * @param inputString The String containing the command(s) to be run.
   */
  public CharacterReader(String inputString) {
    this.inputString = inputString.strip(); // Remove any leading and trailing whitespace
    this.curIndex = 0;
  }

  /**
   * Inspect the single-character substring of inputString that is at curIndex of the String, without
   * incrementing curIndex.
   *
   * @return The single-character substring of inputString that is at curIndex of the String
   * @throws StringIndexOutOfBoundsException if the String is empty or the query is otherwise
   *                                         invalid
   */
  @Override
  public String peekSingleCharacter() throws StringIndexOutOfBoundsException {
    return inputString.substring(curIndex, curIndex);
  }

  @Override
  public String peekMultipleCharacters(int k) {
    return inputString.substring(curIndex, curIndex + k);
  }

  /**
   * Inspect the single-character substring of inputString that is at curIndex of the String,
   * incrementing curIndex.
   *
   * @return The single-character substring of inputString that is at curIndex of the String
   * @throws StringIndexOutOfBoundsException if the String is empty or the query is otherwise
   *                                         invalid
   */
  @Override
  public String consumeSingleCharacter() {
    String ret = inputString.substring(curIndex, curIndex);
    curIndex++;
    return ret;
  }

  @Override
  public String consumeMultipleCharacters(int k) {
    String ret = inputString.substring(curIndex, curIndex + k);
    curIndex += k;
    return ret;
  }
}
