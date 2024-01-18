package game.wordgame;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.*;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Vector;

public class gameInterfaceController {
    @FXML
    AnchorPane exitGamePane;
    @FXML
    Button exitGameButton, matchButton;
    @FXML
    GridPane gridPane;
    @FXML
    Label firstPlayerTurn, secondPlayerTurn, firstPlayerTurn1, firstPlayerTurn2;
    @FXML
    Label selectedString, score1, score2;

    static String winnerName = "Please,Make 100 score first", A, B;

    public void matchButton() throws IOException, URISyntaxException {
        if (showingS == "") {
            selectedString.setText("Select Valid Grid");
        } else {
            showingS = showingS.toLowerCase(); //convert all to lower
            if (dictionary.isExist(showingS)) {
                winSound();
                dictionary.setFalse(showingS);

                selectedString.setText("Congratulation,You have got " + v.size() + " score.");
                int ln = v.size() - 1;
                while (ln >= 0) {
                    int x = v.get(ln).first();
                    int y = v.get(ln).second();
                    ln -= 1;
//                    textField[x][y].setBackground(Background.fill(Color.LIGHTGREEN));
                    textField[x][y].setStyle("-fx-background-color:linear-gradient(from 100.0% 0.0% to 0.0% 100.0%, #713bedff 0.0%, #713bedff 0.662%, #f45e92ff 52.736%, #0efde5ff 100.0%)");
//                    textField[x][y].setDisable(true); //after matching

                    //scoring
                    if (first) {
                        int X = Integer.parseInt(score1.getText()) + 1;
                        score1.setText(Integer.toString(X));
                    } else {
                        int X = Integer.parseInt(score2.getText()) + 1;
                        score2.setText(Integer.toString(X));
                    }
                }
                int sc1 = Integer.parseInt(score1.getText());
                int sc2 = Integer.parseInt(score2.getText());
                heightScore.increaseScore(sc1, sc2);


                if (sc1 > 99) {
                    winnerName = A;
                    loadCong();
                } else if (sc2 > 99) {
                    winnerName = B;
                    loadCong();
                }


                v.clear();
            } else {
                lossSound();
                //negative scoring
                selectedString.setText("Ohh No \"" + showingS + "\" is wrong,You lost " + v.size() + " score.");
                if (first) {
                    int X = Integer.parseInt(score1.getText()) - v.size();
                    score1.setText(Integer.toString(X));
                } else {
                    int X = Integer.parseInt(score2.getText()) - v.size();
                    score2.setText(Integer.toString(X));
                }
            }
            showingS = "";
        }
    }


    public void exitGame() {
        playerNameController.startToss();
//        Stage currentStage = (Stage) exitGamePane.getScene().getWindow();
//        currentStage.close();
    }


    TextField textField[][] = new TextField[17][16];


    boolean first = tossController.first;

    public void turnswap() {
        v.clear();
        selectedString.setText("");
        showingS = "";

        for (int i = 0; i < 17; i++)
            Arrays.fill(flag[i], 0);

        if (first) {
            firstPlayerTurn.setText("Input a Letter");
            firstPlayerTurn.setBackground(Background.fill(Paint.valueOf("83ff6e")));
            secondPlayerTurn.setText("Try to make Word");
            secondPlayerTurn.setBackground(Background.fill(Color.IVORY));
//            firstPlayerTurn.setVisible(true);
//            secondPlayerTurn.setVisible(false);
        } else {
            secondPlayerTurn.setText("Input a Letter");
            secondPlayerTurn.setBackground(Background.fill(Paint.valueOf("498cff")));
            firstPlayerTurn.setText("Try to make Word");
            firstPlayerTurn.setBackground(Background.fill(Color.IVORY));
//            firstPlayerTurn.setVisible(false);
//            secondPlayerTurn.setVisible(true);
        }
        first = !first;
    }


    int flag[][] = new int[17][16];
    private String showingS = "";

    //vector
    Vector<Pair> v = new Vector<>();

    void gaming(int i, int j, String s) {
        int x = -1, y = -1;

        if (!v.isEmpty()) {
            x = (v.get(v.size() - 1)).first();
            y = (v.get(v.size() - 1)).second();
        }

        if (flag[i][j] == 0 && ((x == -1 && y == -1) || (Math.abs(x - i) <= 1 && Math.abs(y - j) <= 1))) {
            showingS += s;
            selectedString.setText(showingS);
            flag[i][j] = 1;

            Pair pair = new Pair(i, j);
            v.add(pair);
        } else {
//            v.clear();
        }
    }

    Dictionary dictionary;

    private double[] calculateHexagonPoints(double centerX, double centerY, double sideLength) {
        double[] points = new double[12];

        for (int i = 0; i < 6; i++) {

            double angle = 2.0 * Math.PI / 6 * i;
            double x = centerX + sideLength * Math.cos(angle);
            double y = centerY + sideLength * Math.sin(angle);

            points[i * 2] = x;
            points[i * 2 + 1] = y;
        }
        return points;
    }

    //music
    MediaPlayer loss, win, click;

    void initializeMusic() {
        Media winmusic = new Media(String.valueOf(getClass().getResource("winn.wav")));
        Media lossmusic = new Media(String.valueOf(getClass().getResource("loss.wav")));
        Media clickmusic = new Media(String.valueOf(getClass().getResource("click.wav")));
        win = new MediaPlayer(winmusic);
        loss = new MediaPlayer(lossmusic);
        click = new MediaPlayer(clickmusic);
    }

