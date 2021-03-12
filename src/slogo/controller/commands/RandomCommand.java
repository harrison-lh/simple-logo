package slogo.controller.commands;

import java.util.concurrent.ThreadLocalRandom;
import slogo.controller.Command;
import slogo.model.Turtle;

public class RandomCommand extends Command {

  private static final int NUM_PARAMS = 1;

  public RandomCommand(){
    setNumParams(NUM_PARAMS);
  }

  @Override
  public double executeCommand(Turtle turtle) {
    double bound = getChildren().get(0).execute(turtle);

    return ThreadLocalRandom.current().nextDouble(0, bound);

  }
}

