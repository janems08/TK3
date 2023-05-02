import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/*
 * @author Jane Mitaria Sinambela
 * */
public class Main {

    public static final int minimumCharacter = 3;

    public static final int maximumCharacter = 6;

    public static final int MaximumChanceInputCharacter = 10;

    public static final int scoreCorrectAnswer = 10;

    public static final int minimumScoreEachLevel = 70;

    public static final int maximumLevel = 3;

    public static List<List<String>> puzzleWord = listPuzzleWordEachLevel();

    public static List<List<String>> listEnglishWord = listOfEnglishWord();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        printRulesGame();

        int totalScore = 0;

        for( int level = 1; level <= maximumLevel; level++){
            System.out.println("Level " + level);
            System.out.println("-------");

            //printing list puzzle word
            for(String s : puzzleWord.get(--level)){
                System.out.print(s + " ");
            }
            System.out.println();

            //temporary score each level
            int tempScoreEachLevel = 0;

            List<String> tempInputWords = new ArrayList<>();

            for (int i = 1; i <= MaximumChanceInputCharacter; i++){
                System.out.println(i + "> Your Answer : ");
                String inputWord = sc.nextLine();

                // check if inputWord has already exsist
                if (tempInputWords.contains(inputWord)){
                    System.out.println("You had already type this word before..");
                }
                // check if inputWord match with rules of the coepoe puzzle word
                else if (isCorrectPuzzleWord(inputWord, level)){
                    tempScoreEachLevel += scoreCorrectAnswer;
                    System.out.println("#Right. Score : " + tempScoreEachLevel);
                }

                tempInputWords.add(inputWord);
            }

            System.out.println("You had already answer " + MaximumChanceInputCharacter+ " times with " + tempScoreEachLevel/MaximumChanceInputCharacter + " right answers..");

            //check if score not already passed from the minimim score each level
            if (tempScoreEachLevel < minimumScoreEachLevel){
                System.out.println("You lost!! Try Again..");
                System.out.println("Do you want to retry [y/t] ?");
                String temp = sc.nextLine();

                //if user do not continue the coepoe puzzle game
                if(temp.charAt(0) == 't'){
                    System.out.println();
                    break;
                }else {
                    level = 0;
                }
            } else {
                level++;
                totalScore += tempScoreEachLevel;
            }

            if (level == 3 && tempScoreEachLevel >= minimumScoreEachLevel) {
                System.out.println("Overall score : " + totalScore);
                System.out.println("You Win!!");
                System.out.println("Press Enter to exit..");
                sc.nextLine();
            }
        }

    }

    private static boolean isCorrectPuzzleWord(String inputWord, int level) {
        //check length of word
        if (inputWord.length() < minimumCharacter || inputWord.length() > maximumCharacter){
            return false;
        }

        //check is correct combination puzzle of word
        if (!isCorrectPuzzleCombination(inputWord, level)){
            return false;
        }

        //check if word already correct english word
        if (!isValidEnglishWord(inputWord, level)){
            return false;
        }
        return true;
    }

    private static boolean isValidEnglishWord(String inputWord, int level) {
        List<String> listValidEnglishWord =  listEnglishWord.get(level);
        for (int i = 0; i<inputWord.length(); i++){
            if(!listValidEnglishWord.contains(inputWord)) {
                return false;
            }
        }
        return true;
    }

    private static List<List<String>> listOfEnglishWord() {
        List<List<String>> listValidWord = new ArrayList<>();
        listValidWord.add(new ArrayList<>(Arrays.asList("del", "die", "dit", "eld", "led", "lie", "lid", "ted", "lei", "let", "deil", "deli", "delt", "diel", "diet", "dite", "edit", "idle", "lied", "tilde", "tiled", "title", "tilted", "titled")));
        listValidWord.add(new ArrayList<>(Arrays.asList("ace", "can", "cee", "sac", "sec", "ane", "ens", "nae", "nee", "sae", "san", "sea", "see", "sen", "aces", "acne", "cane", "cans", "case", "cees", "scan", "anes", "ease", "esne", "naes", "sane", "seen","sene", "acnes", "canes", "cease", "cense","scena", "scene","encase","seance","seneca")));
        listValidWord.add(new ArrayList<>(Arrays.asList("ken", "kor", "oke", "hen", "her", "hoe", "hon", "noh", "rho", "eon","erh", "nor", "one", "ore", "roe", "hoke", "honk", "honk", "hork", "okeh", "keno", "kern", "kore", "hern", "hero", "hoer", "hone","horn", "reno", "krone", "heron", "honer", "honker")));
        return listValidWord;
    }
    private static boolean isCorrectPuzzleCombination(String inputWord, int level) {
        List<String> puzzleWordEachlevel = puzzleWord.get(level);
        for (int i = 0; i<inputWord.length(); i++){
            if (!puzzleWordEachlevel.contains(String.valueOf(inputWord.charAt(i)))){
                return false;
            }
        }

        return true;
    }

    private static void printRulesGame() {
        System.out.println("Coepoe Word Puzzle");
        System.out.println("==================");
        System.out.println("Rules : ");
        System.out.println("1. Create a word using given characters, min 3 characters & max 6 characters.");
        System.out.println("2. Each level, You have 10 chances to answer correctly.");
        System.out.println("3. To get through to next level, You have to score 70 points each level.");
    }

    private static List<List<String>> listPuzzleWordEachLevel() {
        List<List<String>> listCharacter = new ArrayList<>();
        listCharacter.add(new ArrayList<>(Arrays.asList("d", "e", "t", "t", "l", "i")));
        listCharacter.add(new ArrayList<>(Arrays.asList("s", "e", "c", "a", "e", "n")));
        listCharacter.add(new ArrayList<>(Arrays.asList("h", "k", "r", "n", "e", "o")));

        return listCharacter;
    }


}