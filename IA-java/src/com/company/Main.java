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

        // REFRESH WEB DATA
        //DataDownloader.refreshHTMLFilesJava("lotto");
        //DataDownloader.refreshHTMLFilesJava("mini-lotto");
        //DataDownloader.extractDataFromHTML("lotto");
        //DataDownloader.extractDataFromHTML("mini");
        //DataDownloader.refreshJSONData("lotto");
        //DataDownloader.refreshJSONData("mini");



        /*
        // READ JSON DATA
        String path = "";
        JSONParser jsonParser =  new JSONParser();

        String gameOneName = "lotto";
        String fileNameOne = path + gameOneName + "_history.json";
        FileReader lottoFile = DataReader.readFile (fileNameOne);
        JSONArray lottoJSONList = DataReader.parseFileContent (lottoFile, jsonParser);
        ArrayList<Draw> lottoHistory = DataReader.convertJSONArrayToDrawHistory(lottoJSONList);

        String gameTwoName = "mini-lotto";
        String fileNameTwo = path + gameTwoName + "_history.json";
        FileReader miniLottoFile = DataReader.readFile (fileNameTwo);
        JSONArray miniLottoJSONList = DataReader.parseFileContent (miniLottoFile, jsonParser);
        ArrayList<Draw> miniLottoHistory = DataReader.convertJSONArrayToDrawHistory(miniLottoJSONList);

        Game lotto = new Game ("lotto", "1957-03-07", 49, 6, lottoHistory);
        Game miniLotto = new Game ("miniLotto", "30-12-1981", 42, 5, miniLottoHistory);

        menuGame(lotto, miniLotto);
        */


        // The same with DataReader class
        // DataReader lottoDataReader = new DataReader(gameOneName);
        // ArrayList<Draw> lottoHistory = lottoDataReader.createDrawHistory();

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // READ THE DATA
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // przeniesione do Reader

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // MENUS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static void menuGame(Game lotto, Game miniLotto){
        int game = menuChooseGame();

        switch (game) {
            case 1:
                menuStrategy(lotto);
                break;
            case 2:
                menuStrategy(miniLotto);
                break;
        }
    }
    public static int menuChooseGame(){
        Scanner scanner = new Scanner(System.in);
        String massage = "Choose a Game: " +
                "\nLotto - press: 1 " +
                "\nMiniLotto - press: 2 ";
        System.out.println(massage);
        int choice = scanner.nextInt();
        return choice;
    }

    public static void menuStrategy(Game currentGame){
        int strategy = menuChooseStrategy();
        switch (strategy) {
            case 1:
                System.out.println("Results strategy 1");
                Ball[] results1 = currentGame.calculateStrategy1();
                printArray(results1);
                break;
            case 2:
                System.out.println("Results strategy 2");
                Ball[] results2 = currentGame.calculateStrategy2();
                printArray(results2);

                break;
            case 3:
                System.out.println("Results strategy 3");
                List<Ball> results3 = currentGame.calculateStrategy3();
                printArray(results3);
                break;
            case 4:
                System.out.println("Results strategy 4");
                printArray(currentGame.calculateStrategy4());
                break;
            case 5:
                System.out.println("Twenty most frequently winning");
                List<Ball> balls1 = currentGame.twentyMostFrequentlyWinning();
                printArray(balls1, "to string");
                break;
            case 6:
                System.out.println("Twenty least frequently winning");
                List<Ball> balls2 = currentGame.twentyLeastFrequentlyWinning();
                printArray(balls2, "to string");
                break;
        }
    }

    public static int menuChooseStrategy(){
        Scanner scanner = new Scanner(System.in);
        String massage = "What do you want to see?: " +
                "\nStrategy 1 - press: 1 " +
                "\nStrategy 2 - press: 2 " +
                "\nStrategy 3 - press: 3 " +
                "\nStrategy 2 - press: 4 " +
                "\nTwenty most frequently winning - press: 5 " +
                "\nTwenty least frequently winning - press: 6 ";
        System.out.println(massage);
        int choice = scanner.nextInt();
        return choice;
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // PRINTING
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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

    public static void printArray(List<Ball> list, String attribute){
        if (attribute.equals("to string")) {
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i).toString());
            }
        }
    }
}
