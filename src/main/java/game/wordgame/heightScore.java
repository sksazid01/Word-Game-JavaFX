package game.wordgame;

public class heightScore {
    static int score=40;

    public static void increaseScore(int a, int b){
            score+=Math.max(a,b);

    }

    public static int getScore(){
        return score;
    }


    public static void resetScore(){
        score=0;
    }

}
