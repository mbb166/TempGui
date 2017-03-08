package com.bb166.tempgui.components;

import javafx.scene.Group;
import javafx.scene.input.KeyEvent;

import java.util.HashSet;
import java.util.Set;

public final class CartoonComponentGroup{
    private Group group;
    private Set<AnimatedCartoonTextField> textComponents;

    public CartoonComponentGroup(){
        textComponents = new HashSet<>();
        group = new Group();
    }

    void add(AbstractAnimatedCartoonComponent abstractAnimatedCartoonComponent){
        group.getChildren().add(abstractAnimatedCartoonComponent.getPane());

        if (abstractAnimatedCartoonComponent instanceof AnimatedCartoonTextField)
            textComponents.add((AnimatedCartoonTextField) abstractAnimatedCartoonComponent);
    }

    void add(CartoonLoadingAnimation cartoonLoadingAnimation){
        group.getChildren().add(cartoonLoadingAnimation.getGroup());
    }

    void focusedTextComponent(AbstractAnimatedCartoonComponent animatedCartoonTextField) {
        textComponents.forEach(element -> {
            if (!element.equals(animatedCartoonTextField))
                element.startDecreasingAnimation();
        });
    }

    void focusedAnotherComponent(){
        textComponents.forEach(AnimatedCartoonTextField::startDecreasingAnimation);
    }

    Group getGroup(){
        return group;
    }

    void setKeyEvent(KeyEvent event) {
        textComponents.stream()
                .filter(AnimatedCartoonTextField::isFocused)
                .forEach(element -> element.addCharacterToLabel(event));
    }
}
