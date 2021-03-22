package slogo.view.canvas;

import java.util.function.Consumer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import slogo.controller.TurtleProperties;
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
  private final Coordinates coordinates;
  private final BooleanProperty isPenActive;
  private Image myTurtleImage;
  private Coordinates prevCoordinates;
  private Consumer<PenLine> myDrawConsumer;

  /**
   * Constructor with TurtleProperties.
   *
   * @param turtleProperties The properties of the turtle
   */
  public TurtleView(TurtleProperties turtleProperties) {
    this.setId("TurtleView");
    changeTurtleImage("Default");
    this.setFitHeight(HEIGHT);
    this.setPreserveRatio(true);
    this.setSmooth(true);
    this.setCache(true);

    myDrawConsumer = penLine -> {
    };
    coordinates = turtleProperties.getCoordinates();
    prevCoordinates = new GridCoordinates(coordinates);
    isPenActive = turtleProperties.penActiveProperty();
    updatePosition();
    this.coordinatesStringProperty().addListener((observable, oldValue, newValue) -> {
      updatePosition();
      updateHeading();
    });
    turtleProperties.visibleProperty()
        .addListener((observable, oldValue, newValue) -> setTurtleVisibility(newValue));
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
    if (isPenActive.get()) {
      myDrawConsumer.accept(new PenLine(startX, startY, endX, endY));
    }
    updatePrevCoordinates();
  }

  public void changeTurtleImage(String turtleImage) {
    if (turtleImage.equals("Default")) {
      myTurtleImage = new Image(IMAGES_DIRECTORY + DEFAULT_TURTLE);
      this.setImage(myTurtleImage);
    } else if (turtleImage.equals("Realistic")) {
      myTurtleImage = new Image(IMAGES_DIRECTORY + REALISTIC_TURTLE);
      this.setImage(myTurtleImage);
    }
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

  private String getTurtleImageFilename() {
    String fullUrl = myTurtleImage.getUrl();
    return fullUrl.substring(fullUrl.lastIndexOf('/') + 1);
  }

}
