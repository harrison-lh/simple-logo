package util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import slogo.model.Variables;

public class LoadSaveTests {
  @Test
  public void testSaveVariables() {
    Map<String, Double> testVars = new HashMap<>();
    testVars.put(":A", 50.0);
    testVars.put(":B", 75.0);
    testVars.put(":DUKE", 2022.0);
    try {
      LibrarySaver.saveLibrary("test.json", testVars);
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }
  }
}
