package slogo.view.canvas;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.function.Consumer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import slogo.model.Coordinates;
import slogo.model.GridCoordinates;
import slogo.view.SelectorTarget;

/**
 * Contains the gridlines, turtle view, and pen lines. Listens for updates from the model and
 * updates turtle and pen lines accordingly
 *
 * @author David Li
 */
public class TurtleCanvas extends StackPane implements SelectorTarget<String>,
    PropertyChangeListener {

  public static final double DEFAULT_CANVAS_WIDTH = 800;
  public static final double DEFAULT_CANVAS_HEIGHT = 480;

  private final GridLines myGridLines;
  private final TurtlesContainer myTurtlesContainer;
  private TurtleView myTurtleView;
  private final ViewPen myPen;
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

    myPen = new ViewPen();
    myPenLines = new Pane();

    this.getChildren().addAll(myGridLines, myPenLines);

    myTurtlesContainer = new TurtlesContainer();
    //createTurtle();
    // TODO: Get rid of myTurtleView instance variable
    //myTurtleView = myTurtlesContainer.get(1);
  }

  public TurtleView getTurtleView() {
    return myTurtleView;
  }

  public ViewPen getPen() {
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
    if (evt.getPropertyName().equals("VISIBILITY")) {
      setTurtleVisibility((Boolean) evt.getNewValue());
    } else if (evt.getPropertyName().equals("PEN")) {
      setPenActive((Boolean) evt.getNewValue());
    } else if (evt.getPropertyName().equals("CLEAR")) {
      clearScreen();
    }
  }

  /**
   *
   * @param coordinates
   */
  public void createTurtle(Coordinates coordinates) {
    TurtleView newTurtle = myTurtlesContainer.createTurtle(coordinates);

    newTurtle.headingProperty().addListener((observable, oldValue, newValue) -> {
      setTurtleHeading((Double) newValue);
      System.out.println(newTurtle.getCoordinates().getValue());
      System.out.println(newTurtle.getCoordinates().getValue().equals(new GridCoordinates()));
      newTurtle.setPosition(new GridCoordinates());
    });
    newTurtle.getCoordinates().addListener((observable, oldValue, newValue) -> {
      System.out.println("moved");
      setTurtleLocation(newValue);
    });
    newTurtle.getCoordinates().setY(100);

    this.getChildren().add(newTurtle);
    myTurtleView = newTurtle;

  }

  private void setTurtleHeading(double heading) {
    myTurtleView.setHeading(heading);
  }

  private void setTurtleLocation(Coordinates newCoordinates) {
    if (myPen.isPenActive()) {
      drawLine(myTurtleView.getXCoordinate(), myTurtleView.getYCoordinate(), newCoordinates.getX(),
          newCoordinates.getY(), myPen.getColor());
    }
    myTurtleView.setPosition(newCoordinates.getX(), newCoordinates.getY());
  }

  private void setTurtleVisibility(boolean visible) {
    if (visible) {
      myTurtleView.setOpacity(1);
    } else {
      myTurtleView.setOpacity(0);
    }
  }

  private void setPenActive(boolean penActive) {
    if (penActive) {
      myPen.placePen();
    } else {
      myPen.liftPen();
    }
  }

  private void drawLine(double startX, double startY, double endX, double endY, Paint penColor) {
    PenLine penLine = new PenLine(startX, startY, endX, endY, penColor);
    myPenLines.getChildren().add(penLine);
  }

  private void clearScreen() {
    myPenLines.getChildren().clear();
  }
}
