package slogo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.model.Turtle;
import util.DukeApplicationTest;

public class TurtleTests extends DukeApplicationTest {

  private static final double X_AXIS_ANGLE = 0;
  private static final double MOVE_DISTANCE = 100;
  private static final double DEFAULT_X_POSITION = 10;
  private static final double DEFAULT_Y_POSITION = -10;
  private static final double ROTATE_ANGLE = 120;
  private Turtle turtle;

  @BeforeEach
  public void setupTurtle() {
    turtle = new Turtle();
    turtle.setX(DEFAULT_X_POSITION);
    turtle.setY(DEFAULT_Y_POSITION);
    turtle.setHeading(X_AXIS_ANGLE);
  }

  @Test
  public void testInitialPosition() {
    assertEquals(turtle.getX(), DEFAULT_X_POSITION);
    assertEquals(turtle.getY(), DEFAULT_Y_POSITION);
    assertEquals(turtle.getHeading(), X_AXIS_ANGLE);
  }

  @Test
  public void testTurtleForward() {
    turtle.setHeading(X_AXIS_ANGLE);
    turtle.forward(MOVE_DISTANCE);
    assertEquals(DEFAULT_X_POSITION + MOVE_DISTANCE, turtle.getX());
    assertEquals(DEFAULT_Y_POSITION, turtle.getY());
  }

  @Test
  public void testRightRotate() {
    turtle.right(5 * ROTATE_ANGLE);
    assertEquals(ROTATE_ANGLE, turtle.getHeading());
  }

  @Test
  public void testTurtleForwardAngle() {
    turtle.setHeading(ROTATE_ANGLE);
    turtle.forward(MOVE_DISTANCE);
    double expectedX = DEFAULT_X_POSITION + MOVE_DISTANCE * Math.cos(Math.toRadians(ROTATE_ANGLE));
    double expectedY = DEFAULT_Y_POSITION + MOVE_DISTANCE * Math.sin(Math.toRadians(ROTATE_ANGLE));
    assertEquals(expectedX, turtle.getX());
    assertEquals(expectedY, turtle.getY());
  }

}
