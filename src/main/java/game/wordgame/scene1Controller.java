package game.wordgame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class scene1Controller {

    @FXML
    private AnchorPane myPane;


    public void exit() {

//        Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION);
//        exitAlert.setTitle("Exit");
//        exitAlert.setHeaderText("You are going to Exit");
//        exitAlert.setContentText("Do you really Exit from this Awesome game???");
////        exitAlert.show();
//        boolean alertBool = (exitAlert.showAndWait().get() == ButtonType.OK);
//        if (alertBool) {
//            stage = (Stage) myPane.getScene().getWindow();
//            stage.close();
//        }

        Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION);
        exitAlert.setTitle("Exit");
        exitAlert.setHeaderText("You are going to Exit");
        exitAlert.setContentText("Do you really Exit from this Awesome game???");
//        exitAlert.setGraphic(new StackPane(new Background(new BackgroundFill(Color.RED, null,Insets.EMPTY))));

        Window mainDialogWindow = myPane.getScene().getWindow();
        exitAlert.initOwner(mainDialogWindow);
        exitAlert.initModality(Modality.WINDOW_MODAL);

        boolean alertBool = (exitAlert.showAndWait().get() == ButtonType.OK);
        if (alertBool) {
            Stage stage = (Stage) mainDialogWindow;
            stage.close();
        }

    }


     Stage stage;
     Scene scene;

    static Stage stage2;
    static Scene scene2;


    public void myStartF(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("playerName.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Word Matching Game");
        Image icon = new Image("Mylogo.png");
        stage.getIcons().add(icon);
        scene = new Scene(root);

        scene2=scene;
        stage2=stage;

        stage.setScene(scene);
        stage.show();
    }

    public static void startPlayerName(){
            stage2.setScene(scene2);
            stage2.show();
    }

    public void myScore(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("score.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Word Matching Game");
        Image icon = new Image("Mylogo.png");
        stage.getIcons().add(icon);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void myOption(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Option.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Word Matching Game");
        Image icon = new Image("Mylogo.png");
        stage.getIcons().add(icon);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //music

     static MediaPlayer mediaPlayer;
     static boolean isPlay=true;

    public void mediaStop(){
        isPlay=false;
        mediaPlayer.stop();
    }

    @FXML
    public void initialize() {
        String ss=String.valueOf(scene1Controller.class.getResource("alexander-nakarada-superepic.mp3"));
        Media media = new Media(ss);
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        isPlay=true;
        double x=mediaPlayer.getVolume();
        mediaPlayer.setVolume(x-.6);
        mediaPlayer.play();
    }
}
