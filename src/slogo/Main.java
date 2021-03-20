package slogo;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import slogo.view.Workspaces;

/**
 * Starting point of the application, creates the Controller and sets up the MainView in the JavaFX
 * stage
 *
 * @author David Li
 */
public class Main extends Application {

  public static final int MIN_WIDTH = 1260;
  public static final int MIN_HEIGHT = 840;

  /**
   * Start of the program.
   */
  public static void main(String[] args) {
    launch(args);
  }

  /**
   * Create Controller object, and assigns the MainView to the scene
   */
  @Override
  public void start(Stage stage) {
    Scene scene = new Scene(new Workspaces(), MIN_WIDTH, MIN_HEIGHT);
    scene.getStylesheets().add("slogo/view/stylesheet.css");
    stage.setScene(scene);
    stage.setMinWidth(MIN_WIDTH);
    stage.setMinHeight(MIN_HEIGHT);
    stage.setTitle("SLogo");
    stage.show();
  }
}
