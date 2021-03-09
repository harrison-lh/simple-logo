/**
 * Displays information to the user somewhere in the window
 */
interface DisplayBox<E> {

  /**
   * Add a element to the display box
   * @param element Element to be added
   */
  public void addElement(E element);

  /**
   * Update a element in the display box
   * @param element Element to be updated
   */
  public void updateElement(E element);

  /**
   * Remove a element in the display box
   * @param element Element to be removed
   */
  public void removeElement(E element);
}