package slogo.model;

/**
 * Immutable class that holds a variable name and value
 *
 * @author David Li
 */
public final class Variable {

  private final String name;
  private final double value;

  public Variable(String name, double value) {
    this.name = name;
    this.value = value;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return name.substring(1) + " = " + value; // Remove colon
  }
}
