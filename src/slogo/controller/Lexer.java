package slogo.controller;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import slogo.controller.commands.UserCommand;

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
  private List<Entry<String, Pattern>> syntaxSymbols;
  private List<Entry<String, Pattern>> langSymbols;
  private List<UserCommand> userCommands;

  /**
   * Default constructor for Lexer. Takes no language, but has syntaxSymbols
   */
  public Lexer() {
    syntaxSymbols = instantiateSymbols(SYNTAX);
    langSymbols = new ArrayList<>();
    userCommands = new ArrayList<>();
  }

  /**
   * A more useful constructor for Lexer. Takes in a language for which to initialize the symbols.
   *
   * @param syntaxLanguage The language with which to initialize the symbols.
   */
  public Lexer(String syntaxLanguage) {
    syntaxSymbols = instantiateSymbols(SYNTAX);
    langSymbols = instantiateSymbols(syntaxLanguage);
    userCommands = new ArrayList<>();
  }

  public void addUserCommand(UserCommand command){

    userCommands.add(command);

  }

  public boolean containsUserCommand(String name){

    for(UserCommand command : userCommands){

      if(command.getName().equals(name)){
        return true;
      }
    }
    return false;
  }

  public UserCommand getUserCommand(String name){
    for(UserCommand command : userCommands){
      if(command.getName().equals(name)){
        return command;
      }
    }
    return null;
  }

  /**
   * Instantiates the lexer with a set of symbols for the language, syntaxLanguage.
   * <p>
   * NOTE: This is designed such that only one language can have symbols loaded at a time to avoid
   * naming conflicts.
   *
   * @param syntaxLanguage The language to load symbols for
   * @return A List<Entry<String, Pattern>> that represents the symbols as standard regexes
   */
  private List<Entry<String, Pattern>> instantiateSymbols(String syntaxLanguage) {
    List<Entry<String, Pattern>> symbols = new ArrayList<>();
    addSymbols(syntaxLanguage, symbols);
    return symbols;
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
  public List<Entry<String, Pattern>> getLangSymbols() {
    return langSymbols;
  }

  /**
   * Set the symbols for the Lexer to the syntaxLanguage. Allows for hot-swapping between
   * languages.
   *
   * @param syntaxLanguage The language to which the symbols are to be set.
   */
  public void setLangSymbols(String syntaxLanguage) {
    langSymbols = instantiateSymbols(syntaxLanguage);
  }

  /**
   * Get the List of symbols for the general SLogo syntax
   *
   * @return A List<Entry<String, Pattern>> that represents the symbols for the general SLogo
   * syntax.
   */
  public List<Entry<String, Pattern>> getSyntaxSymbols() {
    return syntaxSymbols;
  }

  /**
   * Return a Token that matches the syntactic archetype of the text that has been passed into it.
   * If the text cannot be matched to a valid SLogo command type with the provided regexes, then it
   * is assumed to be an erroneous value and an IllegalArgumentException is thrown.
   *
   * @param text The text to tokenize
   * @return The Token that matches the tokenized text
   * @throws IllegalArgumentException If the text is unable to be tokenized.
   */
  public Token tokenize(String text) throws IllegalArgumentException {
    final String ERROR = "ILLEGAL ARGUMENT EXCEPTION: NO MATCH! UNRECOGNIZED TOKEN!";
    for (Entry<String, Pattern> e : syntaxSymbols) {
      if (match(text, e.getValue())) {
        switch (e.getKey()) {
          case "Comment" -> {
            return Token.COMMENT;
          }
          case "Constant" -> {
            return Token.CONSTANT;
          }
          case "Variable" -> {
            return Token.VARIABLE;
          }
          case "Command" -> {
            return Token.COMMAND;
          }
          case "ListStart" -> {
            return Token.LIST_START;
          }
          case "ListEnd" -> {
            return Token.LIST_END;
          }
        }
      }
    }
    throw new IllegalArgumentException(ERROR);
  }

  /**
   * Return a String that matches the type of command that is being lexed in text. If the text
   * cannot be matched to a valid SLogo command using the provided regexes, then it is assumed to be
   * erroneous and an IllegalArgumentException is thrown.
   *
   * @param text The text to lex.
   * @return A String that matches the command type that has been lexed, if it exists.
   * @throws IllegalArgumentException If the text is unable to be lexed.
   */
  public String lexLangDefinedCommands(String text) throws IllegalArgumentException {
    final String ERROR = "ILLEGAL ARGUMENT EXCEPTION: NO MATCH! UNRECOGNIZED SYNTAX!";
    if (containsUserCommand(text)) {
      return "User";
    }
    for (Entry<String, Pattern> e : langSymbols) {
      if (match(text, e.getValue())) {
        return e.getKey();
      }  //System.out.println("Unable to find user command");
    }
    throw new IllegalArgumentException(ERROR);
  }


  // Returns true if the given text matches the given regular expression pattern
  private boolean match(String text, Pattern regex) {
    return regex.matcher(text).matches();
  }
}
