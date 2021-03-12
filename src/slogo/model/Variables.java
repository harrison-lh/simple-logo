package slogo.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;

public class Variables {

  private Map<String, Variable> varMap;
  private PropertyChangeListener variablesListener;

  public Variables(PropertyChangeListener variablesListener) {
    varMap = new HashMap<>();
    this.variablesListener = variablesListener;
  }

  public double getValue(String key) {
    return varMap.get(key).getValue();
  }

  public void setValue(String key, double value) {
    if (varMap.containsKey(key)) {
      Variable prevVar = new Variable(varMap.get(key));
      varMap.get(key).setValue(value);
      variablesListener
          .propertyChange(new PropertyChangeEvent(this, "UPDATE", prevVar, varMap.get(key)));
    } else {
      varMap.put(key, new Variable(key, value));
      variablesListener
          .propertyChange(new PropertyChangeEvent(this, "ADD", varMap.get(key), varMap.get(key)));
    }
  }

  public boolean containsKey(String key) {
    return varMap.containsKey(key);
  }

}
