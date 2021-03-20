package util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

/**
 * A suite of tests for the LibraryManager.
 *
 * @author Marc Chmielewski
 */
public class LibraryManagerTests {

  @Test
  public void testSaveVariables() {
    Map<String, Double> testVars = new HashMap<>();
    testVars.put(":A", 50.0);
    testVars.put(":B", 75.0);
    testVars.put(":DUKE", 2022.0);

    try {
      LibraryManager.saveVariables("resources/libraries/variables/test.json", testVars);
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
    testUserCommands.put("equilateral_triangle",
        "TO equilateral_triangle [ ] [ repeat 3 [ rt 120 fd 50 ] ]");

    try {
      LibraryManager.saveUserCommands("resources/libraries/user-commands/test.json",
          testUserCommands);
    } catch (IOException e) {
      System.err.println(e.getMessage());
      fail("Failed to create file!");
    }

    File savedUCs = new File("resources/libraries/user-commands/test.json");
    assertTrue(savedUCs.exists());
  }

  @Test
  public void testLoadVariables() {
    String loadVarsCommand = "";
    try {
      loadVarsCommand =
          LibraryManager.loadVariables("resources/libraries/variables/test.json");
    } catch (IOException e) {
      System.err.println(e.getMessage());
      fail("Failed to read file!");
    }
    assertEquals(loadVarsCommand, "MAKE :DUKE 2022.0 MAKE :A 50.0 MAKE :B 75.0");
    System.out.println(loadVarsCommand);
  }

  @Test
  public void testLoadUserCommands() {
    String loadUserCommands = "";
    try {
      loadUserCommands =
          LibraryManager.loadUserCommands("resources/libraries/user-commands/test.json");
    } catch (IOException e) {
      System.err.println(e.getMessage());
      fail("Failed to read file!");
    }
    assertEquals(loadUserCommands, "TO square [ ] [ repeat 4 [ fd 50 rt 90 ] ]\n"
        + "TO equilateral_triangle [ ] [ repeat 3 [ rt 120 fd 50 ] ]");
    System.out.println(loadUserCommands);
  }
}
