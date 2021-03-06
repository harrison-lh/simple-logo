package slogo.controller;

/**
 * An interface that defines common behavior between Objects that consume input streams.
 * <p>
 * Incorporates several methods that will be helpful in the creation of many Parser components.
 *
 * @author Marc Chmielewski
 */
public interface InputConsumer {

  /**
   * Peeks the one-character String at the current index of the input. That is, it inspects it
   * without removing it from the input stream.
   * <p>
   * If ranging would overrun the length of the String, this will throw a
   * StringIndexOutOfBoundsException.
   *
   * @return The one-character String at the current index of the current input
   */
  String peekSingleCharacter();

  /**
   * Peeks the first k-character String starting from the current index of the current input. That
   * is, it inspects them without removing them from the input stream.
   * <p>
   * If ranging would overrun the length of the String, this will throw a
   * StringIndexOutOfBoundsException
   *
   * @param k The number of Characters to inspect
   * @return The k-character String starting at the current index of the current input
   */
  String peekMultipleCharacters(int k);

  /**
   * Consumes the one-character String at the current index of the input. That is, it inspects and
   * removes it from the input stream.
   * <p>
   * If ranging would overrun the length of the String, this will throw a
   * StringIndexOutOfBoundsException.
   * <p>
   * Behind the scenes, this means that it will increment the current index of the String by one.
   *
   * @return The one-character String at the current index of the current input
   */
  String consumeSingleCharacter();


  /**
   * Consumes the first k-character String starting from the current index of the current input.
   * That is, it inspects them and removes them from the input stream.
   * <p>
   * Behind the scenes, this means that it will increment the current index of the String by k.
   * <p>
   * If ranging would overrun the length of the String, this will throw a
   * StringIndexOutOfBoundsException.
   *
   * @param k The number of Characters to inspect
   * @return The k-character String starting at the current index of the current input
   */
  String consumeMultipleCharacters(int k);

  /**
   * Returns if the entire input has been consumed. (We have reached the EOF)
   *
   * @return Has the EOF been reached?
   */
  boolean isEOF();
}
