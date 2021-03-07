package slogo.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.shape.Circle;

public class MenuBar extends HBox {

  private final BackgroundSelector myBackgroundSelector;
  private final GridSelector myGridSelector;
  private final TurtleSelector myTurtleSelector;
  private final PenSelector myPenSelector;
  private final LanguageSelector myLanguageSelector;

  public MenuBar() {
    this.setId("MenuBar");
    this.getStyleClass().add("box");
    this.setAlignment(Pos.CENTER_LEFT);
    myBackgroundSelector = new BackgroundSelector();
    myGridSelector = new GridSelector();
    myTurtleSelector = new TurtleSelector();
    myPenSelector = new PenSelector();
    myLanguageSelector = new LanguageSelector();

    Button infoButton = new Button("?");
    infoButton.setShape(new Circle(2));
    infoButton.setOnAction(e -> openCommandsWindow());

    // Used to align info button to the right
    Pane spacer = new Pane();
    HBox.setHgrow(spacer, Priority.ALWAYS);

    this.getChildren()
        .addAll(myBackgroundSelector, myGridSelector, myTurtleSelector, myPenSelector,
            myLanguageSelector, spacer, infoButton);
  }

  private void openCommandsWindow() {
    System.out.println("Opening commands window");
  }

  public BackgroundSelector getBackgroundSelector() {
    return myBackgroundSelector;
  }

  public GridSelector getGridSelector() {
    return myGridSelector;
  }

  public TurtleSelector getTurtleSelector() {
    return myTurtleSelector;
  }

  public PenSelector getPenSelector() {
    return myPenSelector;
  }

  public LanguageSelector getLanguageSelector() {
    return myLanguageSelector;
  }
}
