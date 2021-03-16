package slogo.view.info;

public class VariablesTab extends InfoTab {

  private final VariablesBox myVariablesBox;

  public VariablesTab() {
    super("Variables");
    myVariablesBox = new VariablesBox();
    this.setContent(myVariablesBox);
  }

  public VariablesBox getVariablesBox() {
    return myVariablesBox;
  }
}
