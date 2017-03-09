package com.bb166.tempgui.components;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.shape.*;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class CartoonLoadingAnimation {
    private Rectangle first;
    private Rectangle second;
    private Rectangle third;
    private Rectangle fourth;

    private float opacity = 1f;
    private boolean appearance = true;

    private boolean firstVisible = true;
    private boolean secondVisible = false;
    private boolean thirdVisible = false;
    private boolean fourthVisible = false;

    private Group group;

    private AnimationTimer animationTimer;
    private ScheduledExecutorService scheduledExecutorService;
    private ScheduledFuture<?> scheduledFuture;

    private int refresh = 5;

    private Runnable runnable = () -> {
        if (appearance) {
            opacity += 0.03f;
            if (opacity >= 0.97f) {
                opacity = 1f;
                appearance = false;
            }
        } else {
            opacity -= 0.03f;
            if (opacity <= 0.03f) {
                opacity = 0f;
                appearance = true;
                if (firstVisible) {
                    secondVisible = true;
                    firstVisible = false;
                } else if (secondVisible) {
                    secondVisible = false;
                    thirdVisible = true;
                } else if (thirdVisible) {
                    thirdVisible = false;
                    fourthVisible = true;
                } else if (fourthVisible) {
                    fourthVisible = false;
                    firstVisible = true;
                }
            }
        }
    };

    public CartoonLoadingAnimation(CartoonComponentGroup group,int x, int y) {
        this.group = new Group();
        this.group.setLayoutX(x);
        this.group.setLayoutY(y);
        this.group.maxWidth(55);
        this.group.maxHeight(55);

        this.scheduledExecutorService  = group.getScheduledExecutorService();

        Color.ColorsNotRepeat colors = Color.getColorsNotRepeat();

        animationTimer = new AnimationTimer() {
            public void handle(long event){
                first.setOpacity(opacity);
                second.setOpacity(opacity);
                third.setOpacity(opacity);
                fourth.setOpacity(opacity);

                first.setVisible(firstVisible);
                second.setVisible(secondVisible);
                third.setVisible(thirdVisible);
                fourth.setVisible(fourthVisible);
            }
        };

        first = new Rectangle();
        first.setWidth(5);
        first.setHeight(5);
        first.setTranslateX(-9);
        first.setTranslateY(-9);
        first.setFill(colors.getColorNotRepeat());

        second = new Rectangle();
        second.setHeight(5);
        second.setWidth(5);
        second.setTranslateY(-9);
        second.setFill(colors.getColorNotRepeat());

        third = new Rectangle();
        third.setHeight(5);
        third.setWidth(5);
        third.setFill(colors.getColorNotRepeat());

        fourth = new Rectangle();
        fourth.setHeight(5);
        fourth.setWidth(5);
        fourth.setTranslateX(-9);
        fourth.setFill(colors.getColorNotRepeat());

        this.group.getChildren().add(first);
        this.group.getChildren().add(second);
        this.group.getChildren().add(third);
        this.group.getChildren().add(fourth);

        first.setVisible(false);
        second.setVisible(false);
        third.setVisible(false);
        fourth.setVisible(false);

        group.add(this);
    }

    public void start(){
        animationTimer.start();
        scheduledFuture = scheduledExecutorService.scheduleWithFixedDelay(
                runnable,
                refresh,
                refresh,
                TimeUnit.MILLISECONDS);

    }

    public void stop(){
        animationTimer.stop();
        scheduledFuture.cancel(false);
        firstVisible = true;
        secondVisible = false;
        thirdVisible = false;
        fourthVisible = false;

        opacity = 1f;
        appearance = true;
    }

    Group getGroup() {
        return group;
    }
}