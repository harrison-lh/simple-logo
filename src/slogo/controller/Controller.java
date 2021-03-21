package slogo.controller;

import slogo.model.GridCoordinates;
import slogo.model.Turtle;
import slogo.view.MainView;

/**
 * Link between the model, view, and parser
 *
 * @author David Li
 * @author Marc Chmielewski
 */

public class Controller {

  public static final String DEFAULT_LANGUAGE = "English";

  private final MainView myMainView;
  private final Parser myParser;
  private final Turtle myTurtle;
  private final TurtleGeneral myTurtleGeneral;

  /**
   * Main constructor
   */
  public Controller() {
    myMainView = new MainView();
    myTurtle = new Turtle(0, new GridCoordinates(),
        myMainView.getTurtleListener(), myMainView.getVariablesListener());
    myMainView.createTurtle(new TurtleProperties(myTurtle));
    TurtleController initController = new TurtleController(myTurtle);
    myTurtleGeneral = new TurtleGeneral(initController);
    myTurtleGeneral.setNewTurtleConsumer(myMainView.newTurtleConsumer());
    myParser = new Parser(myTurtleGeneral, DEFAULT_LANGUAGE, myMainView.getCommandsListener());
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
