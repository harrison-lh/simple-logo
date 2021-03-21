package slogo.view.menubar;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import util.LibraryManager;

public class FileButtons extends HBox {

  private final static String LIBRARIES_PATH = "resources/libraries/";

  public FileButtons() {
    VBox variablesButtons = new VBox();
    Button saveVariablesButton = new Button("Save");
    Button loadVariablesButton = new Button("Load");
    saveVariablesButton.setOnAction(e -> saveVariables());
    loadVariablesButton.setOnAction(e -> loadVariables());
    variablesButtons.getChildren().addAll(saveVariablesButton, loadVariablesButton);

    this.getChildren().addAll(variablesButtons);
  }

  private void loadVariables() {
    TextInputDialog inputDialog = new TextInputDialog();
    inputDialog.setHeaderText(null);
    inputDialog.setGraphic(null);
    inputDialog.setContentText("File Name: ");
    inputDialog.showAndWait().ifPresent(result -> {
      System.out.println("load variables to " + LIBRARIES_PATH + result);
    });
  }

  private void saveVariables() {
    TextInputDialog inputDialog = new TextInputDialog();
    inputDialog.setHeaderText(null);
    inputDialog.setGraphic(null);
    inputDialog.setContentText("File Name: ");
    inputDialog.showAndWait().ifPresent(result -> {
      System.out.println("save variables to " + LIBRARIES_PATH + result);
    });
  }

}
