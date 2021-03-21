package slogo.view.menubar;

import java.util.function.Consumer;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.ComboBoxBase;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import slogo.view.Selector;

/**
 * Abstract class for menu bar selectors
 * @param <T> Type of variable that gets passed to the consumer
 *
 * @author David Li
 */
public abstract class MenuBarSelector<T> extends VBox implements Selector<T> {

  private final ComboBoxBase<T> myComboBoxBase;
  private Property<T> myGlobalProperty;

  /**
   * Main constructor
   */
  public MenuBarSelector(String title, String id, String comboBoxId, ComboBoxBase<T> comboBoxBase) {
    this.setId(id);
    this.getStyleClass().add("selector");
    Label label = new Label(title);
    myComboBoxBase = comboBoxBase;
    myComboBoxBase.setId(comboBoxId);
    myGlobalProperty = new SimpleObjectProperty<>();

    this.getChildren().addAll(label, myComboBoxBase);
  }

  public void setGlobalProperty(Property<T> property) {
    myGlobalProperty = property;
  }

  /**
   * Passes the selected value to the consumer
   */
  @Override
  public void setUpdateAction(Consumer<T> response) {
    myComboBoxBase.setOnAction(e -> {
      response.accept(myComboBoxBase.getValue());
      myGlobalProperty.setValue(myComboBoxBase.getValue());
    });
  }

  protected ComboBoxBase<T> getComboBoxBase() {
    return myComboBoxBase;
  }
}
