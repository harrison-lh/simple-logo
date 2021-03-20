package util;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class LibraryManagerTests {
  @Test
  public void testSaveLibrary() {
    Map<String, Double> testVars = new HashMap<>();
    testVars.put(":A", 50.0);
    testVars.put(":B", 75.0);
    testVars.put(":DUKE", 2022.0);

    Map<String, String> testUserCommands = new HashMap<>();
    testUserCommands.put("square", "TO square [ ] [ repeat 4 [ fd 50 rt 90 ] ]");
    try {
      LibraryManager.saveLibrary("test", testVars, testUserCommands);
    } catch (IOException e) {
      System.err.println(e.getMessage());
      fail("Failed to create file!");
    }

    File savedVars = new File("resources/libraries/variables/test_vars.json");
    assertTrue(savedVars.exists());

    File savedUCs = new File("resources/libraries/user-commands/test_ucs.json");
    assertTrue(savedUCs.exists());
  }
}
