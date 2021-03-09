package slogo.view.canvas;

import java.util.function.Consumer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import slogo.view.SelectorTarget;

public class TurtleView extends ImageView implements SelectorTarget<String> {

  public static final String IMAGES_DIRECTORY = "resources/images/";
  public static final String DEFAULT_TURTLE = "turtle-default.png";
  public static final String REALISTIC_TURTLE = "turtle-realistic.png";

  private Image myTurtleImage;

  public TurtleView() {
    this.setId("TurtleView");
    changeTurtleImage("Default");
    this.setFitHeight(50);
    this.setPreserveRatio(true);
    this.setSmooth(true);
    this.setCache(true);
  }

  public String getTurtleImageFilename() {
    String fullUrl = myTurtleImage.getUrl();
    return fullUrl.substring(fullUrl.lastIndexOf('/') + 1);
  }

  @Override
  public Consumer<String> updateAction() {
    return this::changeTurtleImage;
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