    void winSound() {
        win.stop();
        win.setVolume(win.getVolume() + 10.0);
        win.play();
    }

    void lossSound() {
        loss.stop();
        loss.play();
    }

    void clickSound() {
        click.stop();
        click.play();
    }


    public void loadCong() throws IOException, URISyntaxException {
        File file = new File(getClass().getResource("Score.txt").toURI());
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        bufferedWriter.write(Integer.toString(heightScore.score));
        bufferedWriter.flush();
        bufferedWriter.close();


        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Congrate.fxml")));
        Stage stage = (Stage) exitGameButton.getScene().getWindow();  //get stage from button
        stage.setTitle("Word Matching Game");
        Image icon = new Image("Mylogo.png");
        stage.getIcons().add(icon);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    void initialize() throws FileNotFoundException {
        initializeMusic();
        A = playerNameController.text1;
        B = playerNameController.text2;
        firstPlayerTurn1.setText(A + "'s Score");
        firstPlayerTurn2.setText(B + "'s Score");

        dictionary = new Dictionary();
//        Circle circle=new Circle(19);
//        circle.setFill(Color.GOLD);
//        gridPane.setShape(circle);

        for (int i = 0; i < 17; i++) {
            for (int j = 0; j < 16; j++) {

                //turn label swap
                turnswap();


//                Circle circle = new Circle(19);

                double sideLength = 19;
                double centerX = 3;
                double centerY = 3;

                // Create the points for the hexagon
                double[] points = calculateHexagonPoints(centerX, centerY, sideLength);

//                 Create the polygon
                Polygon circle = new Polygon(points);
//                circle.setFill(Color.RED);

//                Rectangle circle=new Rectangle(38,38);
//                circle.setStyle("st/text-field");
//                circle.getStylesheets().add("text-field");
//                if (j % 2 == 0)                  //not working
//                    circle.setFill(Color.GOLD);
//                else
//                    circle.setFill(Color.BLUE);


                textField[i][j] = new TextField();
                textField[i][j].setShape(circle);
                textField[i][j].setOpacity(15);
                textField[i][j].setMaxSize(38, 38);
                textField[i][j].setAlignment(Pos.CENTER);
//              textField[i][j].getStylesheets().add("text-field");

                String s1 = "-fx-background-color:linear-gradient(from 0.0% 0.0% to 100.0% 0.0%, #00c6fdff 0.0%, #fdfc1dff 100.0%);-fx-text-fill:linear-gradient(from 100.0% 0.0% to 99.7% 81.3%, #0011ffff 0.0%, #ff0000ff 53.542%, #ae00a9ff 100.0%);";
                String s2 = "-fx-background-color:linear-gradient(from 0.0% 0.0% to 100.0% 0.0%, #fd6d7eff 0.0%, #bee8fdff 100.0%);-fx-text-fill:linear-gradient(from 60.6% 13.0% to 100.0% 66.3%, #ce1616ff 0.0%, #120fbaff 100.0%);";
                if ((j % 2 == 0 && i % 2 == 0) || (j % 2 == 1 && i % 2 == 1))
                    textField[i][j].setStyle(s1);
                else textField[i][j].setStyle(s2);


//                textField[i][j].setOpacity(.1);
                int I = i;
                int J = j;
//                textField[i][j].setOnKeyPressed(event->{
//                    if(textField[I][J].getText().length()>1){
//                        event.consume();
//                    }
//                });

                // Event handler on textProperty
                textField[i][j].textProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue.length() >= 1) {
                        String string = newValue.substring(0, 1);
                        //                    System.out.println(string);


                        if (string.charAt(0) <= 'z' && string.charAt(0) >= 'A') {
                            textField[I][J].setText(string); // Restrict to the first character
//                            textField[I][J].setDisable(true); // Disable the TextField
                            textField[I][J].setEditable(false); // not editable
                            turnswap();  //swap your turn Label...
                        } else {
                            textField[I][J].clear();
                        }
                    }
                });

                textField[i][j].setOnMouseClicked(event -> {

                    clickSound();
                    int lengthCount;
                    String s;
                    try {
                        s = textField[I][J].getText();
                        lengthCount = s.length();
                    } catch (Exception e) {
                        lengthCount = 0;
                        s = "";
                    }

                    if (event.getClickCount() >= 1 && lengthCount > 0) {
                        gaming(I, J, s);
                    } else {
                        textField[I][J].clear();
                    }

                });

                String st = textField[I][J].getStyle();
                TextField t = textField[I][J];


//                textField[I][J].setOnMouseEntered(event -> textField[I][J].setBackground(Background.fill(Color.RED)));
//
//
//                textField[I][J].setOnMouseExited(event ->textField[I][J].setBackground(Background.fill(Color.GREEN)));
//



                textField[i][j].setFont(Font.font("Verdana", FontWeight.BOLD, 18));

//                textField[i][j].setOnAction(new EventHandler<ActionEvent>() {
//                    @Override
//                    public void handle(ActionEvent event) {
//
//                    }
//                });    //It work when press inter button on keyboard


//              gridPane.add(circle,i,j);
                gridPane.add(textField[i][j], i, j);
            }
        }
    }
}
