package slogo.view.canvas;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.function.Consumer;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import slogo.model.Coordinates;
import slogo.view.JavaFXPen;
import slogo.view.SelectorTarget;

public class TurtleCanvas extends StackPane implements SelectorTarget<String>,
    PropertyChangeListener {

  public static final double DEFAULT_GRID_WIDTH = 600;
  public static final double DEFAULT_GRID_HEIGHT =
      DEFAULT_GRID_WIDTH / CanvasHolder.TURTLE_CANVAS_ASPECT_RATIO;

  private final GridLines myGridLines;
  private final TurtleView myTurtleView;
  private final JavaFXPen myPen;
  private final Pane myPenLines;

  public TurtleCanvas() {
    this.setId("TurtleCanvas");

    myGridLines = new GridLines();
    myGridLines.changeGridType("None");
    myTurtleView = new TurtleView();
    myTurtleView.setXCoordinate(convertXCoordinate(0));
    myTurtleView.setYCoordinate(convertYCoordinate(0));
    myTurtleView.setHeading(convertHeading(0));
    myPen = new JavaFXPen(Color.BLACK);
    myPenLines = new Pane();

    this.getChildren().addAll(myGridLines, myPenLines, myTurtleView);
  }

  public TurtleView getTurtleView() {
    return myTurtleView;
  }

  public JavaFXPen getPen() {
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
    if (evt.getPropertyName().equals("LOCATION")) {
      setTurtleLocation((Coordinates) evt.getNewValue());
    } else if (evt.getPropertyName().equals("HEADING")) {
      setTurtleHeading((Double) evt.getNewValue());
    } else if (evt.getPropertyName().equals("VISIBILITY")) {
      setTurtleVisibility((Boolean) evt.getNewValue());
    } else if (evt.getPropertyName().equals("PEN")) {
      setPenActive((Boolean) evt.getNewValue());
    }
  }

  public void resizeElements() {
    myGridLines.resize();
    myPenLines.setPrefSize(this.getWidth(), this.getHeight());
    myTurtleView.setTranslateX(convertXCoordinate(myTurtleView.getXCoordinate()));
    myTurtleView.setTranslateY(convertYCoordinate(myTurtleView.getYCoordinate()));
    for (Node penLine : myPenLines.getChildren()) {
      resizePenLine((PenLine) penLine);
    }
  }

  private void resizePenLine(PenLine penLine) {
    penLine.setStartX(convertXCoordinate(penLine.getStartXCoordinate()) + this.getWidth() / 2);
    penLine.setStartY(convertYCoordinate(penLine.getStartYCoordinate()) + this.getHeight() / 2);
    penLine.setEndX(convertXCoordinate(penLine.getEndXCoordinate()) + this.getWidth() / 2);
    penLine.setEndY(convertYCoordinate(penLine.getEndYCoordinate()) + this.getHeight() / 2);
  }

  private void setTurtleHeading(double heading) {
    myTurtleView.setHeading(convertHeading(heading));
    myTurtleView.setRotate(convertHeading(heading));
  }

  private void setTurtleLocation(Coordinates newCoordinates) {
    if (myPen.isPenActive()) {
      drawLine(myTurtleView.getXCoordinate(), myTurtleView.getYCoordinate(), newCoordinates.getX(),
          newCoordinates.getY(), myPen.getColor());
    }

    myTurtleView.setXCoordinate(newCoordinates.getX());
    myTurtleView.setTranslateX(convertXCoordinate(newCoordinates.getX()));
    myTurtleView.setYCoordinate(newCoordinates.getY());
    myTurtleView.setTranslateY(convertYCoordinate(newCoordinates.getY()));
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
    resizePenLine(penLine);
  }

  private double convertHeading(double heading) {
    return (90 - heading);
  }

  private double convertXCoordinate(double x) {
    return x * this.getWidth() / DEFAULT_GRID_WIDTH;
  }

  private double convertYCoordinate(double y) {
    return -1 * y * this.getHeight() / DEFAULT_GRID_HEIGHT;
  }
}
