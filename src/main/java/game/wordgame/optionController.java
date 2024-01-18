package game.wordgame;

import javafx.fxml.FXML;

public class optionController {

    scene1Controller scene1;

    public void back() {
        Main.startScene1();
    }

    public void onMusic() {
        if (!scene1.isPlay) {
            scene1.initialize();
            scene1.isPlay = true;
        }
    }

    public void offMusic() {
        scene1.isPlay = false;
        scene1.mediaStop();
    }

    @FXML
    void initialize() {
        scene1 = new scene1Controller();
    }
}
