package slogo.controller.commands;

import java.util.concurrent.ThreadLocalRandom;
import slogo.controller.Command;
import slogo.controller.GlobalProperties;
import slogo.model.Turtle;

public class RandomCommand extends Command {

  private static final int NUM_PARAMS = 1;

  public RandomCommand() {
    setNumParams(NUM_PARAMS);
  }

  @Override
  protected double executeCommand(Turtle turtle, GlobalProperties globalProperties) {
    double bound = getChildren().get(0).execute(turtle, globalProperties);

    return ThreadLocalRandom.current().nextDouble(0, bound);

  }
}

