package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * LibraryManager is a utility class that allows the program to save the variables and user-defined
 * functions to JSON files, which can then be loaded back into the program at a later time.
 *
 * @author Marc Chmielewski
 */
public class LibraryManager {

  public static void saveLibrary(String filename, Map<String, Double> varMap,
      Map<String, String> userCommandMap)
      throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    mapper.writeValue(new File("resources/libraries/variables/" + filename + "_vars.json"), varMap);
    mapper.writeValue(new File("resources/libraries/user-commands/" + filename + "_ucs.json"), userCommandMap);
  }

  public static void loadLibrary() {
    // TODO: Implement this!
  }
}
