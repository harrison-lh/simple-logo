package slogo.controller;

import java.util.List;

/**
 * An interface that defines common behavior between Objects that consume input streams.
 *
 * Incorporates several methods that will be helpful in the creation of many Parser components.
 *
 * @author Marc Chmielewski
 */
public interface InputConsumer {

  /**
   * Peeks the Character at the end of the current input. That is, it inspects it without removing
   * it from the input stream.
   *
   * @return The Character at the end of the current input
   */
  public Character peekSingleCharacter();

  /**
   * Peeks the last k Characters from the end of the current input. That is, it inspects them without removing
   * them from the input stream.
   *
   * @param k The number of Characters to inspect
   * @return The last k Characters at the end of the current input
   */
  public List<Character> peekMultipleCharacters(int k);

  /**
   * Consumes the Character at the end of the current input. That is, it inspects and removes
   * it from the input stream.
   *
   * @return The Character at the end of the current input
   */
  public Character consumeSingleCharacter();

  /**
   * Consumes the last k Characters from the end of the current input. That is, it inspects and removes
   * them from the input stream.
   *
   * @param k The number of Characters to inspect and remove.
   * @return The last k Characters at the end of the current input.
   */
  public List<Character> consumeMultipleCharacters(int k);
}
