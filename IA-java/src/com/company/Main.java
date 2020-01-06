package com.company;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        // Read data
        String gameName = "lotto";
        String filename = gameName + "_history.json";
        FileReader lottoFile = readFile (filename);
        JSONParser jsonParser =  new JSONParser();
        JSONArray lottoJSONList = parseFileContent (lottoFile, jsonParser);
        ArrayList<Draw> lottoHistory = convertJSONArrayToDrawHistory(lottoJSONList);
        Game lotto = new Game ("lotto", "1957-03-07", 49, 6, lottoHistory);


        //printStrategy(strategy1(lotto));
        //printStrategy(strategy2(lotto));
        //printStrategy(strategy3(lotto));
        //printStrategy(strategy4(lotto));
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                      READ THE DATA
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////



    public static FileReader readFile (String filename){
        // FileReader to read file
        try  {
            FileReader reader  = new FileReader(filename);
            return reader;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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
        System.out.println(date);
        //Get id
        Long id = (Long)element.get("lp");
        System.out.println(id);
        //Get numbers
        JSONArray jsonArray = (JSONArray) element.get("numbers");
        System.out.println(jsonArray);
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
    //                      Based on the historical data program will determine:
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
        result[0] = ball.getLast10drawsWinning();
        result[0] = ball.getLast15drawsWinning();
        return result;
    }
    // Winning Percent for the last 5, 10 and 15 draws for a number
    public static int[] WinningPercentForLastDraws(Game game, String number) {
        int index = Integer.parseInt(number) - 1;
        Ball ball =  game.getBallStatistics().get(index);
        int[] result = new int[3];
        result[0] = ball.getLast5drawsWinning()/5;
        result[0] = ball.getLast10drawsWinning()/10;
        result[0] = ball.getLast15drawsWinning()/15;
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
    public static ArrayList<Ball> twentyMostFrequentlyWinning(Game game) {
        ArrayList<Ball> array = game.getBallStatistics();
        System.out.println("Array size:" + array.size());
        array.sort(game.comparatorByPercentWinningDescending);
        System.out.println("Array size:" + array.size());
        return (ArrayList<Ball>) array.subList(0, 20);
    }

    // 20 least frequently winning numbers in the entire history
    public static ArrayList<Ball>  twentyLeastFrequentlyWinning(Game game) {
        ArrayList<Ball> array = game.getBallStatistics();
        array.sort(game.comparatorByPercentWinningAscending);
        return (ArrayList<Ball>) array.subList(0, 20);
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                  Program will allow user to select a strategy:
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////


    //    Most Frequent Random
    //    From the 20 most frequently winning numbers program will randomly choose a set of numbers, containing 6 numbers each for Lotto and ... numbers each for EuroJackpot.
    public static Ball[] strategy1(Game game){
        ArrayList<Ball> balls = twentyMostFrequentlyWinning(game);
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
    public static Ball[] strategy2(Game game){
        ArrayList<Ball> balls = twentyLeastFrequentlyWinning(game);
        Random randomDrawer = new Random();
        Ball[] result = new Ball[game.getNumberOfSelectedBalls()];
        for (int i=0; i < result.length; i++) {
            int index = randomDrawer.nextInt(balls.size());
            result[i] = balls.get(index);
            balls.remove(index);
        }
        //return result;
        return new Ball[1];
    }

    //    Most Frequent Random with “Acceleration”
    //    For each number in set of “20 most frequently winning numbers” the program will calculate Index of Acceleration using the following formula:
    //    Index of acceleration = [Winning Percent from the last 15 draws] + 1.25 * [Winning Percent from the last 10 draws] + 1.5 * [Winning Percent from the last 5 draws]
    //    Program will then choose 6 numbers with the highest IoA.
    public static ArrayList<Ball> strategy3(Game game){
        ArrayList<Ball> twentyMost = twentyMostFrequentlyWinning(game);
        twentyMost.sort(new Comparator<Ball>() {
            public int compare(Ball ball1, Ball ball2) {
                return ball1.getIndexOfAcceleration() > ball2.getIndexOfAcceleration()  ? 1 : ball1.getIndexOfAcceleration()  < ball2.getIndexOfAcceleration()  ? -1 : 0;
            }
        });
        return (ArrayList<Ball>) twentyMost.subList(0, 6);
    }

    //    Least Frequent Random with “Acceleration”
    //    For each number in set of “20 least frequently winning numbers” the program will calculate Index of Acceleration (IoA) using the following formula:
    //    Index of acceleration = [Winning Percent from the last 15 draws] + 1.25 * [Winning Percent from the last 10 draws] + 1.5 * [Winning Percent from the last 5 draws]
    //    Program will then choose 6 numbers with the highest IoA.
    public static ArrayList<Ball> strategy4(Game game){
        ArrayList<Ball> twentyMost = twentyLeastFrequentlyWinning(game);
        twentyMost.sort(game.comparatorByAccelerationIndexDescending);
        return (ArrayList<Ball>) twentyMost.subList(0, 6);
    }

    public static void printStrategy (Ball[] list){
        for (int i = 0; i < list.length-1; i++ ){
            System.out.print(list[i].getNumber() + ",");
        }
        System.out.println(list[list.length-1].getNumber());
    }
    public static void printStrategy (ArrayList<Ball> list){
        for (int i = 0; i < list.size()-1; i++ ){
            System.out.print(list.get(i).getNumber() + ",");
        }
        System.out.println(list.get(list.size()-1).getNumber());
    }
}
