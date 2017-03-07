package com.bb166.tempgui.components;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public final class AnimatedCartoonTextField extends AbstractAnimatedCartoonComponent {
    private Line cursor;
    private float cursorOpacity = 1f;
    private boolean cursorAnimationStart = false;
    private boolean disappearance = true;
    private int refresh = 50;
    private boolean focused = false;

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
        super.getPane().getChildren().add(cursor);

        super.setOnMouseClicked(focusedEvent);
        cursor.setOnMouseClicked(focusedEvent);

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                cursor.setOpacity(cursorOpacity);
            }
        }.start();
    }

    public void startDecreasingAnimation(){
        super.startDecreasingAnimation();
        cursorOpacity = 1f;
        cursorAnimationStart = false;
        disappearance = true;
        cursor.setVisible(false);
        focused = false;
    }

    public void startExpensionAnimation() {
        super.startExpensionAnimation();
        focused = true;
    }
}
