package slogo.controller;

import javafx.event.Event;
import javafx.event.EventType;

public class ClearScreenEvent extends Event {

  public ClearScreenEvent(Object source) {
    super(new EventType<>("ClearScreen" + source.toString()));
  }
}