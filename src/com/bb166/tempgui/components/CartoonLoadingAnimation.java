package com.bb166.tempgui.components;

import javafx.scene.Group;
import javafx.scene.shape.*;

public class CartoonLoadingAnimation {
    private Rectangle first;
    private Rectangle second;
    private Rectangle third;
    private Rectangle fourth;

    private Group group;

    public CartoonLoadingAnimation(int x, int y) {
        group = new Group();
        group.setLayoutX(x);
        group.setLayoutY(y);
        group.minWidth(100);
        group.minHeight(100);
    }

    Group getGroup() {
        return group;
    }
}
