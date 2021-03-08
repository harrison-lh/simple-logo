package slogo.view.canvas;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TurtleView extends ImageView {

  public TurtleView() {
    this.setId("TurtleView");
    Image image = new Image("resources/images/turtle-0.png");
    this.setImage(image);
    this.setFitWidth(40);
    this.setPreserveRatio(true);
    this.setSmooth(true);
    this.setCache(true);
  }

}
