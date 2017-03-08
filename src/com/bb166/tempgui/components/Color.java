package com.bb166.tempgui.components;

import javafx.scene.paint.Paint;

import java.util.*;

public enum Color {
    RED("#F71B1B"),GREEN("#2EE61A"),BLUE("#1A57E6"),YELLOW("#E5E520"),ORANGE("#E59020");

    private static Random random = new Random();
    private static List<Paint> colors = new ArrayList<>();

    static {
        colors.add(Color.RED.getColor());
        colors.add(Color.BLUE.getColor());
        colors.add(Color.GREEN.getColor());
        colors.add(Color.ORANGE.getColor());
        colors.add(Color.YELLOW.getColor());
    }

    private Paint color;

    Color(String color) {
        this.color = javafx.scene.paint.Color.web(color);
    }

    public Paint getColor() {
        return color;
    }

    public static Paint randomizeColor(){
        return colors.get(random.nextInt(colors.size()-1));
    }
}
