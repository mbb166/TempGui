package com.bb166.tempgui.components;

import javafx.scene.Group;

import java.util.HashSet;
import java.util.Set;

public final class CartoonComponentGroup{
    private Group group;
    private Set<AnimatedCartoonTextField> textComponents;

    public CartoonComponentGroup(){
        textComponents = new HashSet<>();
        group = new Group();
    }

    public void add(AbstractAnimatedCartoonComponent abstractAnimatedCartoonComponent){
        group.getChildren().add(abstractAnimatedCartoonComponent.getPane());

        if (abstractAnimatedCartoonComponent instanceof AnimatedCartoonTextField)
            textComponents.add((AnimatedCartoonTextField) abstractAnimatedCartoonComponent);
    }

    public void focusedTextComponent(AbstractAnimatedCartoonComponent animatedCartoonTextField) {
        textComponents.forEach(element -> {
            if (!element.equals(animatedCartoonTextField))
                element.startDecreasingAnimation();
        });
    }

    public void focusedAnotherComponent(){
        textComponents.forEach(AnimatedCartoonTextField::startDecreasingAnimation);
    }

    public Group getGroup(){
        return group;
    }
}
