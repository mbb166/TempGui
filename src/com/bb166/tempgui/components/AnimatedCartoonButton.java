package com.bb166.tempgui.components;

import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class AnimatedCartoonButton extends AbstractAnimatedCartoonComponent{
    private Text label;

    private int verticalSpace = 16;
    private int horizontalSpace = 40;

    public AnimatedCartoonButton(int x, int y) {
        super(x,y);
        label = new Text("Button");
        label.setFill(Paint.valueOf("WHITE"));
        label.setFont(new Font("Arial Black", 20));
        Bounds bounds = label.getLayoutBounds();
        super.setWidth((int)(bounds.getWidth()+horizontalSpace/2));
        super.setHeight((int)(bounds.getHeight()+verticalSpace/2));

        super.setOnMouseEntered(event -> super.startExpensionAnimation());
        super.setOnMouseExited(event -> super.startDecreasingAnimation());

        label.setOnMouseEntered(event -> super.startExpensionAnimation());
        label.setOnMouseExited(event -> super.startDecreasingAnimation());
        label.setX(x + horizontalSpace / 2);
        label.setY(y + verticalSpace / 2);

        super
                .getPane()
                .getChildren()
                .add(label);
}

    public String getLabel() {
        return label.getText();
    }

    public void setLabel(String label) {
        this.label.setText(label);
        Bounds bounds = this.label.getLayoutBounds();
        super.setWidth((int)(bounds.getWidth()+horizontalSpace));
        this.label.setX(super.getPane().getLayoutX()+horizontalSpace/2);
        this.label.setY(super.getPane().getLayoutY()+verticalSpace/2);
    }

    public void setOnMousePressed(EventHandler<? super MouseEvent> ev){
        super.setOnMousePressed(ev);
        label.setOnMousePressed(ev);
    }

    public void setOnMouseReleased(EventHandler<? super MouseEvent> ev){
        super.setOnMouseReleased(ev);
        label.setOnMouseReleased(ev);
    }

    public void setOnMouseClicked(EventHandler<? super MouseEvent> ev){
        super.setOnMouseClicked(ev);
        label.setOnMouseClicked(ev);
    }
}