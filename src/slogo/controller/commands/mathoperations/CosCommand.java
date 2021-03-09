package slogo.controller.commands.mathoperations;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import slogo.controller.Command;
import slogo.model.Turtle;

public class CosCommand extends Command {

  private static final int NUM_PARAMS = 1;

  public CosCommand(){
    numParams = NUM_PARAMS;
  }

  @Override
  public double execute(Turtle turtle) {
    double degrees = getChildren().get(0).execute(turtle);

    return Math.cos(degrees);
  }
}

