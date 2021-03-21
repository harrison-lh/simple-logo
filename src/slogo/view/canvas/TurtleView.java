package slogo.view.canvas;

import java.util.function.Consumer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import slogo.model.Coordinates;
import slogo.model.GridCoordinates;
import slogo.view.SelectorTarget;

/**
 * Image view of the view turtle
 *
 * @author David Li
 */
public class TurtleView extends ImageView implements SelectorTarget<String> {

  public static final String IMAGES_DIRECTORY = "resources/images/";
  public static final String DEFAULT_TURTLE = "turtle-default.png";
  public static final String REALISTIC_TURTLE = "turtle-realistic.png";
  private static final double HEIGHT = 40;

  private Image myTurtleImage;
  private Coordinates coordinates;
  private Coordinates prevCoordinates;
  private boolean isPenActive;
  private Consumer<PenLine> myDrawConsumer;

  /**
   * Constructor with Coordinates object.
   *
   * @param coordinates The coordinates of the turtle
   */
  public TurtleView(Coordinates coordinates, BooleanProperty isVisibleProperty,
      BooleanProperty isPenActiveProperty) {
    this.setId("TurtleView");
    changeTurtleImage("Default");
    this.setFitHeight(HEIGHT);
    this.setPreserveRatio(true);
    this.setSmooth(true);
    this.setCache(true);
    this.coordinates = coordinates;
    prevCoordinates = new GridCoordinates(coordinates);
    updatePosition();
    isPenActive = isPenActiveProperty.get();
    this.coordinatesStringProperty().addListener((observable, oldValue, newValue) -> {
      updatePosition();
      updateHeading();
    });
    isVisibleProperty
        .addListener((observable, oldValue, newValue) -> setTurtleVisibility(newValue));
    isPenActiveProperty.addListener((observable, oldValue, newValue) -> isPenActive = newValue);
  }

  /**
   * Changes the turtle image
   */
  @Override
  public Consumer<String> updateAction() {
    return this::changeTurtleImage;
  }

  private void updatePosition() {
    this.setTranslateX(TurtleCanvas.convertXCoordinate(getXCoordinate()));
    this.setTranslateY(TurtleCanvas.convertYCoordinate(getYCoordinate()));
    double startX = prevCoordinates.getX();
    double startY = prevCoordinates.getY();
    double endX = coordinates.getX();
    double endY = coordinates.getY();
    if (isPenActive) {
      myDrawConsumer.accept(new PenLine(startX, startY, endX, endY));
    }
    updatePrevCoordinates();
  }

  public void setDrawConsumer(Consumer<PenLine> consumer) {
    myDrawConsumer = consumer;
  }

  public double getXCoordinate() {
    return coordinates.getX();
  }

  public double getYCoordinate() {
    return coordinates.getY();
  }

  public double getHeading() {
    return coordinates.getHeading();
  }

  public StringProperty coordinatesStringProperty() {
    return coordinates.stringProperty();
  }

  private void updatePrevCoordinates() {
    prevCoordinates = new GridCoordinates(coordinates);
  }

  private void setTurtleVisibility(boolean visible) {
    if (visible) {
      this.setOpacity(1);
    } else {
      this.setOpacity(0);
    }
  }

  private void updateHeading() {
    this.setRotate(TurtleCanvas.convertHeading(getHeading()));
  }

  private void changeTurtleImage(String turtleImage) {
    if (turtleImage.equals("Default")) {
      myTurtleImage = new Image(IMAGES_DIRECTORY + DEFAULT_TURTLE);
      this.setImage(myTurtleImage);
    } else if (turtleImage.equals("Realistic")) {
      myTurtleImage = new Image(IMAGES_DIRECTORY + REALISTIC_TURTLE);
      this.setImage(myTurtleImage);
    }
  }

  private String getTurtleImageFilename() {
    String fullUrl = myTurtleImage.getUrl();
    return fullUrl.substring(fullUrl.lastIndexOf('/') + 1);
  }

}
