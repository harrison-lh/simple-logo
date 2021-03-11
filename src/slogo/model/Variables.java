package slogo.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;

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
    if (varMap.containsKey(key)) {
      variablesListener
          .propertyChange(new PropertyChangeEvent(this, "UPDATE", varMap.get(key), value));
    } else {
      variablesListener
          .propertyChange(new PropertyChangeEvent(this, "ADD", 0, value));
    }
    varMap.put(key, value);
  }

  public boolean containsKey(String key) {
    return varMap.containsKey(key);
  }

}
