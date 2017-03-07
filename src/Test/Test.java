package Test;

import com.bb166.tempgui.components.AnimatedCartoonButton;
import com.bb166.tempgui.components.AnimatedCartoonTextField;
import com.bb166.tempgui.components.CartoonComponentGroup;
import com.bb166.tempgui.components.CartoonComponentScene;
import javafx.application.Application;
import javafx.stage.Stage;

public class Test extends Application {
    public static void main(String... args){
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        CartoonComponentGroup cartoonComponentGroup = new CartoonComponentGroup();
        AnimatedCartoonButton animatedCartoonButton = new AnimatedCartoonButton(cartoonComponentGroup,60,10);
        AnimatedCartoonTextField animatedCartoonTextField = new AnimatedCartoonTextField(cartoonComponentGroup,100,100,100,30);
        AnimatedCartoonTextField animatedCartoonTextField1 = new AnimatedCartoonTextField(cartoonComponentGroup,200,200,100,30);
        animatedCartoonButton.setOnMouseClicked(System.out::println);
        animatedCartoonButton.setLabel("Tak to juz jest");

        CartoonComponentScene scene = new CartoonComponentScene(cartoonComponentGroup);
        primaryStage.setOnCloseRequest(e -> System.exit(0));

        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
