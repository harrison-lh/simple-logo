package slogo.model;

import java.util.HashMap;
import java.util.Map;


public class Variables<E>{

  private Map<String, E> varMap;

  public Variables(){
    varMap = new HashMap<>();
  }

  public E getValue(String key){
    return varMap.get(key);
  }

  public void setValue(String key, E value){
    varMap.put(key, value);
  }




}
