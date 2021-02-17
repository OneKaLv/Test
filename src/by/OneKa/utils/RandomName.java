package by.OneKa.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomName {

    private static List<Character> letters = new ArrayList<>();

    public static String getRandomName(){
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            sb.insert(i, letters.get(random.nextInt(62)));
        }
        return sb.toString();
    }

    public static void initLetters(){
        for(int i = 0; i<62; i++){
            if(i<26) {
                letters.add((char) (0x61 + i));
            }
            if(i>=26 && i<52){
                letters.add((char) (0x41+i-26));
            }
            if(i>=52){
                letters.add((char) (0x30+i-52));
            }
        }
    }
}
