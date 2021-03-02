/**
 * Lists all the variables defined by the user in a variables box
 */
interface VariablesBox {

  /**
   * Add a variable to the variable box
   * @param variable Variable to be added
   */
  public void addVariable(Variable variable);

  /**
   * Update a variable to the variable box
   * @param variable Variable to be updated
   */
  public void updateVariable(Variable variable);

  /**
   * Remove a variable to the variable box
   * @param variable Variable to be removed
   */
  public void removeVariable(Variable variable);
}