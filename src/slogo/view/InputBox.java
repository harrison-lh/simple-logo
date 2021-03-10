package slogo.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;

public class InputBox extends BorderPane {

  public static final int BOTTOM_HEIGHT = 180;

  private final TextArea myInputBoxArea;
  private final Button myInputButton;

  public InputBox(EventHandler<ActionEvent> eventHandler) {
    this.setId("InputBox");
    myInputBoxArea = new TextArea();
    myInputBoxArea.setId("InputBoxArea");
    myInputBoxArea.setPrefHeight(BOTTOM_HEIGHT);

    myInputButton = new Button("Go");
    myInputButton.setId("InputButton");
    myInputButton.setOnAction(eventHandler);
    myInputButton.prefWidthProperty().bind(this.widthProperty());
    this.setTop(myInputBoxArea);
    this.setBottom(myInputButton);
  }
}
