package com.bb166.tempgui.components;

import javafx.scene.shape.Rectangle;

public class RoomsListScrollbar extends AbstractAnimatedCartoonComponent {
    private Rectangle scroll;
    private AnimatedCartoonRoomsList animatedCartoonRoomsList;
    private double lastPosition = 0;

    private boolean moved = false;
    private int hiddenLinesCount;

    public RoomsListScrollbar(CartoonComponentGroup group, AnimatedCartoonRoomsList animatedCartoonRoomsList) {
        super(group, animatedCartoonRoomsList.getX() + animatedCartoonRoomsList.getWidth() + 10, animatedCartoonRoomsList.getY() + 30);
        super.setHeight(animatedCartoonRoomsList.getHeight() - 30);
        super.setWidth(30);
        this.animatedCartoonRoomsList = animatedCartoonRoomsList;

        if (animatedCartoonRoomsList.size() - 30 < animatedCartoonRoomsList.getHeight()) {
            hiddenLinesCount = animatedCartoonRoomsList.size() - animatedCartoonRoomsList.getMaxLineToView();
            scroll = new Rectangle(30, super.getHeight() -hiddenLinesCount);
            scroll.setFill(Color.randomizeColor());
            scroll.setTranslateY(-hiddenLinesCount / 2);
            super.add(scroll);
        }

        scroll.setOnMousePressed(event -> {
            moved = true;
            lastPosition = 0;
        });

        scroll.setOnMouseReleased(event -> moved = false);
        scroll.setOnMouseDragged(event -> {
            if (moved) {
                double l = event.getSceneY() - super.getY();

                if (lastPosition != 0) {
                    if (l - lastPosition > 0 && l - lastPosition > hiddenLinesCount / 2d - scroll.getTranslateY()) {
                        scroll.setTranslateY(hiddenLinesCount / 2d);
                    } else if (l - lastPosition < 0 && lastPosition - l > hiddenLinesCount / 2d + scroll.getTranslateY()) {
                        scroll.setTranslateY(-hiddenLinesCount / 2d);
                    }else if ((l - lastPosition > 0 && scroll.getTranslateY() < hiddenLinesCount / 2d) ||
                            (l - lastPosition < 0 && scroll.getTranslateY() > -hiddenLinesCount / 2d)) {
                        scroll.setTranslateY(scroll.getTranslateY() - lastPosition + l);

                    }
                    animatedCartoonRoomsList.shiftViewedList((int)(l - lastPosition));
                }
                lastPosition = l;
            }
        });
    }
}