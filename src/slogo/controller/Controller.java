package slogo.controller;

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

  /**
   * Main constructor
   */
  public Controller() {
    myMainView = new MainView();
    TurtleGeneral myTurtleGeneral = new TurtleGeneral();
    myTurtleGeneral.setNewTurtleConsumer(myMainView.newTurtleConsumer());
    Parser myParser = new Parser(myTurtleGeneral, DEFAULT_LANGUAGE,
        myTurtleGeneral.getGlobalProperties());
    myTurtleGeneral.createFirstTurtle();
    myTurtleGeneral.getGlobalProperties().addActiveTurtleId(1);
    // Input button action
    myMainView.setInputAction(myParser.receiveInputAction());
    // Select language action
    myMainView.getLanguageSelector().setUpdateAction(myParser.updateAction());
    myMainView.setGlobalProperties(myTurtleGeneral.getGlobalProperties());
  }

  /**
   * Gets the MainView for this Controller instance.
   *
   * @return The MainView for this Controller instance.
   */
  public MainView getMainView() {
    return myMainView;
  }
}
