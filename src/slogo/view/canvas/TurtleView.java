package slogo.view.canvas;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.function.Consumer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import slogo.view.SelectorTarget;

public class TurtleView extends ImageView implements SelectorTarget<String>{

  public static final String IMAGES_DIRECTORY = "resources/images/";
  public static final String DEFAULT_TURTLE = "turtle-default.png";
  public static final String REALISTIC_TURTLE = "turtle-realistic.png";

  private Image myTurtleImage;
  private double xCoordinate;
  private double yCoordinate;
  private double heading;

  public TurtleView() {
    this.setId("TurtleView");
    changeTurtleImage("Default");
    this.setFitHeight(50);
    this.setPreserveRatio(true);
    this.setSmooth(true);
    this.setCache(true);

    this.setXCoordinate(0);
    this.setYCoordinate(0);
    this.setHeading(0);
  }

  public String getTurtleImageFilename() {
    String fullUrl = myTurtleImage.getUrl();
    return fullUrl.substring(fullUrl.lastIndexOf('/') + 1);
  }

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

  private void changeTurtleImage(String turtleImage) {
    if (turtleImage.equals("Default")) {
      myTurtleImage = new Image(IMAGES_DIRECTORY + DEFAULT_TURTLE);
      this.setImage(myTurtleImage);
    }
    else if (turtleImage.equals("Realistic")) {
      myTurtleImage = new Image(IMAGES_DIRECTORY + REALISTIC_TURTLE);
      this.setImage(myTurtleImage);
    }
  }
}
