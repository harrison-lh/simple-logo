package slogo.model;

import java.util.HashMap;
import java.util.Map;
import javafx.beans.property.MapProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

public class Variables {

  private Map<String, Double> varMap;
  private final MapProperty<String, Double> mapProperty;

  public Variables() {
    varMap = new HashMap<>();
    mapProperty = new SimpleMapProperty<>(FXCollections.observableMap(varMap));
  }

  public MapProperty<String, Double> getMapProperty() {
    return mapProperty;
  }

  public double getValue(String key) {
    return varMap.get(key);
  }

  public void setValue(String key, double value) {
    varMap.put(key, value);
    mapProperty.setValue(FXCollections.observableMap(varMap));
  }

  public void removeValue(String key){
    varMap.remove(key);
  }

  public boolean containsKey(String key) {
    return varMap.containsKey(key);
  }

  public Map<String, Double> getVarMap() {
    return varMap;
  }

  public void setVarMap(Map<String, Double> varMap) {
    this.varMap = varMap;
  }
}
