package slogo.view.menubar;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Help window that pops up when the user clicks the help button
 * Contains a list of all commands and descriptions of each
 *
 * @author David Li
 */
public class HelpWindow extends Stage {

  public static final int WIDTH = 540;
  public static final int HEIGHT = 600;
  public static final String REFERENCE_PATH = "./src/resources/reference/";

  private ScrollPane myContent;
  private VBox commandLinks;

  /**
   * Main constructor
   */
  public HelpWindow() {
    this.setTitle("Commands");
    myContent = new ScrollPane();
    myContent.setFitToWidth(true);
    myContent.setId("HelpWindow");
    createCommandLinks();
    Scene scene = new Scene(myContent, HelpWindow.WIDTH, HelpWindow.HEIGHT);
    scene.getStylesheets().add("slogo/view/stylesheet.css");
    this.setScene(scene);
  }

  private void createCommandLinks() {
    commandLinks = new VBox();
    File references = new File(REFERENCE_PATH);
    try {
      String[] commandsList = references.list().clone();
      Arrays.sort(commandsList);
      for (String command : commandsList) {
        Hyperlink link = new Hyperlink(command.toUpperCase());
        link.getStyleClass().add("command-link");
        link.setOnAction(e -> openCommandPage(command));
        commandLinks.getChildren().add(link);
      }
    } catch (NullPointerException e) {
      throw new NullPointerException("INVALID REFERENCE PATH");
    }
    myContent.setContent(commandLinks);
  }

  private void openCommandPage(String command) {
    VBox commandPage = new VBox();
    StackPane header = new StackPane();
    Label title = new Label(command.toUpperCase());
    title.getStyleClass().add("command-title");

    Button backButton = new Button("Back");
    backButton.setOnAction(e -> myContent.setContent(commandLinks));
    header.getChildren().addAll(backButton, title);
    StackPane.setAlignment(backButton, Pos.CENTER_LEFT);

    Label commandInfo = parseCommandInfo(command);

    commandPage.getChildren().addAll(header, commandInfo);
    commandPage.setSpacing(8);
    myContent.setContent(commandPage);
  }

  private Label parseCommandInfo(String command) {
    Label commandLabel = new Label();
    commandLabel.getStyleClass().add("command-info");
    try {
      String content = Files
          .readString(Paths.get(REFERENCE_PATH + command), StandardCharsets.US_ASCII);
      content += " ";
      commandLabel.setText(content);
    } catch (IOException e) {
      e.printStackTrace();
    }

    return commandLabel;
  }
}
