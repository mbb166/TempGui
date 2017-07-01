package com.bb166.tempgui.components;

import javafx.animation.*;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class AnimatedCartoonRoomsList extends CartoonNode {
    private static final double roomNameWidthProportion = 0.57;
    private static final double ownerPlayersWidthProportion = 0.21;
    private static final int lineHeight = 30;

    private final int roomNameWidth;
    private final int ownerWidth;
    private final int playerCountWidth;

    private List<RoomLine> lines;

    private VBox list;

    private int lastLineView;
    private int firstLineView;
    private final int maxLineToView;

    private CartoonComponentGroup group;

    private volatile boolean animationEnd = false;

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

    private Queue<Runnable> rowToRemove;
    private Future<?> rowDeleteFuture;

    public class RoomLine extends StackPane {
        private Rectangle rectangle;

        private HBox hBox;

        private FillTransition focused;

        private FlowPane roomNamePane;
        private Text roomNameText;

        private FlowPane ownerPane;
        private Text ownerText;

        private FlowPane playersCountPane;
        private Text playersCountText;

        public RoomLine() {
            super.setWidth(AnimatedCartoonRoomsList.this.getWidth());
            super.setHeight(lineHeight);

            hBox = new HBox();
            hBox.setTranslateX(5);
            hBox.setTranslateY(2);

            super.setOnMouseEntered(event -> {
                focused.stop();
                focused.setToValue(Color.valueOf("#393939"));
                focused.play();
            });

            super.setOnMouseExited(event -> {
                focused.stop();
                focused.setToValue(Color.valueOf("#3F3F3F"));
                focused.play();
            });

            rectangle = new Rectangle(0, 0,
                    AnimatedCartoonRoomsList.this.getWidth(),
                    30);
            rectangle.setFill(Color.valueOf("#3F3F3F"));

            focused = new FillTransition(Duration.millis(200), rectangle);

            roomNamePane = new FlowPane();
            roomNamePane.setMinWidth(roomNameWidth);
            roomNamePane.setMaxWidth(roomNameWidth);
            roomNamePane.setAlignment(Pos.BASELINE_LEFT);
            roomNameText = new Text();
            roomNameText.setFill(Color.valueOf("#9C9C9C"));
            roomNameText.setFont(headerFont);
            roomNamePane.getChildren().add(roomNameText);

            hBox.getChildren().add(roomNamePane);

            ownerPane = new FlowPane();
            ownerPane.setMinWidth(ownerWidth);
            ownerPane.setMaxWidth(ownerWidth);
            ownerPane.setAlignment(Pos.BASELINE_CENTER);
            ownerText = new Text();
            ownerText.setFont(headerFont);
            ownerText.setFill(Color.valueOf("#9C9C9C"));
            ownerPane.getChildren().add(ownerText);

            hBox.getChildren().add(ownerPane);

            playersCountPane = new FlowPane();
            playersCountPane.setMaxWidth(playerCountWidth);
            playersCountPane.setMaxWidth(playerCountWidth);
            playersCountPane.setAlignment(Pos.BASELINE_CENTER);
            playersCountText = new Text();
            playersCountText.setFill(Color.valueOf("#9C9C9C"));
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

    public AnimatedCartoonRoomsList(CartoonComponentGroup group, int x, int y, int width, int height) {
        lastLineView = (height / lineHeight)-1;
        maxLineToView = lastLineView;
        int h = (int) (lineHeight * Math.ceil(height / lineHeight));
        lines = new LinkedList<>();

        roomNameWidth = (int) (width * roomNameWidthProportion);
        ownerWidth = (int) (width * ownerPlayersWidthProportion);
        playerCountWidth = ownerWidth;

        rowToRemove = new ConcurrentLinkedQueue<>();
        list = new VBox();
        list.setPrefHeight(h);
        list.setMaxWidth(width - 30);

        headerPane = new StackPane();
        headerPane.setMinWidth(width);
        headerPane.setMinHeight(lineHeight);

        super.setWidth(width);
        super.setHeight(h);
        super.setX(x);
        super.setY(y);

        rectangle = new Rectangle(x, y, width, h);
        rectangle.setFill(Color.web("#333333", 1d));
        super.add(rectangle);

        header = new HBox();
        header.setTranslateX(3);

        headerRectangle = new Rectangle(0, 0, width, lineHeight);
        headerRectangle.setFill(Color.valueOf("#545454"));
        super.add(headerRectangle);
        headerFont = Font.font("Arial Black", FontWeight.BOLD, 18);

        roomnamePane = new FlowPane();
        roomnameText = new Text("Rooms name");
        roomnamePane.setMinWidth(roomNameWidth);
        roomnamePane.setMaxWidth(roomNameWidth);
        roomnamePane.setAlignment(Pos.BASELINE_LEFT);
        this.setParameters(roomnamePane, roomnameText);
        header.getChildren().add(roomnamePane);

        ownerPane = new FlowPane();
        ownerText = new Text("Owner");
        ownerPane.setMinWidth(ownerWidth);
        ownerPane.setMaxWidth(ownerWidth);
        ownerPane.setAlignment(Pos.BASELINE_CENTER);
        this.setParameters(ownerPane, ownerText);
        header.getChildren().add(ownerPane);

        playersCountPane = new FlowPane();
        playersCountText = new Text("Player count");
        playersCountPane.setMaxWidth(playerCountWidth);
        playersCountPane.setMinWidth(playerCountWidth);
        playersCountPane.setAlignment(Pos.BASELINE_CENTER);
        this.setParameters(playersCountPane, playersCountText);
        header.getChildren().add(playersCountPane);

        headerPane.getChildren().add(headerRectangle);
        headerPane.getChildren().add(header);

        list.getChildren().add(headerPane);
        super.add(list);

        this.group = group;
        group.add(this);
    }

    private void setParameters(FlowPane pane, Text text) {
        text.setFont(headerFont);
        text.setFill(Color.WHITE);
        pane.getChildren().add(text);
    }

    public void addLine(RoomLine roomLine) {
        lines.add(roomLine);
        if (list.getChildren().size() * lineHeight < AnimatedCartoonRoomsList.this.getHeight()) {
            roomLine.setOpacity(0f);
            list.getChildren().add(roomLine);
            animateLine(roomLine);
        }
    }

    public void clear(){
        list.getChildren().clear();
        list.getChildren().add(headerPane);
    }

    public void shiftViewedList(int shift) {
        if (shift > 0) {
            if (shift <= maxLineToView) {
                if (shift < lines.size() - lastLineView) {
                    list
                            .getChildren()
                            .remove(1, shift + 1);

                    List<RoomLine> addLines = lines.subList(
                            lastLineView + 1,
                            lastLineView + shift + 1
                    );

                    list
                            .getChildren()
                            .addAll(addLines);

                    addLines.forEach(this::animateLine);

                    firstLineView += shift;
                    lastLineView += shift;
                } else {
                    list
                            .getChildren()
                            .remove(1, lines.size() - lastLineView);

                    List<RoomLine> addLines = lines.subList(
                            lastLineView+1,
                            lines.size()
                    );

                    list
                            .getChildren()
                            .addAll(addLines);

                    addLines.forEach(this::animateLine);

                    lastLineView = lines.size() - 1;
                    firstLineView = lines.size() - maxLineToView - 1;
                }
            } else {
                if (shift < lines.size() - lastLineView) {
                    clear();

                    List<RoomLine> addLines = lines.subList(firstLineView + shift,
                            lastLineView + shift);

                    list.getChildren().addAll(addLines);

                    addLines.forEach(this::animateLine);

                    firstLineView += shift;
                    lastLineView += shift;
                } else {
                    clear();

                    List<RoomLine> addLines = lines.subList(lines.size() - maxLineToView,
                            lines.size());

                    list.getChildren().addAll(addLines);

                    firstLineView += lines.size() - maxLineToView;
                }
            }
        } else if (shift < 0) {
            if (shift > -firstLineView) {
                list
                        .getChildren()
                        .remove(maxLineToView + shift + 1,
                                maxLineToView + 1);

                List<RoomLine> addLines = lines.subList(firstLineView + shift - 1, firstLineView - 1);

                list.getChildren().addAll(1, addLines);

                addLines.forEach(this::animateLine);

                firstLineView += shift;
                lastLineView += shift;
            }
        }
    }

    private void animateLine(RoomLine roomLine) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(200), roomLine);
        fadeTransition.setFromValue(0f);
        fadeTransition.setToValue(1f);
        fadeTransition.play();
    }

    private static int getNodeIndex(ObservableList<Node> list, Node node) {
        for (int i = 0; i != list.size(); i++)
            if (node == list.get(i))
                return i;
        return -1;
    }

    public int size() {
        return lines.size();
    }

    public void removeLine(RoomLine roomLine) {

        Runnable removeAction = () -> {
            FadeTransition fadeTransition = new FadeTransition(Duration.millis(200), roomLine);
            fadeTransition.setFromValue(1f);
            fadeTransition.setToValue(0f);
            fadeTransition.setAutoReverse(false);
            fadeTransition.play();

            animationEnd = false;

            new AnimationTimer() {
                public void handle(long now) {
                    if (fadeTransition.getStatus() == Animation.Status.STOPPED) {
                        int index = getNodeIndex(list.getChildren(), roomLine);
                        list.getChildren().remove(roomLine);
                        ObservableList<Node> nodes = list.getChildren();

                        for (int i = index; i != nodes.size(); i++)
                            nodes.get(i).setTranslateY(lineHeight);

                        for (int i = index; i != nodes.size(); i++) {
                            TranslateTransition translateTransition = new TranslateTransition(Duration.millis(200), nodes.get(i));
                            translateTransition.setFromY(lineHeight);
                            translateTransition.setToY(0);
                            translateTransition.play();
                        }
                        animationEnd = true;
                        stop();
                    }
                }
            }.start();

            while (!animationEnd) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        };

        if (getNodeIndex(list.getChildren(), roomLine) != -1) {
            rowToRemove.add(removeAction);
            if (rowDeleteFuture == null || rowDeleteFuture.isDone())
                rowDeleteFuture = group.executeRunnable(() -> rowToRemove.forEach(Runnable::run));

        }
    }

    public int getMaxLineToView() {
        return maxLineToView;
    }
}