package slogo.view.canvas;

import java.util.function.Consumer;
import javafx.beans.property.DoubleProperty;
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

  /**
   * Main constructor
   */
//  public TurtleView() {
//    this.setId("TurtleView");
//    changeTurtleImage("Default");
//    this.setFitHeight(HEIGHT);
//    this.setPreserveRatio(true);
//    this.setSmooth(true);
//    this.setCache(true);
//    coordinates = new GridCoordinates();
//  }

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
    setPosition(coordinates.getX(), coordinates.getY());
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

  public void setPosition(double x, double y) {
    coordinates.setX(x);
    coordinates.setY(y);
    this.setTranslateX(TurtleCanvas.convertXCoordinate(x));
    this.setTranslateY(TurtleCanvas.convertYCoordinate(y));
  }

  public void setHeading(double heading) {
    coordinates.setHeading(heading);
    this.setRotate(TurtleCanvas.convertHeading(heading));
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

  public DoubleProperty headingProperty() {
    return coordinates.headValProperty();
  }

  public Coordinates getCoordinates() {
    return coordinates;
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
