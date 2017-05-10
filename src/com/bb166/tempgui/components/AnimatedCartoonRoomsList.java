package com.bb166.tempgui.components;

import javafx.geometry.Pos;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class AnimatedCartoonRoomsList extends CartoonNode {
    private int width = 700;
    private int height = 500;

    private Rectangle rectangle;

    private Font headerFont;
    private HBox header;
    private Rectangle headerRectangle;

    private FlowPane roomnamePane;
    private Text roomnameText;

    private FlowPane ownerPane;
    private Text ownerText;

    private FlowPane playersCountPane;
    private Text playersCountText;

    public AnimatedCartoonRoomsList(CartoonComponentGroup group, int x, int y) {
        super.setWidth(width);
        super.setHeight(height);
        super.setX(x);
        super.setY(y);

        rectangle = new Rectangle(x,y,width,height);
        rectangle.setFill(Color.web("#333333", 1d));
        super.add(rectangle);

        header = new HBox();

        headerRectangle = new Rectangle(0,0,width,36);
        headerRectangle.setTranslateY(-height/2);
        headerRectangle.setFill(Color.valueOf("#300000"));
        super.add(headerRectangle);
        headerFont = Font.font("Arial Black",FontWeight.BOLD,18);

        roomnamePane = new FlowPane();
        roomnameText = new Text("Rooms name");
        roomnamePane.setMinWidth(400);
        this.setParameters(roomnamePane,roomnameText);
        header.getChildren().add(roomnamePane);

        ownerPane = new FlowPane();
        ownerText = new Text("Owner");
        ownerPane.setMinWidth(150);
        this.setParameters(ownerPane,ownerText);
        header.getChildren().add(ownerPane);

        playersCountPane = new FlowPane();
        playersCountText = new Text("Players count");
        playersCountPane.setAlignment(Pos.CENTER_RIGHT);
        this.setParameters(playersCountPane,playersCountText);
        header.getChildren().add(playersCountPane);

        super.add(header);

        group.add(this);
    }

    private void setParameters(FlowPane pane, Text text) {
        text.setFont(headerFont);
        text.setFill(Color.WHITE);
        pane.setAlignment(Pos.BASELINE_LEFT);
        pane.getChildren().add(text);
        pane.setTranslateY(-12);
        pane.setTranslateX(5);
    }
}