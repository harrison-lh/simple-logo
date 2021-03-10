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
    parser.createParseTree("# This is a comment");
    controller.setIsAllowedToExecute(true);
    controller.runCommands();
    assertEquals(turtle.getX(), initX);
  }

  @Test
  public void forward50Test() {
    double initY = turtle.getY();
    parser.createParseTree("fd 50");
    controller.setIsAllowedToExecute(true);
    controller.runCommands();
    assertEquals(turtle.getY(), initY + 50);
  }


}
