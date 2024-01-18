package game.wordgame;

// 1st year, Java Project,CSE SUST



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    static Scene scene2;
    static Stage stage2;

    @Override
    public void start(Stage stage) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("scene1.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            scene2=scene;
            stage.setTitle("Word Matching Game");
            Image icon = new Image("Mylogo.png");
            stage.getIcons().add(icon);

//            Get primary screen bounds
//        Rectangle2D screenBounds = Scr een.getPrimary().getBounds();
//        double screenX=screenBounds.getMinX();
//        double screenY=screenBounds.getMinY();
//        System.out.println(screenBounds);


//        stage.setX(screenX);
//        stage.setY(screenY);

//        @FXML
//        AnchorPane myPane;
//        myPane.setLayoutX(screenX);

            stage.setResizable(false);
            stage.setFullScreen(true);
//        stage.setMaximized(true);
            stage.setFullScreenExitHint(""); // no hind
            stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);

//        scene.setFill(Color.valueOf("black"));

            stage.setScene(scene);
            stage2=stage;
            stage.show();

        } catch (Exception e)
        {
            System.out.println("sk --->  "+e);
//            System.out.println("hi");
        }

    }
        public static void startScene1(){
            stage2.setScene(scene2);
            stage2.show();
        }


    public static void main(String[] args) {
        launch(args);
    }
}