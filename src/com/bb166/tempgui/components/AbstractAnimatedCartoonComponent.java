package com.bb166.tempgui.components;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

abstract class AbstractAnimatedCartoonComponent extends CartoonNode {
    private Rectangle rectangle;

    private Random random;

    private double maxXScale = 1;
    private double maxYScale = 1;
    private double xScale = 1;
    private double yScale = 1;
    private double scaleIncrement = 0.005;

    private int refresh = 5;
    private int refreshIncrement = 1;

    private CartoonComponentGroup cartoonComponentGroup = null;

    private ScheduledFuture<?> scheduledFuture;
    private volatile boolean increment = true;

    private AnimationTimer animationTimer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            rectangle.setScaleX(xScale);
            rectangle.setScaleY(yScale);
        }
    };

    private void animation() {
        if (increment) {
            if (xScale <= maxXScale)
                xScale += scaleIncrement;

            if (yScale <= maxYScale)
                yScale += scaleIncrement;

            if (yScale <= maxYScale ||
                    xScale <= maxXScale)
                refresh += refreshIncrement;

            if (xScale >= maxXScale &&
                    yScale >= maxYScale) {
                scheduledFuture.cancel(false);
                animationTimer.stop();
            }
        } else {
            if (xScale > 1)
                xScale -= scaleIncrement;
            if (yScale > 1)
                yScale -= scaleIncrement;
            if (xScale > 1 ||
                    yScale > 1)
                refresh -= refreshIncrement;
            if (xScale <= 1)
                xScale = 1;
            if (yScale <= 1)
                yScale = 1;
            if (yScale == 1 && xScale == 1) {
                scheduledFuture.cancel(false);
                animationTimer.stop();
                increment = true;
            }
        }
    }

    protected AbstractAnimatedCartoonComponent(CartoonComponentGroup cartoonComponentGroup, int x, int y) {
        super(cartoonComponentGroup.getScheduledExecutorService());
        this.cartoonComponentGroup = cartoonComponentGroup;

        random = new Random();
        rectangle = new Rectangle();
        rectangle.setFill(Color.web("#333333", 1d));

        super.getPane().setLayoutX(x);
        super.getPane().setLayoutY(y);
        super.getPane().getChildren().add(rectangle);

        cartoonComponentGroup.add(this);
    }

    void startExpensionAnimation() {
        increment = true;
        if (scheduledFuture == null || scheduledFuture.isCancelled()) {
            animationTimer.start();
            maxXScale = xScale + (random.nextInt(5) + 5) / 100d;
            maxYScale = yScale + (random.nextInt(5) + 5) / 100d;
            scheduledFuture =
                    super.getScheduledExecutorService().scheduleWithFixedDelay(
                            this::animation,
                            refresh,
                            refresh,
                            TimeUnit.MILLISECONDS);
        }
    }

    public void setWidth(int width) {
        rectangle.setWidth(width);
        super.getPane().setMaxWidth(width);
    }

    public void setHeight(int height) {
        rectangle.setHeight(height);
        super.getPane().setMaxHeight(height);
    }

    public void setX(int x) {
        rectangle.setX(x);
    }

    public void setY(int y) {
        rectangle.setY(y);
    }

    void startDecreasingAnimation() {
        increment = false;
        if (scheduledFuture == null || scheduledFuture.isCancelled()) {
            animationTimer.start();
            scheduledFuture =
                    super.getScheduledExecutorService().scheduleWithFixedDelay(
                            this::animation,
                            refresh,
                            refresh,
                            TimeUnit.MILLISECONDS);
        }
    }

    public void setOnMousePressed(EventHandler<? super MouseEvent> ev) {
        rectangle.setOnMousePressed(ev);
    }

    public void setOnMouseReleased(EventHandler<? super MouseEvent> ev) {
        rectangle.setOnMouseReleased(ev);
    }

    public void setOnMouseClicked(EventHandler<? super MouseEvent> ev) {
        rectangle.setOnMouseClicked(ev);
    }

    public void setOnMouseEntered(EventHandler<? super MouseEvent> ev) {
        rectangle.setOnMouseEntered(ev);
    }

    public void setOnMouseExited(EventHandler<? super MouseEvent> ev) {
        rectangle.setOnMouseExited(ev);
    }

    protected CartoonComponentGroup getCartoonComponentGroup() {
        return cartoonComponentGroup;
    }

    protected Rectangle getRectangle() {
        return rectangle;
    }
}