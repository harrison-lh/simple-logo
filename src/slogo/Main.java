package slogo;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import slogo.controller.Controller;
import slogo.view.MainView;

/**
 * Starting point of the application, creates the Controller and sets up the MainView in the JavaFX
 * stage
 *
 * @author David Li
 */
public class Main extends Application {

  public static final int MIN_WIDTH = 960;
  public static final int MIN_HEIGHT = 720;

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
    Controller controller = new Controller();
    MainView mainView = controller.getMainView();
    Scene scene = new Scene(mainView, MIN_WIDTH, MIN_HEIGHT);
    scene.getStylesheets().add("slogo/view/stylesheet.css");
    stage.setScene(scene);
    stage.setMinWidth(MIN_WIDTH);
    stage.setMinHeight(MIN_HEIGHT);
    stage.widthProperty().addListener((obs, oldVal, newVal) -> mainView.resizeElements());
    stage.heightProperty().addListener((obs, oldVal, newVal) -> mainView.resizeElements());
    stage.show();
  }
}
