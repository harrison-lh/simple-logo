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
  private static final String SYNTAX = "Syntax";
  private List<Entry<String, Pattern>> symbols;

  /**
   * Default constructor for Lexer. Takes no language; has no symbols
   */
  public Lexer() {
    symbols = new ArrayList<>();
  }

  /**
   * A more useful constructor for Lexer. Takes in a language for which to initialize the symbols.
   *
   * @param syntaxLanguage The language with which to initialize the symbols.
   */
  public Lexer(String syntaxLanguage) {
    symbols = instantiateSymbols(syntaxLanguage);
  }

  /**
   * Instantiates the lexer with a set of symbols for the language, syntaxLanguage, as well as the
   * general syntax.
   * <p>
   * NOTE: This is designed such that only one language can have symbols loaded at a time to avoid
   * naming conflicts.
   *
   * @param syntaxLanguage The language to load symbols for
   * @return A List<Entry<String, Pattern>> that represents the symbols as standard regexes
   */
  private List<Entry<String, Pattern>> instantiateSymbols(String syntaxLanguage) {
    List<Entry<String, Pattern>> langSymbols = new ArrayList<>();
    addSymbols(syntaxLanguage, langSymbols);
    addSymbols(SYNTAX, langSymbols);
    return langSymbols;
  }

  private void addSymbols(String syntaxLanguage, List<Entry<String, Pattern>> langSymbols) {
    ResourceBundle langResources = ResourceBundle.getBundle(RESOURCES_PACKAGE + syntaxLanguage);
    for (String key : Collections.list(langResources.getKeys())) {
      String regex = langResources.getString(key);
      langSymbols.add(new SimpleEntry<>(key, Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
    }
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
