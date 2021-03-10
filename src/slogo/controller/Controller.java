package slogo.controller;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import slogo.model.GridCoordinates;
import slogo.model.Turtle;
import slogo.view.JavaFXPen;
import slogo.view.MainView;

/**
 * Link between the model, view, and parser
 *
 * @author David Li
 */

public class Controller {

  private MainView myMainView;
  private Parser myParser;
  private Turtle myTurtle;
  private TurtleController myTurtleController;

  public Controller() {
    myMainView = new MainView();
    myTurtle = new Turtle(new GridCoordinates(), new JavaFXPen(Color.BLACK, new ImageView()));
    myTurtleController = new TurtleController(myTurtle);
    myParser = new Parser(myTurtleController, "English");
  }

  public MainView getMainView() {
    return myMainView;
  }
}
