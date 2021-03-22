package slogo.view.menubar;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.function.Consumer;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import util.LibraryManager;

public class FileButtons extends HBox {

  private final static String LIBRARIES_PATH = "resources/libraries/";
  private Consumer<String> commandConsumer;
  private Map<String, Double> variables;
  private Map<String, String> commands;
  private FileChooser fileChooser;

  public FileButtons() {
    VBox variablesButtons = new VBox();
    Button saveVariablesButton = new Button("Save Vars");
    Button loadVariablesButton = new Button("Load Vars");
    saveVariablesButton.setOnAction(e -> saveVariables());
    loadVariablesButton.setOnAction(e -> loadVariables());
    variablesButtons.getChildren().addAll(saveVariablesButton, loadVariablesButton);

    VBox commandsButtons = new VBox();
    Button saveCommandsButton = new Button("Save Comms");
    Button loadCommandsButton = new Button("Load Comms");
    saveCommandsButton.setOnAction(e -> saveCommands());
    loadCommandsButton.setOnAction(e -> loadCommands());
    commandsButtons.getChildren().addAll(saveCommandsButton, loadCommandsButton);

    this.setSpacing(10);
    this.getChildren().addAll(variablesButtons, commandsButtons);

    fileChooser = new FileChooser();
    fileChooser.setInitialDirectory(new File(LIBRARIES_PATH));
  }

  public void setVariablesMap(Map<String, Double> map) {
    variables = map;
  }

  public void setCommands(Map<String, String> map) {
    commands = map;
  }

  public void setCommandConsumer(Consumer<String> consumer) {
    commandConsumer = consumer;
  }

  private void loadCommands() {
    File file = fileChooser.showOpenDialog(null);
    try {
      String command = LibraryManager.loadVariables(file);
      commandConsumer.accept(command);
    } catch (IOException exception) {
      Alert alert = new Alert(AlertType.ERROR, "Invalid file");
      alert.showAndWait();
    }
  }

  private void saveCommands() {
    File file = fileChooser.showOpenDialog(null);
    try {
      LibraryManager.saveUserCommands(file, commands);
    } catch (IOException exception) {
      Alert alert = new Alert(AlertType.ERROR, "Invalid file");
      alert.showAndWait();
    }
  }

  private void loadVariables() {
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
    File file = fileChooser.showOpenDialog(null);
    try {
      LibraryManager.saveVariables(file, variables);
    } catch (IOException exception) {
      Alert alert = new Alert(AlertType.ERROR, "Invalid file");
      alert.showAndWait();
    }
  }

}
