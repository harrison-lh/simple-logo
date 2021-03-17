package slogo.view.info;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import slogo.model.Variable;

/**
 * Display box for variables
 */
public class TurtlesBox extends ScrollPane {

  private final VBox myContents;
  /**
   * Main constructor
   */
  public TurtlesBox() {
    this.setId("VariablesBox");
    this.getStyleClass().add("info-box");
    myContents = new VBox();
    this.setContent(myContents);
  }
}
