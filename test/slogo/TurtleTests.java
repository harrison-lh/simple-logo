package slogo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.model.GridCoordinates;
import slogo.model.ModelPen;
import slogo.model.Turtle;
import util.DukeApplicationTest;

public class TurtleTests extends DukeApplicationTest {


  private static final double X_AXIS_ANGLE = 0;
  private static final double MOVE_DISTANCE = 100;
  private static final double DEFAULT_X_POSITION = 0;
  private static final double DEFAULT_Y_POSITION = 0;
  private static final double DEFAULT_HEADING = 90;
  private static final double ROTATE_ANGLE = 120;
  private Turtle turtle;

  @BeforeEach
  public void setupTurtle() {
    turtle = new Turtle(new GridCoordinates(), new ModelPen(Color.BLACK));
  }

  @Test
  public void testInitialPosition() {
    assertEquals(DEFAULT_X_POSITION, turtle.getX());
    assertEquals(DEFAULT_Y_POSITION,turtle.getY());
    assertEquals(DEFAULT_HEADING, turtle.getHeading());
  }

  @Test
  public void testTurtleForward() {
    //set turtle facing right along the x-axis
    double heading = 0;
    turtle.setHeading(heading);
    //move the turtle 100 pixels along the x-axis
    double moveDistance = 100;
    turtle.forward(moveDistance);
    assertEquals(DEFAULT_X_POSITION + moveDistance, turtle.getX());
    assertEquals(DEFAULT_Y_POSITION, turtle.getY());
  }

  @Test
  public void testRightRotate() {
    double amountToTurn = 45;
    turtle.right(amountToTurn);
    assertEquals(45, turtle.getHeading());
  }

  @Test
  public void testExcessiveRightRotate() {
    double amountToTurn = 180;
    turtle.right(amountToTurn);
    assertEquals(270, turtle.getHeading());
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
