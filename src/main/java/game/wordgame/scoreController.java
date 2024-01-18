package game.wordgame;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.*;
import java.net.URISyntaxException;

public class scoreController {
    File file;
    @FXML
    Label scoreLabel;

    public void reset() throws IOException, URISyntaxException {
        heightScore.resetScore();
        scoreLabel.setText(Integer.toString(heightScore.score));
//
       BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(file));
       bufferedWriter.write("00");
       bufferedWriter.flush();
       bufferedWriter.close();
    }

    public void back(){
        Main.startScene1();
    }

    public void initialize() throws IOException, URISyntaxException {
        file=new File(getClass().getResource("Score.txt").toURI());
        BufferedReader bufferedReader=new BufferedReader(new FileReader(file));

        try {
           heightScore.score= Integer.parseInt(bufferedReader.readLine());
        }
        catch (Exception e) {
            System.out.println("Exception in bufferedReader.nextLine "+e);
        }

        scoreLabel.setText(Integer.toString(heightScore.getScore()));
        bufferedReader.close();
    }
}
