package slogo.view;

import java.util.function.Consumer;

/**
 * Interface for component that gets updated when a selector selects a new property.
 *
 * @param <T> The type of property that gets passed
 * @author David Li
 */
public interface SelectorTarget<T> {

  /**
   * @return A lambda expression that represents updating the target given some property Ex: return
   * property -> this.setProperty(property));
   */
  Consumer<T> updateAction();
}
