package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import slogo.model.Variables;

public class LibrarySaver {
    public static void saveLibrary(String filename, Map<String, Double> varMap)
        throws IOException {
      ObjectMapper mapper = new ObjectMapper();
      mapper.writeValue(new File("resources/libraries/variables/" + filename), varMap);
    }
}
