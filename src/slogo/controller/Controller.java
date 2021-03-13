package slogo.controller;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import slogo.model.GridCoordinates;
import slogo.model.ModelPen;
import slogo.model.Turtle;
import slogo.view.MainView;

/**
 * Link between the model, view, and parser
 *
 * @author David Li
 */

public class Controller {

  private final MainView myMainView;
  private final Parser myParser;
  private final Turtle myTurtle;
  private final TurtleController myTurtleController;

  /**
   * Main constructor
   */
  public Controller() {
    myMainView = new MainView();
    myTurtle = new Turtle(new GridCoordinates(), new ModelPen(),
        myMainView.getTurtleListener(), myMainView.getVariablesListener());
    myTurtleController = new TurtleController(myTurtle);
    myParser = new Parser(myTurtleController, "English", myMainView.getCommandsListener());

    // Input button action
    myMainView.setInputAction(myParser.receiveInputAction());
    // Select language action
    myMainView.getLanguageSelector().setUpdateAction(myParser.updateAction());
  }

  public MainView getMainView() {
    return myMainView;
  }

  public Turtle getTurtle() {
    return myTurtle;
  }

  public Parser getParser() {
    return myParser;
  }
}
