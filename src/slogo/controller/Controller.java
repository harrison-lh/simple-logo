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

  private final MainView myMainView;
  private final Parser myParser;
  private final Turtle myTurtle;
  private final TurtleController myTurtleController;

  public Controller() {
    myMainView = new MainView();
    myTurtle = new Turtle(new GridCoordinates(), new JavaFXPen(Color.BLACK, new ImageView()));
    myTurtleController = new TurtleController(myTurtle);
    myParser = new Parser(myTurtleController, "English");

    myMainView.setInputAction(myParser.receiveInputAction());
  }

  public MainView getMainView() {
    return myMainView;
  }
}
