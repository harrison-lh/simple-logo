package slogo.controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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
  private final List<Entry<String, Pattern>> syntaxSymbols;
  private final List<UserCommand> userCommands;
  private final PropertyChangeListener commandsListener;
  private List<Entry<String, Pattern>> langSymbols;

  /**
   * Default constructor for Lexer. Takes no language, but has syntaxSymbols
   */
  public Lexer() {
    syntaxSymbols = instantiateSymbols(SYNTAX);
    langSymbols = new ArrayList<>();
    userCommands = new ArrayList<>();
    commandsListener = evt -> {
    };
  }

  /**
   * Calls main constructor, passing in an empty commands listener
   */
  public Lexer(String syntaxLanguage) {
    this(syntaxLanguage, e -> {
    });
  }

  /**
   * A more useful constructor for Lexer. Takes in a language for which to initialize the symbols.
   *
   * @param syntaxLanguage The language with which to initialize the symbols.
   */
  public Lexer(String syntaxLanguage, PropertyChangeListener commandsListener) {
    syntaxSymbols = instantiateSymbols(SYNTAX);
    langSymbols = instantiateSymbols(syntaxLanguage);
    userCommands = new ArrayList<>();
    this.commandsListener = commandsListener;
  }

  /**
   * Removes a user-defined command from the list of userCommands.
   *
   * @param name The name of the command to remove.
   */
  public void deleteUserCommand(String name) {
    userCommands.removeIf(command -> command.getName().equals(name));
  }

  /**
   * Adds a user-defined command to the list of userCommands.
   *
   * @param command The command to add to the list
   */
  public void addUserCommand(UserCommand command) {
    if (containsUserCommand(command.getName())) {
      commandsListener
          .propertyChange(
              new PropertyChangeEvent(this, "UPDATE", command.getName(), command.getName()));
      getUserCommand(command.getName()).updateCommand(command);
    } else {
      commandsListener
          .propertyChange(
              new PropertyChangeEvent(this, "ADD", command.getName(), command.getName()));
      userCommands.add(command);
    }
  }

  /**
   * Check the list of userCommands to see if it contains the user-command in question.
   *
   * @param name The name of the user-command.
   * @return The presence of the user-command.
   */
  public boolean containsUserCommand(String name) {
    for (UserCommand command : userCommands) {
      if (command.getName().equals(name)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Check the list of userCommands to see if it contains the user-command in question, and if so
   * return it!
   *
   * @param name The name of the user-command.
   * @return The UserCommand, if it exists.
   */
  public UserCommand getUserCommand(String name) {
    for (UserCommand command : userCommands) {
      if (command.getName().equals(name)) {
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
          case "GroupStart" -> {
            return Token.GROUP_START;
          }
          case "End" -> {
            return Token.COLLECTION_END;
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
      }
    }
    throw new IllegalArgumentException(ERROR);
  }


  // Returns true if the given text matches the given regular expression pattern
  private boolean match(String text, Pattern regex) {
    return regex.matcher(text).matches();
  }
}
