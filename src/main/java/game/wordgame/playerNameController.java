package game.wordgame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class playerNameController {

    private Stage stage;
    private Scene scene;
    static Stage stage2;
    static Scene scene2;
    @FXML
    TextField firstNameText;
    @FXML
    TextField secondNameText;
    @FXML
    Label playerNameWarning;

    public static String text1,text2;

    public void tossWindo(ActionEvent event) throws IOException {

        if(firstNameText.getText().length()==0 || secondNameText.getText().length()==0){
            playerNameWarning.setText("! Please Write Both Player Name.");
            return;
        }

        text1=firstNameText.getText();
        text2=secondNameText.getText();

        FXMLLoader loader=new FXMLLoader( getClass().getResource("toss.fxml"));
        Parent root = loader.load();

        tossController Toss=loader.getController();
        Toss.setStringValue(firstNameText.getText(),secondNameText.getText());



        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Word Matching Game");
        Image icon = new Image("Mylogo.png");
        stage.getIcons().add(icon);
        scene = new Scene(root);
        stage.setScene(scene);

        stage2=stage;
        scene2=scene;

        stage.show();
    }

    public static void startToss(){
        stage2.setScene(scene2);
        stage2.show();
    }

    @FXML
    AnchorPane myPanePlayerName;
    @FXML
    Button exitPlayerNameButton;


    public void exitPlayerName() {
        Main.startScene1();
//        Stage currentStage = (Stage) exitPlayerNameButton.getScene().getWindow();
//        currentStage.close();
    }
}
