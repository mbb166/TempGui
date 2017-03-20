package com.bb166.tempgui.components;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.util.LinkedList;
import java.util.List;

public class AnimatedCartoonList extends AbstractAnimatedCartoonComponent {
    private int width = 400;
    private int height = 300;
    private int columnCount;

    private static class ListLine extends FlowPane {
        private List<Group> rows;

        public ListLine(String... rows) {
            this.rows = new LinkedList<>();
            super.setMaxWidth(40);
            super.setHeight(300);


            for (int i = 0; i != rows.length; i++) {
                Group group = new Group();
                group.prefWidth(10);
                Text text = new Text(rows[i]);
                group.getChildren().add(text);
                this.rows.add(group);
                super.getChildren().add(group);
                super.setAlignment(Pos.BASELINE_CENTER);
                super.setHgap(20);

            }
        }

        public List<Group> getRows() {
            return rows;
        }
    }

    private List<ListLine> lines;
    private GridPane gridPane;

    public AnimatedCartoonList(CartoonComponentGroup group, int x, int y, int columnCount) {
        super(group, x, y);
        super.getRectangle().setWidth(width);
        super.getRectangle().setHeight(height);

        this.columnCount = columnCount;
        this.lines = new LinkedList<>();
        this.gridPane = new GridPane();
        super.getPane().getChildren().add(gridPane);
    }

    public void addLine(String... line) {
        if (line.length == columnCount) {
            ListLine listLine = new ListLine(line);
            this.lines.add(listLine);
            this.gridPane.add(listLine, 0, lines.size() - 1);
        } else throw new IllegalStateException("Bad line column count");
    }


}