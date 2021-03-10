package slogo.model;

import java.util.HashMap;
import java.util.Map;


public class Variables {

  private Map<String, Double> varMap;

  public Variables(){
    varMap = new HashMap<>();
  }

  public double getValue(String key){
    return varMap.get(key);
  }

  public void setValue(String key, double value){
    varMap.put(key, value);
  }

  public boolean containsKey(String key) {
    return varMap.containsKey(key);
  }

}
