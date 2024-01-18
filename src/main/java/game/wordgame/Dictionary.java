package game.wordgame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Dictionary {
    HashMap<String, Boolean> map = new HashMap<>();

    Dictionary() {
        File file = null;

        try {
//            file = new File(getClass().getResource("BanglaDictionary_3+ character.txt").toURI());
            file = new File(getClass().getResource("test.txt").toURI());
        } catch (Exception e) {
            System.out.println("Sk-file == " + e);
        }

        if (file.exists()) {
            Scanner sc = null;

            try {
                sc = new Scanner(file);
            } catch (FileNotFoundException e) {
                System.out.println("Sk-sc == " + e);
            }


            while (sc.hasNextLine()) {
                map.put(sc.nextLine(), true);
            }

            System.out.println("exits dictionary");
        } else System.out.println("not exist this dictionary  .... sazid");


//        try (BufferedReader reader = new BufferedReader(new FileReader("sorted_all_word.txt"))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
////                System.out.println(line);
//                map.put(line, true);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        try (BufferedReader reader = new BufferedReader(new FileReader("sorted_all_word.txt"))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    public boolean isExist(String s) {
        if (map.get(s) != null) return true;  //null != NULL
        else return false;
    }

    public void setFalse(String s) {
        map.put(s, null);
    }


    public static void main(String[] args) throws FileNotFoundException {
        Dictionary dictionary = new Dictionary();
        if (dictionary.isExist("good"))
            System.out.println("TRUE");
        else System.out.println("FALSE");
    }
}

