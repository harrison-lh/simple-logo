package slogo;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import slogo.controller.Controller;
import slogo.view.MainView;

/**
 * Feel free to completely change this code or delete it entirely. 
 */
public class Main extends Application {

    public static final int WIDTH = 960;
    public static final int HEIGHT = 720;
    public static final int MIN_WIDTH = 960;
    public static final int MIN_HEIGHT = 720;

    /**
     * A method to test (and a joke :).
     */
    public double getVersion () {
        return 0.001;
    }

    /**
     * Start of the program.
     */
    public static void main (String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        Controller controller = new Controller();
        MainView mainView = controller.getMainView();
        Scene scene = new Scene(mainView, WIDTH, HEIGHT);
        scene.getStylesheets().add("slogo/view/stylesheet.css");
        stage.setScene(scene);
        stage.setMinWidth(MIN_WIDTH);
        stage.setMinHeight(MIN_HEIGHT);
        stage.widthProperty().addListener((obs, oldVal, newVal) -> mainView.resizeElements());
        stage.heightProperty().addListener((obs, oldVal, newVal) -> mainView.resizeElements());
        stage.show();
    }
}
