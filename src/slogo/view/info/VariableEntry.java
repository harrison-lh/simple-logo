package slogo.view.info;

import java.util.function.Consumer;
import slogo.view.ClickableEntry;

public class VariableEntry extends ClickableEntry<String> {

  private final String myName;
  private double myValue;

  public VariableEntry(String name, double value, Consumer<String> consumer) {
    super(consumer);
    myName = name;
    myValue = value;
  }

  public void updateVariable(double value) {
    myValue = value;
    setText(displayText());
  }

  @Override
  protected void onClick(Consumer<String> consumer) {
    System.out.println("set variable");
  }

  private String displayText() {
    return myName.substring(1) + " = " + myValue; // Remove colon
  }
}
