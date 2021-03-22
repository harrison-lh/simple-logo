package slogo.view.menubar;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.function.Consumer;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import util.LibraryManager;

public class FileButtons extends HBox {

  private final static String LIBRARIES_PATH = "resources/libraries/";
  private Consumer<String> commandConsumer;
  private Map<String, Double> variables;

  public FileButtons() {
    VBox variablesButtons = new VBox();
    Button saveVariablesButton = new Button("Save");
    Button loadVariablesButton = new Button("Load");
    saveVariablesButton.setOnAction(e -> saveVariables());
    loadVariablesButton.setOnAction(e -> loadVariables());
    variablesButtons.getChildren().addAll(saveVariablesButton, loadVariablesButton);

    this.getChildren().addAll(variablesButtons);
  }

  public void setVariablesMap(Map<String, Double> map) {
    variables = map;
  }

  public void setCommandConsumer(Consumer<String> consumer) {
    commandConsumer = consumer;
  }

  private void loadVariables() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setInitialDirectory(new File(LIBRARIES_PATH));
    File file = fileChooser.showOpenDialog(null);
    try {
      String command = LibraryManager.loadVariables(file);
      commandConsumer.accept(command);
    } catch (IOException exception) {
      Alert alert = new Alert(AlertType.ERROR, "Invalid file");
      alert.showAndWait();
    }
  }

  private void saveVariables() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setInitialDirectory(new File(LIBRARIES_PATH));
    File file = fileChooser.showOpenDialog(null);
    try {
      LibraryManager.saveVariables(file, variables);
    } catch (IOException exception) {
      Alert alert = new Alert(AlertType.ERROR, "Invalid file");
      alert.showAndWait();
    }
  }

}
