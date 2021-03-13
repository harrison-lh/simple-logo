package slogo.view.canvas;

import java.util.function.Consumer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
  private static final double HEIGHT = 50;

  private Image myTurtleImage;
  private double xCoordinate;
  private double yCoordinate;
  private double heading;

  /**
   * Main constructor
   */
  public TurtleView() {
    this.setId("TurtleView");
    changeTurtleImage("Default");
    this.setFitHeight(HEIGHT);
    this.setPreserveRatio(true);
    this.setSmooth(true);
    this.setCache(true);
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

  public void setXCoordinate(double x) {
    this.xCoordinate = x;
  }

  public void setYCoordinate(double y) {
    this.yCoordinate = y;
  }

  public void setHeading(double heading) {
    this.heading = heading;
  }

  public double getXCoordinate() {
    return xCoordinate;
  }

  public double getYCoordinate() {
    return yCoordinate;
  }

  public double getHeading() {
    return heading;
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
