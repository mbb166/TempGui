package com.bb166.tempgui.components;

import javafx.scene.Scene;
import javafx.scene.paint.Color;

public final class CartoonComponentScene extends Scene {
    public CartoonComponentScene(CartoonComponentGroup cartoonComponentGroup){
        super(cartoonComponentGroup.getGroup());
        super.setFill(Color.web("#4E4C49"));
        super.setOnKeyPressed(cartoonComponentGroup::setKeyEvent);
    }
}
