package slogo.view;

import java.util.function.Consumer;

/**
 * Consumer of language updates
 */
public interface LanguageConsumer {

  /**
   * @return Consumer that takes in the new language and updates current state accordingly
   */
  Consumer<String> languageConsumer();

}
