package slogo.controller.commands;

import java.util.Map;
import slogo.controller.Command;
import slogo.controller.GlobalProperties;
import slogo.model.Turtle;

/**
 * GetPenColor is a type of Command that gets the current turtle shape.
 *
 * @author Harrison Huang
 */

public class GetShapeCommand extends Command {

  private static final int NUM_PARAMS = 0;

  /**
   * Constructor for GetShapeCommand.
   */
  public GetShapeCommand() {
    setNumParams(NUM_PARAMS);
  }

  /**
   * Gets the current turtle shape.
   *
   * @param turtle           The current active turtle
   * @param globalProperties The global properties
   * @return The index of the current turtle shape
   */
  @Override
  protected double executeCommand(Turtle turtle, GlobalProperties globalProperties) {
    Map<Integer, String> shapeMap = globalProperties.getShapeMap();
    for (int i = 0; i < shapeMap.keySet().size(); i++) {
      if (globalProperties.turtleShapePropertyProperty().get().equals(shapeMap.get(i))) {
        return i;
      }
    }
    return 0.0;
  }

}
