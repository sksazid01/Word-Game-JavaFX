package game.wordgame;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;


public class congrate {
    @FXML
    Button cong,leaveButton;
    @FXML
    void initialize(){
       cong.setText("Congratulation for winning this Game\n\n\n     " +gameInterfaceController.winnerName);
       cong.setAlignment(Pos.CENTER);
    }

    @FXML
    AnchorPane myCongoPane;

    public void leave() {
        Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION);
        exitAlert.setTitle("Exit");
        exitAlert.setHeaderText("You are going to Exit");
        exitAlert.setContentText("Do you really Exit from this Awesome game???");
//        exitAlert.setGraphic(new StackPane(new Background(new BackgroundFill(Color.RED, null,Insets.EMPTY))));

// Get the window of the main dialog box
        Window mainDialogWindow = myCongoPane.getScene().getWindow();
// Set the owner of the exitAlert dialog to the main dialog box
        exitAlert.initOwner(mainDialogWindow);
// Set the modality to WINDOW_MODAL to ensure the exitAlert is shown on top
        exitAlert.initModality(Modality.WINDOW_MODAL);

        boolean alertBool = (exitAlert.showAndWait().get() == ButtonType.OK);
        if (alertBool) {
            // Close the main dialog box
            Stage stage = (Stage) mainDialogWindow;
            stage.close();
        }

     /*   Stage currentStage = (Stage) leaveButton.getScene().getWindow();
        currentStage.close();*/
    }

    public void Start(){
        Main.startScene1();
    }
}
