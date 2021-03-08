package slogo.controller;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

/**
 * Lexer implements the tokenization behavior of the Parser. That is, the Lexer is responsible for
 * breaking up the input stream in Tokens, currently just separated by whitespace, sanitizing for
 * comments and whitespace, validating for legitimacy and then returning that output to the Parser
 * for interpretation.
 *
 * @author Marc Chmielewski
 */
public class Lexer {

  private static final String RESOURCES_PACKAGE = "resources.languages.";
  private List<Entry<String, Pattern>> symbols;

  /**
   * Default constructor for Lexer. Takes no language; has no symbols
   */
  public Lexer() {
    symbols = new ArrayList<>();
  }

  public Lexer(String syntaxLanguage) {
    symbols = instantiateSymbols(syntaxLanguage);
  }

  private List<Entry<String, Pattern>> instantiateSymbols(String syntaxLanguage) {
    ResourceBundle resources = ResourceBundle.getBundle(RESOURCES_PACKAGE + syntaxLanguage);
    List<Entry<String, Pattern>> langSymbols = new ArrayList<>();
    for (String key : Collections.list(resources.getKeys())) {
      String regex = resources.getString(key);
      langSymbols.add(new SimpleEntry<>(key, Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
    }
    return langSymbols;
  }

  /**
   * Get the List of symbols for the currently instantiated language.
   *
   * @return A List<Entry<String, Pattern>> that represents the symbols for the currently
   * instantiated language.
   */
  public List<Entry<String, Pattern>> getSymbols() {
    return symbols;
  }

  /**
   * Set the symbols for the Lexer to the syntaxLanguage.
   *
   * @param syntaxLanguage The language to which the symbols are to be set.
   */
  public void setSymbols(String syntaxLanguage) {
    instantiateSymbols(syntaxLanguage);
  }
}
