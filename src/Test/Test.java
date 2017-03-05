package Test;

import com.bb166.tempgui.components.AnimatedCartoonButton;
import com.bb166.tempgui.components.AnimatedCartoonTextField;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Mateusz on 16.02.2017.
 */
public class Test extends Application {
    public static void main(String... args){
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        AnimatedCartoonButton animatedCartoonButton = new AnimatedCartoonButton(60,10);
        AnimatedCartoonTextField animatedCartoonTextField = new AnimatedCartoonTextField(100,100,100,30);
        Group group = new Group();
        animatedCartoonButton.setLabel("Start");
        group.getChildren().add(animatedCartoonButton.getPane());
        group.getChildren().add(animatedCartoonTextField.getPane());
        Scene scene = new Scene(group);

        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
