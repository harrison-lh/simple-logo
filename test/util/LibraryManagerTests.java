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
  public void testSaveVariables() {
    Map<String, Double> testVars = new HashMap<>();
    testVars.put(":A", 50.0);
    testVars.put(":B", 75.0);
    testVars.put(":DUKE", 2022.0);

    try {
      LibraryManager.saveVariables("test.json", testVars);
    } catch (IOException e) {
      System.err.println(e.getMessage());
      fail("Failed to create file!");
    }

    File savedVars = new File("resources/libraries/variables/test.json");
    assertTrue(savedVars.exists());
  }

  @Test
  public void testSaveUserCommands() {
    Map<String, String> testUserCommands = new HashMap<>();
    testUserCommands.put("square", "TO square [ ] [ repeat 4 [ fd 50 rt 90 ] ]");

    try {
      LibraryManager.saveUserCommands("test.json", testUserCommands);
    } catch (IOException e) {
      System.err.println(e.getMessage());
      fail("Failed to create file!");
    }

    File savedUCs = new File("resources/libraries/user-commands/test.json");
    assertTrue(savedUCs.exists());
  }

  @Test
  public void testLoadVariables() {

  }
}
