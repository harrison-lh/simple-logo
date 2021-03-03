/**
 * An interface that defines the common methods between objects that can draw on screen.
 * <p>
 * An object that implements Pen will implement methods that allow it to draw on the canvas of the
 * view.
 *
 * @author Marc Chmielewski
 */
public interface Pen {

  /**
   * Tell the object that is implementing the interface to stamp with whatever "drawing implement"
   * it currently posseses.
   * <p>
   * Ex. JavaFXPen will implement Pen, and depending upon current conditions (pen-up vs. pen-down,
   * current Color, "trace" vs. "stamp" mode, etc.) JavaFXPen will stamp accordingly.
   */
  public void stamp();
}