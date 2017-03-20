package com.bb166.tempgui.components;

import javafx.scene.input.KeyEvent;

public class AnimatedCartoonPasswordField extends AnimatedCartoonTextField {

    private StringBuilder stars;

    public AnimatedCartoonPasswordField(CartoonComponentGroup cartoonComponentGroup, int x, int y, int width) {
        super(cartoonComponentGroup, x, y, width);
        stars = new StringBuilder();
    }

    @Override
    void addCharacterToLabel(KeyEvent event) {
        super.addCharacterToLabel(event);
        StringBuilder text = super.getText();
        if (text.length() != stars.length()) {
            if (text.length() < stars.length())
                stars.deleteCharAt(stars.length() - 1);
            else
                stars.append('•');

            super.setTextControl(stars.toString());

            super.setCursorTranslateX(-Math.ceil(super.getRectangle().getWidth() / 2d) + super.getTextControlBounds().getWidth() + 10);
            super.setTextControlTranslateX(-Math.round(super.getRectangle().getWidth() / 2d) + Math.round(super.getTextControlBounds().getWidth() / 2d) + 7);
        }
    }
}
