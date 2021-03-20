package slogo.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Variables {

  private Map<String, Double> varMap;
  private PropertyChangeListener variablesListener;

  public Variables(PropertyChangeListener variablesListener) {
    varMap = new HashMap<>();
    this.variablesListener = variablesListener;
  }

  public double getValue(String key) {
    return varMap.get(key);
  }

  public void setValue(String key, double value) {
    Variable newVariable = new Variable(key, value);
    if (varMap.containsKey(key)) {
      Variable prevVar = new Variable(key, varMap.get(key));
      variablesListener
          .propertyChange(new PropertyChangeEvent(this, "UPDATE", prevVar, newVariable));
    } else {
      variablesListener
          .propertyChange(new PropertyChangeEvent(this, "ADD", newVariable, newVariable));
    }
    varMap.put(key, value);
  }

  public void removeValue(String key){
    varMap.remove(key);
  }

  public boolean containsKey(String key) {
    return varMap.containsKey(key);
  }

}
