package com.bb166.tempgui.components;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public final class AnimatedCartoonTextField extends AbstractAnimatedCartoonComponent {
    private Line cursor;
    private float cursorOpacity = 1f;
    private boolean cursorAnimationStart = false;
    private boolean disappearance = true;
    private int refresh = 50;
    private boolean focused = false;

    private StringBuilder text;
    private Text textControl;

    private Runnable cursorAnimation = () -> {
        while (cursorAnimationStart) {
            if (disappearance) {
                cursorOpacity -= 0.05f;
                if (cursorOpacity < 0.05f) {
                    cursorOpacity = 0f;
                    disappearance = false;
                }
            } else {
                cursorOpacity += 0.05f;
                if (cursorOpacity > 0.95f) {
                    cursorOpacity = 1f;
                    disappearance = true;
                }
            }
            try {
                Thread.sleep(refresh);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    };

    private EventHandler<? super MouseEvent> focusedEvent = ev -> {
        if (!focused) {
            super.getCartoonComponentGroup().focusedTextComponent(this);
            focused = true;
            super.startExpensionAnimation();
            if (!cursorAnimationStart) {
                cursor.setVisible(true);
                cursorAnimationStart = true;
                new Thread(cursorAnimation).start();
            }
        }
    };

    public AnimatedCartoonTextField(CartoonComponentGroup cartoonComponentGroup,int x, int y, int width, int heigth) {
        super(cartoonComponentGroup,x, y);
        super.setWidth(width);
        super.setHeight(heigth);
        cursor = new Line(x, y, x, y + 23);
        cursor.setTranslateX(-width / 2 + 5);
        cursor.setStroke(Color.web("#1E1E1E"));
        cursor.setVisible(false);

        text = new StringBuilder();
        textControl = new Text(text.toString());
        textControl.setTranslateX(-width / 2);
        textControl.setFill(Paint.valueOf("White"));
        textControl.setFont(new Font("Arial Black", 20));

        super.getPane().getChildren().add(textControl);
        super.getPane().getChildren().add(cursor);

        super.setOnMouseClicked(focusedEvent);
        cursor.setOnMouseClicked(focusedEvent);
        textControl.setOnMouseClicked(focusedEvent);

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                cursor.setOpacity(cursorOpacity);
            }
        }.start();
    }

    void addCharacterToLabel(KeyEvent event) {
        if ((event.getCode().isLetterKey() || event.getCode().isDigitKey()) &&
                textControl.getLayoutBounds().getWidth() + 20 < super.getButton().getWidth()) {

            text.append(event.getText());
            textControl.setText(text.toString());
            cursor.setTranslateX(-Math.ceil(super.getButton().getWidth() / 2d) + textControl.getLayoutBounds().getWidth() + 10);
            textControl.setTranslateX(-Math.round(super.getButton().getWidth() / 2d) + Math.round(textControl.getLayoutBounds().getWidth() / 2d) + 7);

        } else if (event.getCode() == KeyCode.BACK_SPACE && text.length() != 0) {

            text.deleteCharAt(text.length() - 1);
            textControl.setText(text.toString());
            cursor.setTranslateX(-Math.ceil(super.getButton().getWidth() / 2d) + textControl.getLayoutBounds().getWidth() + 10);
            textControl.setTranslateX(-Math.round(super.getButton().getWidth() / 2d) + Math.round(textControl.getLayoutBounds().getWidth() / 2d) + 7);

        }
    }

    void startDecreasingAnimation(){
        super.startDecreasingAnimation();
        cursorOpacity = 1f;
        cursorAnimationStart = false;
        disappearance = true;
        cursor.setVisible(false);
        focused = false;
    }

    void startExpensionAnimation() {
        super.startExpensionAnimation();
        focused = true;
    }

    boolean isFocused() {
        return focused;
    }
}
