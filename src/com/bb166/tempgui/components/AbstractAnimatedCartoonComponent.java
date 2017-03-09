package com.bb166.tempgui.components;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

abstract class AbstractAnimatedCartoonComponent {
    private Rectangle button;
    private StackPane stackPane;

    private ScheduledExecutorService scheduledExecutorService;
    private AnimationTimer animationTimer;

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
        this.cartoonComponentGroup = cartoonComponentGroup;
        this.scheduledExecutorService = cartoonComponentGroup.getScheduledExecutorService();
        random = new Random();
        stackPane = new StackPane();
        button = new Rectangle();
        button.setFill(Color.web("#333333", 1d));

        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                button.setScaleX(xScale);
                button.setScaleY(yScale);
            }
        };

        stackPane.setLayoutX(x);
        stackPane.setLayoutY(y);
        stackPane.getChildren().add(button);

        cartoonComponentGroup.add(this);
    }

    void startExpensionAnimation() {
        increment = true;
        if (scheduledFuture == null || scheduledFuture.isCancelled()) {
            animationTimer.start();
            maxXScale = xScale + (random.nextInt(5) + 5) / 100d;
            maxYScale = yScale + (random.nextInt(5) + 5) / 100d;
            scheduledFuture =
                    scheduledExecutorService.scheduleWithFixedDelay(
                            this::animation,
                            refresh,
                            refresh,
                            TimeUnit.MILLISECONDS);
        }
    }

    public void setWidth(int width) {
        button.setWidth(width);
        stackPane.setMaxWidth(width);
    }

    public void setHeight(int height) {
        button.setHeight(height);
        stackPane.setMaxHeight(height);
    }

    public void setX(int x) {
        button.setX(x);
    }

    public void setY(int y) {
        button.setY(y);
    }

    void startDecreasingAnimation() {
        increment = false;
        if (scheduledFuture == null || scheduledFuture.isCancelled()) {
            animationTimer.start();
            scheduledFuture =
                    scheduledExecutorService.scheduleWithFixedDelay(
                            this::animation,
                            refresh,
                            refresh,
                            TimeUnit.MILLISECONDS);
        }
    }

    public void setOnMousePressed(EventHandler<? super MouseEvent> ev) {
        button.setOnMousePressed(ev);
    }

    public void setOnMouseReleased(EventHandler<? super MouseEvent> ev) {
        button.setOnMouseReleased(ev);
    }

    public void setOnMouseClicked(EventHandler<? super MouseEvent> ev) {
        button.setOnMouseClicked(ev);
    }

    public void setOnMouseEntered(EventHandler<? super MouseEvent> ev) {
        button.setOnMouseEntered(ev);
    }

    public void setOnMouseExited(EventHandler<? super MouseEvent> ev) {
        button.setOnMouseExited(ev);
    }

    Pane getPane() {
        return stackPane;
    }

    protected CartoonComponentGroup getCartoonComponentGroup() {
        return cartoonComponentGroup;
    }

    protected Rectangle getButton() {
        return button;
    }

    protected ScheduledExecutorService getScheduledExecutorService() {
        return scheduledExecutorService;
    }
}