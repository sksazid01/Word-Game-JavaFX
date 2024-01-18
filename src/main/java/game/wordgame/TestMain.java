package game.wordgame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

public class TestMain extends Application {
    @Override
    public void start(Stage stage)  {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GameInterface.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            stage.setTitle("Word Matching Game");
            Image icon = new Image("Mylogo.png");
            stage.getIcons().add(icon);

//            Get primary screen bounds
//        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
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
            stage.show();

        } catch (Exception e) {
            System.out.println("sk (TestMain) --->  "+e);
//            System.out.println("hi");
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}