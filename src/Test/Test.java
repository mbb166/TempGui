package Test;

import com.bb166.tempgui.components.*;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test extends Application {
    public static void main(String... args){
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        CartoonComponentGroup cartoonComponentGroup = new CartoonComponentGroup(Executors.newScheduledThreadPool(2));
        AnimatedCartoonButton animatedCartoonButton = new AnimatedCartoonButton(cartoonComponentGroup,60,10);
        AnimatedCartoonTextField animatedCartoonTextField = new AnimatedCartoonTextField(cartoonComponentGroup,100,100,200,36);
        AnimatedCartoonTextField animatedCartoonTextField1 = new AnimatedCartoonTextField(cartoonComponentGroup,200,200,200,36);
        animatedCartoonButton.setOnMouseClicked(System.out::println);
        animatedCartoonButton.setLabel("Tak to juz jest");
        CartoonLoadingAnimation cartoonLoadingAnimation = new CartoonLoadingAnimation(cartoonComponentGroup,400,400);
        cartoonLoadingAnimation.start();
        CartoonComponentScene scene = new CartoonComponentScene(cartoonComponentGroup);
        primaryStage.setOnCloseRequest(e -> System.exit(0));

        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
