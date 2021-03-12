package slogo.view;

import java.util.Objects;
import javafx.scene.control.Label;
import slogo.model.Variable;

public class VariableText extends Label {

  private Variable myVariable;

  public VariableText(Variable variable) {
    super(variable.toString());
    myVariable = variable;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VariableText that = (VariableText) o;
    return Objects.equals(myVariable, that.myVariable);
  }

  @Override
  public int hashCode() {
    return Objects.hash(myVariable);
  }
}
