package slogo.view.canvas;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.function.Consumer;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import slogo.controller.TurtleProperties;
import slogo.view.Pen;
import slogo.view.SelectorTarget;

/**
 * Contains the gridlines, turtle view, and pen lines. Listens for updates from the model and
 * updates turtle and pen lines accordingly.
 *
 * @author David Li
 * @author Harrison Huang
 */
public class TurtleCanvas extends StackPane implements SelectorTarget<String>,
    PropertyChangeListener {

  public static final double DEFAULT_CANVAS_WIDTH = 800;
  public static final double DEFAULT_CANVAS_HEIGHT = 400;
  public static final String[] TURTLE_SHAPES = new String[]{"Default", "Realistic"};

  private final GridLines myGridLines;
  private final TurtlesContainer myTurtlesContainer;
  private TurtleView myTurtleView;
  private final Pen myPen;
  private final Pane myPenLines;

  public static double convertXCoordinate(double x) {
    return x;
  }

  public static double convertYCoordinate(double y) {
    return -1 * y;
  }

  public static double convertHeading(double heading) {
    return (90 - heading);
  }

  public TurtleCanvas() {
    this.setId("TurtleCanvas");
    this.setMaxSize(DEFAULT_CANVAS_WIDTH, DEFAULT_CANVAS_HEIGHT);

    myGridLines = new GridLines();
    myGridLines.changeGridType("None");

    myPen = new Pen();
    myPenLines = new Pane();

    this.getChildren().addAll(myGridLines, myPenLines);

    myTurtlesContainer = new TurtlesContainer();
  }

  public TurtleView getTurtleView() {
    return myTurtleView;
  }

  public Pen getPen() {
    return myPen;
  }

  /**
   * Changes grid type when new grid type is selected
   */
  @Override
  public Consumer<String> updateAction() {
    return myGridLines::changeGridType;
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    if (evt.getPropertyName().equals("CLEAR")) {
      clearScreen();
    }
  }

  /**
   * Creates a new turtle in the view that syncs with the respective model turtle. Contains three
   * properties to listen for: position of the turtle (includes x,y and heading), whether the turtle
   * is visible or not, and whether the pen is active or not.
   *
   * @param turtleProperties The properties of the turtle
   */
  public void createTurtle(TurtleProperties turtleProperties) {
    TurtleView newTurtle = myTurtlesContainer.createTurtle(turtleProperties);
    newTurtle.setDrawConsumer(this::drawLine);
    this.getChildren().add(newTurtle);
    myTurtleView = newTurtle;
  }

  public Consumer<TurtleProperties> newTurtleConsumer() {
    return this::createTurtle;
  }

  public void clearScreen() {
    myPenLines.getChildren().clear();
  }

  public void setTurtleShape(String shape) {
    myTurtlesContainer.setTurtleShapes(shape);
  }

  private void drawLine(PenLine penLine) {
    penLine.setStroke(myPen.getColor());
    penLine.setStrokeWidth(myPen.getSize());
    myPenLines.getChildren().add(penLine);
  }

}
