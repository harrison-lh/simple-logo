package slogo.view.canvas;

import java.util.function.Consumer;
import javafx.beans.property.DoubleProperty;
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

/*
  /**
   * Main constructor
   * /
  public TurtleView() {
    this.setId("TurtleView");
    changeTurtleImage("Default");
    this.setFitHeight(HEIGHT);
    this.setPreserveRatio(true);
    this.setSmooth(true);
    this.setCache(true);
    coordinates = new GridCoordinates();
  }
*/

  /**
   * Constructor with Coordinates object.
   */
  public TurtleView(Coordinates coordinates) {
    this.setId("TurtleView");
    changeTurtleImage("Default");
    this.setFitHeight(HEIGHT);
    this.setPreserveRatio(true);
    this.setSmooth(true);
    this.setCache(true);
    this.coordinates = coordinates;
    setPosition();
    prevCoordinates = new GridCoordinates(coordinates);
  }

  /**
   * @return File name of the turtle image file
   */
  public String getTurtleImageFilename() {
    String fullUrl = myTurtleImage.getUrl();
    return fullUrl.substring(fullUrl.lastIndexOf('/') + 1);
  }

  /**
   * Changes the turtle image
   */
  @Override
  public Consumer<String> updateAction() {
    return this::changeTurtleImage;
  }

  public void setPosition() {
    this.setTranslateX(TurtleCanvas.convertXCoordinate(getXCoordinate()));
    this.setTranslateY(TurtleCanvas.convertYCoordinate(getYCoordinate()));
  }

  public void setHeading() {
    this.setRotate(TurtleCanvas.convertHeading(getHeading()));
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

  public double getPrevXCoordinate() {
    return prevCoordinates.getX();
  }

  public double getPrevYCoordinate() {
    return prevCoordinates.getY();
  }

  public void updatePrevCoordinates() {
    prevCoordinates = new GridCoordinates(coordinates);
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
}
