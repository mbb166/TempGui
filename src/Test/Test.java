package Test;

import com.bb166.tempgui.components.*;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.concurrent.Executors;

public class Test extends Application {
    public static void main(String... args){
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        CartoonComponentGroup cartoonComponentGroup = new CartoonComponentGroup(Executors.newScheduledThreadPool(2));
        AnimatedCartoonButton animatedCartoonButton = new AnimatedCartoonButton(cartoonComponentGroup,60,10);
        AnimatedCartoonTextField animatedCartoonTextField = new AnimatedCartoonTextField(cartoonComponentGroup,100,100,200);
        AnimatedCartoonTextField animatedCartoonTextField1 = new AnimatedCartoonPasswordField(cartoonComponentGroup,200,200,200);
//2
        CartoonLoadingAnimation cartoonLoadingAnimation = new CartoonLoadingAnimation(cartoonComponentGroup,400,400);
        cartoonLoadingAnimation.start();

        AnimatedCartoonRoomsList animatedCartoonList = new AnimatedCartoonRoomsList(cartoonComponentGroup,500,100,900,500);
        AnimatedCartoonRoomsList.RoomLine roomLine = animatedCartoonList.new RoomLine();
        roomLine.setOwner("Owner");
        roomLine.setRoomName("Room name");
        roomLine.setPlayersCount(1);
        animatedCartoonList.addLine(roomLine);

        AnimatedCartoonRoomsList.RoomLine roomLine1 = animatedCartoonList.new RoomLine();
        roomLine1.setOwner("bb116");
        roomLine1.setRoomName("Tutsdassssaj graj");
        roomLine1.setPlayersCount(1);
        animatedCartoonList.addLine(roomLine1);
        AnimatedCartoonRoomsList.RoomLine roomLine2 = animatedCartoonList.new RoomLine();
        roomLine2.setOwner("bb116");
        roomLine2.setRoomName("Tutsdassssaj graj");
        roomLine2.setPlayersCount(1);
        //animatedCartoonList.addLine(roomLine2);

        animatedCartoonButton.setOnMouseClicked(event -> {animatedCartoonList.removeLine(roomLine);
                    animatedCartoonList.removeLine(roomLine1);
                    //animatedCartoonList.removeLine(roomLine2);
                    animatedCartoonList.addLine(roomLine2);
        }

        );
        animatedCartoonButton.setLabel("Tak to juz jest");

        CartoonComponentScene scene = new CartoonComponentScene(cartoonComponentGroup);
        primaryStage.setOnCloseRequest(e -> System.exit(0));

        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
