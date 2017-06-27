package Test;

import com.bb166.tempgui.components.*;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.List;
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

        CartoonLoadingAnimation cartoonLoadingAnimation = new CartoonLoadingAnimation(cartoonComponentGroup,400,400);
        cartoonLoadingAnimation.start();

        AnimatedCartoonRoomsList animatedCartoonList = new AnimatedCartoonRoomsList(cartoonComponentGroup,500,100,900,500);

        List<AnimatedCartoonRoomsList.RoomLine> list = new LinkedList<>();
        for (int i = 0 ; i!= 22 ;i++) {
            AnimatedCartoonRoomsList.RoomLine roomLine1 = animatedCartoonList.new RoomLine();
            roomLine1.setOwner("bb116");
            roomLine1.setRoomName("Tutsdassssaj graj"+(i+1));
            roomLine1.setPlayersCount(1);
            animatedCartoonList.addLine(roomLine1);
        }

        RoomsListScrollbar roomsListScrollbar = new RoomsListScrollbar(cartoonComponentGroup, animatedCartoonList);


        animatedCartoonButton.setLabel("Tak to juz jest");

        CartoonComponentScene scene = new CartoonComponentScene(cartoonComponentGroup);
        primaryStage.setOnCloseRequest(e -> System.exit(0));

        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
