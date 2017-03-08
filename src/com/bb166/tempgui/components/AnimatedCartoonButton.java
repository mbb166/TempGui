package com.bb166.tempgui.components;

import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.input.MouseEvent;

import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public final class AnimatedCartoonButton extends AbstractAnimatedCartoonComponent {

    private Text label;

    private int verticalSpace = 16;
    private int horizontalSpace = 40;

    public AnimatedCartoonButton(CartoonComponentGroup cartoonComponentGroup, int x, int y) {
        super(cartoonComponentGroup, x, y);
        label = new Text("Button");
        label.setFill(Paint.valueOf("WHITE"));
        label.setFont(new Font("Arial Black", 20));
        Bounds bounds = label.getLayoutBounds();
        super.setWidth((int) (bounds.getWidth() + horizontalSpace / 2));
        super.setHeight((int) (bounds.getHeight() + verticalSpace / 2));

        super.setOnMouseEntered(event -> super.startExpensionAnimation());
        super.setOnMouseExited(event -> super.startDecreasingAnimation());

        label.setOnMouseEntered(event -> super.startExpensionAnimation());
        label.setOnMouseExited(event -> super.startDecreasingAnimation());

        super
                .getPane()
                .getChildren()
                .add(label);

        super.getButton().setFill(Color.randomizeColor());
    }

    public String getLabel() {
        return label.getText();
    }

    public void setLabel(String label) {
        this.label.setText(label);
        Bounds bounds = this.label.getLayoutBounds();
        super.setWidth((int) (bounds.getWidth() + horizontalSpace));
        this.label.setX(super.getPane().getLayoutX() + horizontalSpace / 2);
        this.label.setY(super.getPane().getLayoutY() + verticalSpace / 2);
    }

    public void setOnMousePressed(EventHandler<? super MouseEvent> ev) {
        EventHandler<? super MouseEvent> unFocusedAction = this.addUnfocusedTextFieldAction(ev);
        super.setOnMousePressed(unFocusedAction);
        label.setOnMousePressed(unFocusedAction);
    }

    public void setOnMouseReleased(EventHandler<? super MouseEvent> ev) {
        EventHandler<? super MouseEvent> unFocusedAction = this.addUnfocusedTextFieldAction(ev);
        super.setOnMouseReleased(unFocusedAction);
        label.setOnMouseReleased(unFocusedAction);
    }

    public void setOnMouseClicked(EventHandler<? super MouseEvent> ev) {
        EventHandler<? super MouseEvent> unFocusedAction = this.addUnfocusedTextFieldAction(ev);
        super.setOnMouseClicked(unFocusedAction);
        label.setOnMouseClicked(unFocusedAction);
    }

    private EventHandler<? super MouseEvent> addUnfocusedTextFieldAction(EventHandler<? super MouseEvent> action) {
        return event -> {
            action.handle(event);
            super.getCartoonComponentGroup().focusedAnotherComponent();
        };
    }
}