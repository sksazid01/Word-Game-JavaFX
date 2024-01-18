package game.wordgame;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class tossController {
    public static boolean first;

    private Stage stage;
    private Scene scene;
    static Scene scene2;
    static Stage stage2;

    public void startGame(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("GameInterface.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Word Matching Game");
        Image icon = new Image("Mylogo.png");
        stage.getIcons().add(icon);
        scene = new Scene(root);

        stage2=stage;
        scene2=scene;

        stage.setScene(scene);
        stage.show();
    }



    private int tossValue = 1;

    public void headButton(ActionEvent event) {
        tossValue = 2;
    }


    public void tailButton(ActionEvent event) {
        tossValue = 3;
    }


    @FXML
    Label warningLabel;

    @FXML
    Label tossResultLabel;

    @FXML
    ImageView bothToss, bothToss2;


    public void startRotateBoth(double x, double y, double ScaleDuration, ImageView imageView) {

        RotateTransition rotate = new RotateTransition();
        rotate.setNode(imageView);
        rotate.setDuration(Duration.millis(400));
        rotate.setCycleCount(10); //number of rotation
        rotate.setInterpolator(Interpolator.LINEAR);
        rotate.setRate(1);
        rotate.setByAngle(360);
        rotate.setAxis(Rotate.Z_AXIS);
//        rotate.setToAngle(100);
//        rotate.getFromAngle();


        ScaleTransition scale = new ScaleTransition();
        scale.setNode(imageView);
        scale.setDuration(Duration.seconds(ScaleDuration));
        scale.setCycleCount(0);  //How many cycle done before Exit
//        scale.setRate(1);
//        scale.setInterpolator(Interpolator.LINEAR);
//        scale.setByX(2.0);
//        scale.setByY(2.0);
        scale.setFromX(x);
        scale.setFromY(x);
        scale.setToX(y);
        scale.setToY(y);
        scale.setDelay(Duration.seconds(2.5));
//        scale.setAutoReverse(true);

        ParallelTransition pt = new ParallelTransition(rotate, scale);
//        pt.setDelay(Duration.millis(1900));
        pt.play();
    }


    Image ok;

    public Boolean lossOrWin(double val) {
        double x = Math.random() * 100;
        int y = (int) (x * Math.random());
        if (y % 2 == val % 2) {
            ok = new Image(getClass().getResourceAsStream("UP.png"));
            bothToss2.setImage(ok);
            startRotateBoth(0, 1.5, 1.5, bothToss2);
            return true;
        } else {
            ok = new Image(getClass().getResourceAsStream("Down.png"));
            bothToss2.setImage(ok);
            startRotateBoth(0, 1.5, 1.5, bothToss2);
            return false;
        }
    }

    //Delay
    @FXML
    Button startGame;

    public void appear() {
        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(e -> {
            tossResultLabel.setVisible(true);
            startGame.setVisible(true);
        });
        pause.play();
    }


    private boolean onlyFirstToss = false;

    public void tossButton() {
        if (onlyFirstToss) return;
        //label design
        tossResultLabel.setBackground(Background.fill(Color.GOLD));


        //tossResultLabel visibility
        tossResultLabel.setVisible(false);
        startGame.setVisible(false);


        boolean x;
        if (tossValue == 1) {
            warningLabel.setText("! Please select Head or Tail...");
        } else {
            onlyFirstToss = true;
            appear();
            startRotateBoth(1, 0, 2, bothToss);

//            Thread.sleep(1000);
            if (tossValue == 2) {
                warningLabel.setText("You have select Head");
                x = lossOrWin(2);
            } else {
                warningLabel.setText("You have select Tail");
                x = lossOrWin(3);
            }

            if (x) tossResultLabel.setText("You Win.");
            else tossResultLabel.setText("You Loss.");
            first = !x;
//              Thread.sleep(2000);

        }
    }


    @FXML
    AnchorPane myPaneToss;

    public void exitToss(ActionEvent event) {
        scene1Controller.startPlayerName();
//        Stage currentStage = (Stage) myPaneToss.getScene().getWindow();
//        currentStage.close();
    }


    @FXML
    Label tossForFirstPlayerLabel, tossForFirstPlayerLabel1;

    public void setStringValue(String s1, String s2) {

        try {
            tossForFirstPlayerLabel1.setText(s1);
            tossForFirstPlayerLabel1.setTextFill(Color.RED);
        } catch (Exception e) {
            System.out.println("getText() not found");
        }
//        System.out.println(Name1+Name2);
    }

    @FXML
    public void initialize() {
//        tossForFirstPlayerLabel.setText("This Toss For First Player : ");
    }
}
