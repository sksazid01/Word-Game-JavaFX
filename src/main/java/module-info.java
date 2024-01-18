module game.wordgame {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.media;

    opens game.wordgame to javafx.fxml;
    exports game.wordgame;
}