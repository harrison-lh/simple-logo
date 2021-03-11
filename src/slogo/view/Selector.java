package slogo.view;

import java.util.function.Consumer;

/**
 * Interface for selector items (combo box, color picker, etc.) that
 * passes a property of the selector to the target to update
 * @param <T> The type of property that is passed
 *
 * @author David Li
 */
public interface Selector<T> {

  /**
   * Sets the action event that occurs when a property is changed,
   * and passes the selected property to the consumer
   * Ex: this.setOnAction(e -> response.accept(this.getValue()));
   * @param response
   */
  void setUpdateAction(Consumer<T> response);
}
