package slogo;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import slogo.view.MainView;

/**
 * Feel free to completely change this code or delete it entirely. 
 */
public class Main extends Application {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

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
        MainView mainView = new MainView();
        Scene scene = new Scene(mainView, WIDTH, HEIGHT);
        scene.getStylesheets().add("slogo/stylesheet.css");
        stage.setScene(scene);
        stage.show();
    }
}
