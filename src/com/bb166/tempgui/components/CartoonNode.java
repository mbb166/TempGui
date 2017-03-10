package com.bb166.tempgui.components;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.util.concurrent.ScheduledExecutorService;

abstract class CartoonNode {

    private StackPane stackPane;

    private ScheduledExecutorService scheduledExecutorService;

    protected CartoonNode(ScheduledExecutorService scheduledExecutorService){
        this.scheduledExecutorService = scheduledExecutorService;
        stackPane = new StackPane();
    }

    Pane getPane() {
        return stackPane;
    }

    ScheduledExecutorService getScheduledExecutorService() {
        return scheduledExecutorService;
    }
}
