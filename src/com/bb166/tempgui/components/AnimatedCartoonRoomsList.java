package com.bb166.tempgui.components;

import javafx.animation.FillTransition;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class AnimatedCartoonRoomsList extends CartoonNode {
    private static final double roomNameWidthProportion = 0.57;
    private static final double ownerPlayersWidthProportion = 0.21;

    private final int roomNameWidth;
    private final int ownerWidth;
    private final int playerCountWidth;

    private VBox list;

    private Rectangle rectangle;

    private StackPane headerPane;
    private Font headerFont;
    private HBox header;
    private Rectangle headerRectangle;

    private FlowPane roomnamePane;
    private Text roomnameText;

    private FlowPane ownerPane;
    private Text ownerText;

    private FlowPane playersCountPane;
    private Text playersCountText;

    public class RoomLine extends StackPane {
        private Rectangle rectangle;

        private HBox hBox;

        private FillTransition focused;

        private Font font = Font.font("Arial Black", FontPosture.REGULAR,16);

        private FlowPane roomNamePane;
        private Text roomNameText;

        private FlowPane ownerPane;
        private Text ownerText;

        private FlowPane playersCountPane;
        private Text playersCountText;

        public RoomLine() {
            super.setWidth(AnimatedCartoonRoomsList.this.getWidth());
            super.setHeight(28);

            hBox = new HBox();
            hBox.setTranslateX(5);
            hBox.setTranslateY(2);

            super.setOnMouseEntered(event -> {
                focused.stop();
                focused.setToValue(Color.valueOf("#733333"));
                focused.play();
            });

            super.setOnMouseExited(event -> {
                focused.stop();
                focused.setToValue(Color.valueOf("#433333"));
                focused.play();
            });

            rectangle = new Rectangle(0,0,
                    AnimatedCartoonRoomsList.this.getWidth(),
                    30);
            rectangle.setFill(Color.valueOf("#433333"));

            focused = new FillTransition(Duration.millis(200),rectangle);

            roomNamePane = new FlowPane();
            roomNamePane.setMinWidth(roomNameWidth);
            roomNamePane.setMaxWidth(roomNameWidth);
            roomNamePane.setAlignment(Pos.BASELINE_LEFT);
            roomNameText = new Text();
            roomNameText.setFont(headerFont);
            roomNamePane.getChildren().add(roomNameText);

            hBox.getChildren().add(roomNamePane);

            ownerPane = new FlowPane();
            ownerPane.setMinWidth(ownerWidth);
            ownerPane.setMaxWidth(ownerWidth);
            ownerPane.setAlignment(Pos.BASELINE_CENTER);
            ownerText = new Text();
            ownerText.setFont(headerFont);
            ownerPane.getChildren().add(ownerText);

            hBox.getChildren().add(ownerPane);

            playersCountPane = new FlowPane();
            playersCountPane.setMaxWidth(playerCountWidth);
            playersCountPane.setMaxWidth(playerCountWidth);
            playersCountPane.setAlignment(Pos.BASELINE_CENTER);
            playersCountText = new Text();
            playersCountText.setFont(headerFont);
            playersCountPane.getChildren().add(playersCountText);

            hBox.getChildren().add(playersCountPane);

            super.getChildren().add(rectangle);
            super.getChildren().add(hBox);
        }

        public void setRoomName(String text) {
            roomNameText.setText(text);
        }

        public void setOwner(String text) {
            ownerText.setText(text);
        }

        public void setPlayersCount(int count) {
            playersCountText.setText(Integer.toString(count));
        }
    }

    public AnimatedCartoonRoomsList(CartoonComponentGroup group, int x, int y,int width,int height) {
        roomNameWidth = (int)(width * roomNameWidthProportion);
        ownerWidth = (int)(width * ownerPlayersWidthProportion);
        playerCountWidth = ownerWidth;

        list = new VBox();

        headerPane = new StackPane();
        headerPane.setMinWidth(width);
        headerPane.setMinHeight(36);

        super.setWidth(width);
        super.setHeight(height);
        super.setX(x);
        super.setY(y);

        rectangle = new Rectangle(x,y,width,height);
        rectangle.setFill(Color.web("#333333", 1d));
        super.add(rectangle);

        header = new HBox();
        header.setTranslateX(5);
        header.setTranslateY(4);

        headerRectangle = new Rectangle(0,0,width,36);
        headerRectangle.setFill(Color.valueOf("#5A5A5A"));
        super.add(headerRectangle);
        headerFont = Font.font("Arial Black",FontWeight.BOLD,18);

        roomnamePane = new FlowPane();
        roomnameText = new Text("Rooms name");
        roomnamePane.setMinWidth(roomNameWidth);
        roomnamePane.setMaxWidth(roomNameWidth);
        roomnamePane.setAlignment(Pos.BASELINE_LEFT);
        this.setParameters(roomnamePane,roomnameText);
        header.getChildren().add(roomnamePane);

        ownerPane = new FlowPane();
        ownerText = new Text("Owner");
        ownerPane.setMinWidth(ownerWidth);
        ownerPane.setMaxWidth(ownerWidth);
        ownerPane.setAlignment(Pos.BASELINE_CENTER);
        this.setParameters(ownerPane,ownerText);
        header.getChildren().add(ownerPane);

        playersCountPane = new FlowPane();
        playersCountText = new Text("Player count");
        playersCountPane.setMaxWidth(playerCountWidth);
        playersCountPane.setMinWidth(playerCountWidth);
        playersCountPane.setAlignment(Pos.BASELINE_CENTER);
        this.setParameters(playersCountPane,playersCountText);
        header.getChildren().add(playersCountPane);

        headerPane.getChildren().add(headerRectangle);
        headerPane.getChildren().add(header);

        list.getChildren().add(headerPane);
        super.add(list);

        group.add(this);
    }

    private void setParameters(FlowPane pane, Text text) {
        text.setFont(headerFont);
        text.setFill(Color.WHITE);
        pane.getChildren().add(text);
    }

    public void addLine(RoomLine roomLine) {
        list.getChildren().add(roomLine);
    }
}