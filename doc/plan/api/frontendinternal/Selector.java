/**
 * Select settings of a component of the visualization (background color, pen color, etc)
 */
interface Selector {

  /**
   * When the user selects a new option, change the appropriate property in the view
   */
  public void selectNewOption();
}