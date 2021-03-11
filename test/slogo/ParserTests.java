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
  public void altForwardThenReverse() {
    double initY = turtle.getY();
    parser.parseCommandString("fd 50 bk 50");
    controller.setIsAllowedToExecute(true);
    controller.runCommands();
    assertEquals(turtle.getY(), initY);
  }

  @Test
  public void leftThenRight() {
    double initHeading = turtle.getHeading();
    parser.parseCommandString("rt 90 lt 90");
    controller.setIsAllowedToExecute(true);
    controller.runCommands();
    assertEquals(turtle.getHeading(), initHeading, 0.01);
  }

  @Test
  public void setHeading() {
    parser.parseCommandString("seth 93");
    controller.setIsAllowedToExecute(true);
    controller.runCommands();
    assertEquals(turtle.getHeading(), 93);
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

    double initY = turtle.getY();
    parser.parseCommandString("fd pendown?");
    controller.setIsAllowedToExecute(true);
    controller.runCommands();
    assertEquals(initY + 1, turtle.getY());
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

  @Test
  public void testVariables() {
    double initY = turtle.getY();
    parser.parseCommandString("make :A 50 fd :A");
    controller.setIsAllowedToExecute(true);
    controller.runCommands();
    assertEquals(turtle.getY(), initY + 50);
  }

  @Test
  public void testVariablesHard() {
    double initY = turtle.getY();
    parser.parseCommandString("make :A 50 make :B 50 fd sum :A :B");
    controller.setIsAllowedToExecute(true);
    controller.runCommands();
    assertEquals(turtle.getY(), initY + 100);
  }

  @Test
  public void testVariableResets() {
    double initY = turtle.getY();
    parser.parseCommandString("make :A 50 make :B 50 set :A 25 fd sum :A :B");
    controller.setIsAllowedToExecute(true);
    controller.runCommands();
    assertEquals(turtle.getY(), initY + 75);
  }

  @Test
  public void testUninitialized() {
    double initY = turtle.getY();
    parser.parseCommandString("fd sum :A :B");
    controller.setIsAllowedToExecute(true);
    controller.runCommands();
    assertEquals(turtle.getY(), initY);
  }
  public void testSetTowards() {
    turtle.setX(0);
    turtle.setY(0);
    turtle.setHeading(0);
    parser.parseCommandString("towards 1 1");
    controller.setIsAllowedToExecute(true);
    controller.runCommands();
    assertEquals(45, turtle.getHeading());

    parser.parseCommandString("towards -1 towards 1500 1500");
    controller.setIsAllowedToExecute(true);
    controller.runCommands();
    assertEquals(180, turtle.getHeading());

    parser.parseCommandString("towards towards 1 / 5773502692 10000000000 -150");
    controller.setIsAllowedToExecute(true);
    controller.runCommands();
    assertEquals(315, Math.round(turtle.getHeading()));
  }

  @Test
  public void testSetPosition() {
    parser.parseCommandString("setxy -12 setxy 3 4");
    controller.setIsAllowedToExecute(true);
    controller.runCommands();
    assertEquals(-12, turtle.getX());
    assertEquals(5, turtle.getY());
    parser.parseCommandString("home");
    controller.setIsAllowedToExecute(true);
    controller.runCommands();
    assertEquals(0, turtle.getX());
    assertEquals(0, turtle.getY());
  }

  @Test
  public void testBooleanOperations() {
    double initY = turtle.getY();
    parser.parseCommandString("fd less? 0 1");
    controller.setIsAllowedToExecute(true);
    controller.runCommands();
    assertEquals(initY + 1, turtle.getY());

    initY = turtle.getY();
    parser.parseCommandString("fd greater? 100 -3");
    controller.setIsAllowedToExecute(true);
    controller.runCommands();
    assertEquals(initY + 1, turtle.getY());

    initY = turtle.getY();
    parser.parseCommandString("fd equal? 100 -3");
    controller.setIsAllowedToExecute(true);
    controller.runCommands();
    assertEquals(initY, turtle.getY());

    initY = turtle.getY();
    parser.parseCommandString("fd notequal? 100 -3");
    controller.setIsAllowedToExecute(true);
    controller.runCommands();
    assertEquals(initY + 1, turtle.getY());

    initY = turtle.getY();
    parser.parseCommandString("fd and 100 -3");
    controller.setIsAllowedToExecute(true);
    controller.runCommands();
    assertEquals(initY + 1, turtle.getY());

    initY = turtle.getY();
    parser.parseCommandString("fd and -3 0");
    controller.setIsAllowedToExecute(true);
    controller.runCommands();
    assertEquals(initY, turtle.getY());

    initY = turtle.getY();
    parser.parseCommandString("fd or -3 0");
    controller.setIsAllowedToExecute(true);
    controller.runCommands();
    assertEquals(initY + 1, turtle.getY());

    initY = turtle.getY();
    parser.parseCommandString("fd or 0 0");
    controller.setIsAllowedToExecute(true);
    controller.runCommands();
    assertEquals(initY, turtle.getY());

    initY = turtle.getY();
    parser.parseCommandString("fd not 0");
    controller.setIsAllowedToExecute(true);
    controller.runCommands();
    assertEquals(initY + 1, turtle.getY());

    initY = turtle.getY();
    parser.parseCommandString("fd not 100");
    controller.setIsAllowedToExecute(true);
    controller.runCommands();
    assertEquals(initY, turtle.getY());
  }

  @Test
  public void testShowingTurtle() {
    controller.setIsAllowedToExecute(true);

    parser.parseCommandString("ht");
    controller.runCommands();
    assertEquals(turtle.isVisible(), false);

    parser.parseCommandString("st");
    controller.runCommands();
    assertEquals(turtle.isVisible(), true);

    double initY = turtle.getY();
    parser.parseCommandString("fd showing?");
    controller.setIsAllowedToExecute(true);
    controller.runCommands();
    assertEquals(initY + 1, turtle.getY());
  }

  @Test
  public void testMiscMathOperations() {
    double initY = turtle.getY();
    parser.parseCommandString("fd pow 3 2");
    controller.setIsAllowedToExecute(true);
    controller.runCommands();
    assertEquals(initY + 9, turtle.getY());

    initY = turtle.getY();
    parser.parseCommandString("fd log 100");
    controller.setIsAllowedToExecute(true);
    controller.runCommands();
    assertEquals(initY + Math.log(100), turtle.getY());

    initY = turtle.getY();
    parser.parseCommandString("fd pi");
    controller.setIsAllowedToExecute(true);
    controller.runCommands();
    assertEquals(initY + Math.PI, turtle.getY());
  }

  @Test
  public void testRepeatBasic() {
    double initY = turtle.getY();
    parser.parseCommandString("repeat 2 [ fd 50 ]");
    controller.setIsAllowedToExecute(true);
    controller.runCommands();
    assertEquals(turtle.getY(), initY + 100);
  }

  @Test
  public void testRepeatBasic2() {
    double initY = turtle.getY();
    parser.parseCommandString("repeat 4 [ fd 50 bk 50 ]");
    controller.setIsAllowedToExecute(true);
    controller.runCommands();
    assertEquals(turtle.getY(), initY);
  }

  @Test
  public void testRepeatBasic3() {
    double initY = turtle.getY();
    parser.parseCommandString("repeat 5 [ fd sum 50 50 ]");
    controller.setIsAllowedToExecute(true);
    controller.runCommands();
    assertEquals(turtle.getY(), initY + 500);
  }

  @Test
  public void testRepeatAdvanced() {
    double initY = turtle.getY();
    parser.parseCommandString("repeat 5 [ repeat 2 [ fd 50 ] ]");
    controller.setIsAllowedToExecute(true);
    controller.runCommands();
    assertEquals(turtle.getY(), initY + 500);
  }
}
