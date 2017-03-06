package com.bb166.tempgui.components;

import javafx.scene.Group;

import java.util.Set;

public class CartoonComponentGroup extends Group {
    Set<AbstractAnimatedCartoonComponent> components;

    public CartoonComponentGroup(){

    }

    public void add(AbstractAnimatedCartoonComponent abstractAnimatedCartoonComponent){
        super.getChildren().add(abstractAnimatedCartoonComponent.getPane());
        components.add(abstractAnimatedCartoonComponent);
    }
}
