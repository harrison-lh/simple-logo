package util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * LibraryManager is a utility class that allows the program to save the variables and user-defined
 * functions to JSON files, which can then be loaded back into the program at a later time.
 * <p>
 * Storing information is handled by passing in a Map that takes the general form
 * Map<NAME_OF_THING_TO_STORE, THING_TO_STORE> while loading handled by reading the file in, and
 * converting into a SLogo command, which is then passed to the Parser and run.
 *
 * @author Marc Chmielewski
 */
public class LibraryManager {

  private static final ObjectMapper mapper = new ObjectMapper();

  /**
   * saveVariables saves the contents of varMap to a JSON file, which can then be loaded back in at
   * a later time.
   *
   * @param filepath The filepath of the JSON file.
   * @param varMap   The map of Strings and Doubles that represents the variables.
   * @throws IOException On invalid filenames and paths
   */
  public static void saveVariables(String filepath, Map<String, Double> varMap)
      throws IOException {
    mapper.writeValue(new File(filepath), varMap);
  }

  /**
   * saveUserCommands saves the contents of userCommandMap to a JSON file, which can then be loaded
   * back in at a later time.
   *
   * @param filepath       The filepath of the JSON file.
   * @param userCommandMap The map of Strings and Strings that represents the commands.
   * @throws IOException On invalid filenames and paths
   */
  public static void saveUserCommands(String filepath, Map<String, String> userCommandMap)
      throws IOException {
    mapper.writeValue(new File(filepath),
        userCommandMap);
  }

  /**
   * loadVariables converts the contents of the provided JSON into a command String that can be
   * passed to the Parser to populate the environment with the variable contents thereof.
   *
   * @param filepath The filepath of the JSON to load
   * @return A properly formatted command String that will load the variables from the JSON into the
   * environment.
   * @throws IOException If file is unable to be located or read.
   */
  public static String loadVariables(String filepath) throws IOException {
    Map<String, Double> varMap
        = mapper.readValue(new File(filepath),
        new TypeReference<>() {
        });
    StringBuilder loadVarCommand = new StringBuilder();
    for (String curVar : varMap.keySet()) {
      loadVarCommand.append("MAKE ").append(curVar).append(" ").append(varMap.get(curVar))
          .append(" ");
    }
    return loadVarCommand.toString().trim();
  }

  /**
   * loadUserCommands converts the contents of the provided JSON into a command String that can be
   * passed to the Parser to populate the environment with the user-command contents thereof.
   *
   * @param filepath The filepath of the JSON to load
   * @return A properly formatted command String that will load the user-commands from the JSON into
   * the environment.
   * @throws IOException If the file is unable to be located or read.
   */
  public static String loadUserCommands(String filepath) throws IOException {
    Map<String, String> userCommandMap
        = mapper.readValue(new File(filepath),
        new TypeReference<>() {
        });
    StringBuilder loadUserCommand = new StringBuilder();
    for (String curCommand : userCommandMap.keySet()) {
      loadUserCommand.append(userCommandMap.get(curCommand)).append("\n");
    }
    return loadUserCommand.toString().trim();
  }
}
