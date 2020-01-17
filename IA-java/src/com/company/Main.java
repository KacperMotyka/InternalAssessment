package com.company;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Read data
        String path = "../";
        String gameName = "lotto";
        String filename = path + gameName + "_history.json";
        FileReader lottoFile = readFile (filename);
        JSONParser jsonParser =  new JSONParser();
        JSONArray lottoJSONList = parseFileContent (lottoFile, jsonParser);
        ArrayList<Draw> lottoHistory = convertJSONArrayToDrawHistory(lottoJSONList);
        Game lotto = new Game ("lotto", "1957-03-07", 49, 6, lottoHistory);

        // The same with DataReader class
        // DataReader lottoDataReader = new DataReader(gameName);
        // ArrayList<Draw> lottoHistory = lottoDataReader.createDrawHistory();

        choiceToMethod(lotto);

    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                      READ THE DATA
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////



    public static FileReader readFile (String filename){
        // FileReader to read file
        try  {
            FileReader reader = new FileReader(filename);
            return reader;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void readFile2 (String filename){
        try {
            ArrayList<String> list = new ArrayList<String>();
            list.get(10);
        } catch (Exception bug) {
            bug.printStackTrace();
            System.out.println("Cos poszlo nie tak, ale to nic");
        }
    }
    public static JSONArray parseFileContent (FileReader readFile, JSONParser jsonParser){
        try  {
            //JSON object to parse JSON file
            Object obj = jsonParser.parse(readFile);
            JSONArray jsonList = (JSONArray) obj;
            //System.out.println(jsonList);
            return jsonList;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public  static ArrayList<Draw> convertJSONArrayToDrawHistory(JSONArray jsonList) {
        ArrayList<Draw> history = new ArrayList<Draw>();
        //Iterate over array
        for (Object element : jsonList) {
            JSONObject jsonObject = (JSONObject) element;
            Draw draw = convertJSONObjectToDraw(jsonObject);
            history.add(draw);
        }
        return history;
    }

    public static Draw convertJSONObjectToDraw(JSONObject element) {
        //Get date
        String date = (String) element.get("date");
        //System.out.println(date);
        //Get id
        Long id = (Long)element.get("lp");
        // System.out.println(id);
        //Get numbers
        JSONArray jsonArray = (JSONArray) element.get("numbers");
        // System.out.println(jsonArray);
        ArrayList<Integer> list = new ArrayList<Integer>();
        if (jsonArray != null) {
            int len = jsonArray.size();
            for (int i = 0; i < len; i++) {
                list.add(Integer.parseInt(jsonArray.get(i).toString()));
            }
        }
        // create Draw object
        return new Draw(id, date, list);
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //               Based on the historical data program will determine th following STATISTICS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // Entire History Winning Frequency for a number
    public static double EHWinningFrequency (Game game, String number){
        int index = Integer.parseInt(number) - 1;
        return game.getBallStatistics().get(index).getTotalNumberOfWinning();
    }
    //  Entire History Winning Percent for a number = Number of draws in which the number won /  Number of all draws
    public static double EHWinningPercent (Game game, String number){
        int index = Integer.parseInt(number) - 1;
        return game.getBallStatistics().get(index).getTotalPercentOfWinning();
    }

    // Winning Frequency for the last 5, 10 and 15 draws for a number
    public static int[] WinningFrequencyForLastDraws(Game game, String number) {
        int index = Integer.parseInt(number) - 1;
        Ball ball =  game.getBallStatistics().get(index);
        int[] result = new int[3];
        result[0] = ball.getLast5drawsWinning();
        result[1] = ball.getLast10drawsWinning();
        result[2] = ball.getLast15drawsWinning();
        return result;
    }
    // Winning Percent for the last 5, 10 and 15 draws for a number
    public static double[] WinningPercentForLastDraws(Game game, String number) {
        int index = Integer.parseInt(number) - 1;
        Ball ball =  game.getBallStatistics().get(index);
        double[] result = new double[3];
        result[0] = ball.getLast5drawsWinning()/5;
        result[1] = ball.getLast10drawsWinning()/10;
        result[2] = ball.getLast15drawsWinning()/15;
        return result;
    }

    // Entire History Winning Frequency for each number
    public static int[] EHWinningFrequency (Game game){
        int n = game.getTotalNumberOfBalls();
        int[] array = new int[n+1];
        for (int index = 1; index <= n; index++){
            array[index] = game.getBallStatistics().get(index).getTotalNumberOfWinning();
        }
        return array;
    }
    //  Entire History Winning Percent for each number = Number of draws in which the number won /  Number of all draws
    public static double[] EHWinningPercent (Game game){
        int n = game.getTotalNumberOfBalls();
        double[] array = new double[n+1];
        for (int i = 1; i <= n; i++){
            array[i] = game.getBallStatistics().get(i).getTotalPercentOfWinning();
        }
        return array;
    }
    // 20 most frequently winning numbers in the entire history
    public static List<Ball> twentyMostFrequentlyWinning(Game game) {
        ArrayList<Ball> array = (ArrayList<Ball>) game.getBallStatistics().clone();
        // System.out.println("Array size:" + array.size());
        array.sort(game.comparatorByPercentWinningDescending);
        // System.out.println("Array size:" + array.size());
        return array.subList(0, 20);
    }

    // 20 least frequently winning numbers in the entire history
    public static List<Ball>  twentyLeastFrequentlyWinning(Game game) {
        ArrayList<Ball> array = (ArrayList<Ball>) game.getBallStatistics().clone();
        // System.out.println("Array size:" + array.size());
        array.sort(game.comparatorByPercentWinningAscending);
        // System.out.println("Array size:" + array.size());
        return array.subList(0, 20);
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                  Program will allow user to select a STRATEGY:
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////


    //    Most Frequent Random
    //    From the 20 most frequently winning numbers program will randomly choose a set of numbers, containing 6 numbers each for Lotto and ... numbers each for EuroJackpot.
    public static Ball[] calculateStrategy1(Game game){
        List<Ball> balls = twentyMostFrequentlyWinning(game);
        // printArray(balls);
        Random rand = new Random();
        Ball[] result = new Ball[game.getNumberOfSelectedBalls()];
        for (int i=0; i < result.length; i++) {
            int index = rand.nextInt(balls.size());
            result[i] = balls.get(index);
            balls.remove(index);
        }
        return result;
    }

    //    Least Frequent Random
    //    From the 20 least frequently winning program will randomly choose a set of numbers, containing 6 numbers each.
    public static Ball[] calculateStrategy2(Game game){
        List<Ball> balls = twentyLeastFrequentlyWinning(game);
        // printArray(balls);
        Random randomDrawer = new Random();
        Ball[] result = new Ball[game.getNumberOfSelectedBalls()];
        for (int i=0; i < result.length; i++) {
            int index = randomDrawer.nextInt(balls.size());
            result[i] = balls.get(index);
            balls.remove(index);
        }
        return result;
    }

    //    Most Frequent Random with “Acceleration”
    //    For each number in set of “20 most frequently winning numbers” the program will calculate Index of Acceleration using the following formula:
    //    Index of acceleration = [Winning Percent from the last 15 draws] + 1.25 * [Winning Percent from the last 10 draws] + 1.5 * [Winning Percent from the last 5 draws]
    //    Program will then choose 6 numbers with the highest IoA.
    public static List<Ball> calculateStrategy3(Game game){
        List<Ball> twentyMost = twentyMostFrequentlyWinning(game);
        //printArray(twentyMost);
        twentyMost.sort(game.comparatorByAccelerationIndexDescending);
        return twentyMost.subList(0, 6);
    }

    //    Least Frequent Random with “Acceleration”
    //    For each number in set of “20 least frequently winning numbers” the program will calculate Index of Acceleration (IoA) using the following formula:
    //    Index of acceleration = [Winning Percent from the last 15 draws] + 1.25 * [Winning Percent from the last 10 draws] + 1.5 * [Winning Percent from the last 5 draws]
    //    Program will then choose 6 numbers with the highest IoA.
    public static List<Ball> calculateStrategy4(Game game){
        List<Ball> twentyLeast = twentyLeastFrequentlyWinning(game);
        //printArray(twentyLeast);
        twentyLeast.sort(game.comparatorByAccelerationIndexDescending);
        return twentyLeast.subList(0, 6);
    }

    public static void printArray(Ball[] list){
        for (int i = 0; i < list.length-1; i++ ){
            System.out.print(list[i].getNumber() + ",");
        }
        System.out.println(list[list.length-1].getNumber());
    }
    public static void printArray(List<Ball> list){
        for (int i = 0; i < list.size()-1; i++ ){
            System.out.print(list.get(i).getNumber() + ",");
        }
        System.out.println(list.get(list.size()-1).getNumber());
    }


    public static int menuChooseStrategy(){
        Scanner scanner = new Scanner(System.in);
        String massage = "Choose a strategy: " +
                "\nStrategy 1: press: 1 " +
                "\nStrategy 2: press: 2 " +
                "\nStrategy 3: press: 3 " +
                "\nStrategy 4: press: 4 ";
        System.out.println(massage);
        int choice = scanner.nextInt();
        return choice;
    }

    public static void choiceToMethod(Game currentGame){
        int strategy = menuChooseStrategy();
        switch (strategy) {
            case 1:
                System.out.println("Results strategy 1");
                Ball[] results1 = calculateStrategy1(currentGame);
                printArray(results1);
                break;
            case 2:
                System.out.println("Results strategy 2");
                Ball[] results2 = calculateStrategy2(currentGame);
                printArray(results2);
                break;
            case 3:
                System.out.println("Results strategy 3");
                List<Ball> results3 = calculateStrategy3(currentGame);
                printArray(results3);
                break;
            case 4:
                System.out.println("Results strategy 4");
                printArray(calculateStrategy4(currentGame));
                break;
        }
    }
}
