package com.bb166.tempgui.components;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.util.concurrent.ScheduledExecutorService;

abstract class CartoonNode {

    private StackPane stackPane;

    protected CartoonNode(){
        stackPane = new StackPane();
    }

    protected void add(Node node) {
        stackPane.getChildren().add(node);
    }

    public void setWidth(int width) {
        stackPane.setMaxWidth(width);
    }

    public void setHeight(int height) {
        stackPane.setMaxHeight(height);
    }

    public void setX(int x) {
        stackPane.setLayoutX(x);
    }

    public void setY(int y) {
        stackPane.setLayoutY(y);
    }

    public int getX(){
        return (int) stackPane.getLayoutX();
    }

    public int getY() {
        return (int) stackPane.getLayoutY();
    }

    Pane getPane() {
        return stackPane;
    }
}
