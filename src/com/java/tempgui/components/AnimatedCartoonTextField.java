package com.java.tempgui.components;

import javafx.scene.shape.Line;

public class AnimatedCartoonTextField extends AbstractAnimatedCartoonComponent {
    private Line cursor;

    public AnimatedCartoonTextField(int x,int y, int width, int heigth){
        super(x,y);
        super.setWidth(width);
        super.setHeight(heigth);
    }

}
