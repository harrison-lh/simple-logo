package slogo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.controller.Parser;
import slogo.controller.TurtleController;
import slogo.model.GridCoordinates;
import slogo.model.Turtle;
import slogo.view.JavaFXPen;

/**
 * A testing suite for the Parser!
 *
 * @author Marc Chmielewski
 */
public class ParserTests {
  private Turtle turtle;
  private TurtleController controller;
  private Parser parser;

  @BeforeEach
  public void setup() {
    turtle = new Turtle(new GridCoordinates(), new JavaFXPen(Color.BLACK, new ImageView()));
    controller = new TurtleController(turtle);
    parser = new Parser(controller, "English");
  }

  @Test
  public void commentTest() {
    double initX = turtle.getX();
    parser.parseCommandString("# This is a comment");
    controller.setIsAllowedToExecute(true);
    controller.runCommands();
    assertEquals(turtle.getX(), initX);
  }

  @Test
  public void forward50Test() {
    double initY = turtle.getY();
    parser.parseCommandString("fd 50");
    controller.setIsAllowedToExecute(true);
    controller.runCommands();
    assertEquals(turtle.getY(), initY + 50);
  }

  @Test
  public void forwardAdditionTest() {
    double initY = turtle.getY();
    parser.parseCommandString("fd sum 50 50");
    controller.setIsAllowedToExecute(true);
    controller.runCommands();
    assertEquals(turtle.getY(), initY + 100);
  }

  @Test
  public void hardForwardAdditionTest() {
    double initY = turtle.getY();
    parser.parseCommandString("fd sum sum sum sum 10 20 30 5 5");
    controller.setIsAllowedToExecute(true);
    controller.runCommands();
    assertEquals(turtle.getY(), initY + 70);
  }

  @Test
  public void alternativeHardForwardAdditionTest() {
    double initY = turtle.getY();
    parser.parseCommandString("fd sum 10 sum 10 sum 10 sum 20 20");
    controller.setIsAllowedToExecute(true);
    controller.runCommands();
    assertEquals(turtle.getY(), initY + 70);
  }

  @Test
  public void hardForwardAdditionTestEnEspanol() {
    parser.setSyntaxLang("Spanish");
    double initY = turtle.getY();
    parser.parseCommandString("ava + + + + 10 20 30 5 5");
    controller.setIsAllowedToExecute(true);
    controller.runCommands();
    assertEquals(turtle.getY(), initY + 70);
  }

  @Test
  public void forwardThenReverse() {
    double initY = turtle.getY();
    parser.parseCommandString("fd 50 fd -50");
    controller.setIsAllowedToExecute(true);
    controller.runCommands();
    assertEquals(turtle.getY(), initY);
  }

  @Test
  public void fdCos90ThenSin0ThenTan0ThenATan0() {
    double initY = turtle.getY();
    parser.parseCommandString("fd cos 90 fd sin 0 fd tan 0 atan 0");
    controller.setIsAllowedToExecute(true);
    controller.runCommands();
    assertEquals(turtle.getY(), initY, 0.01);
  }

  @Test
  public void funWithMath() {
    double initY = turtle.getY();
    parser.parseCommandString("fd - 10 10 fd * 50 0 fd / 0 50 fd % 50 50");
    controller.setIsAllowedToExecute(true);
    controller.runCommands();
    assertEquals(turtle.getY(), initY);
  }

  @Test
  public void drawSquare() {
    controller.setIsAllowedToExecute(true);
    double initY = turtle.getY();
    double initX = turtle.getX();
    parser.parseCommandString("fd 50 rt 90");
    controller.runCommands();
    assertEquals(turtle.getY(), initY + 50, 0.01);
    assertEquals(turtle.getX(), initX, 0.01);
    assertEquals(turtle.getHeading(), 0, 0.01);

    initY = turtle.getY();
    initX = turtle.getX();
    parser.parseCommandString("fd 50 rt 90");
    controller.runCommands();
    assertEquals(turtle.getY(), initY, 0.01);
    assertEquals(turtle.getX(), initX + 50, 0.01);
    assertEquals(turtle.getHeading(), 270, 0.01);

    initY = turtle.getY();
    initX = turtle.getX();
    parser.parseCommandString("fd 50 rt 90");
    controller.runCommands();
    assertEquals(turtle.getY(), initY - 50, 0.01);
    assertEquals(turtle.getX(), initX, 0.01);
    assertEquals(turtle.getHeading(),  180, 0.01);

    initY = turtle.getY();
    initX = turtle.getX();
    parser.parseCommandString("fd 50 rt 90");
    controller.runCommands();
    assertEquals(turtle.getY(), initY, 0.01);
    assertEquals(turtle.getX(), initX - 50, 0.01);
    assertEquals(turtle.getHeading(), 90, 0.01);
  }

  @Test
  public void testRandom() {
    double initY = turtle.getY();
    parser.parseCommandString("fd random 3");
    controller.setIsAllowedToExecute(true);
    controller.runCommands();
    assertEquals(turtle.getY(), initY, 3);
  }

  @Test
  public void penStatusTests() {
    controller.setIsAllowedToExecute(true);

    parser.parseCommandString("pu");
    controller.runCommands();
    assertEquals(turtle.isPenActive(), false);

    parser.parseCommandString("pd");
    controller.runCommands();
    assertEquals(turtle.isPenActive(), true);
  }

  @Test
  public void testMinus() {
    double initY = turtle.getY();
    parser.parseCommandString("fd ~ 50");
    controller.setIsAllowedToExecute(true);
    controller.runCommands();
    assertEquals(turtle.getY(), initY - 50);
  }

  @Test
  public void testXYCoordinate() {
    double initY = turtle.getY();
    parser.parseCommandString("fd ~ 50");
    controller.setIsAllowedToExecute(true);
    controller.runCommands();
    assertEquals(turtle.getY(), initY - 50);

    parser.parseCommandString("fd xcor");
    controller.runCommands();
    assertEquals(turtle.getY(), initY - 50);

    parser.parseCommandString("fd ycor");
    controller.runCommands();
    assertEquals(turtle.getY(), initY - 100);
  }
}
