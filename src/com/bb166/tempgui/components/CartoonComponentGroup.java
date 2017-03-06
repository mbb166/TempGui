package com.bb166.tempgui.components;

import javafx.scene.Group;

import java.util.HashSet;
import java.util.Set;

public class CartoonComponentGroup extends Group {
    Set<AbstractAnimatedCartoonComponent> textComponents;

    public CartoonComponentGroup(){
        textComponents = new HashSet<>();
    }

    public void add(AbstractAnimatedCartoonComponent abstractAnimatedCartoonComponent){
        super.getChildren().add(abstractAnimatedCartoonComponent.getPane());
        textComponents.add(abstractAnimatedCartoonComponent);
    }

    public void focusedTextComponent(AnimatedCartoonTextField animatedCartoonTextField) {
        textComponents.forEach(element -> {
            if (!element.equals(animatedCartoonTextField))
                animatedCartoonTextField.startDecreasingAnimation();
        });
    }
}
