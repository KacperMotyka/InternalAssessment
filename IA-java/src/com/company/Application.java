package com.company;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {

    Game lotto;
    Game miniLotto;

    public Application() {

        // REFRESH WEB DATA WITH PYTHON
        DataDownloader1.refreshData();

        // READ JSON DATA
        String path = "";
        JSONParser jsonParser =  new JSONParser();

        String gameOneName = "lotto";
        String fileNameOne = path + gameOneName + "-history.json";
        FileReader lottoFile = DataReader.readFile (fileNameOne);
        JSONArray lottoJSONList = DataReader.parseFileContent (lottoFile, jsonParser);
        ArrayList<Draw> lottoHistory = DataReader.convertJSONArrayToDrawHistory(lottoJSONList);

        String gameTwoName = "mini-lotto";
        String fileNameTwo = path + gameTwoName + "-history.json";
        FileReader miniLottoFile = DataReader.readFile (fileNameTwo);
        JSONArray miniLottoJSONList = DataReader.parseFileContent (miniLottoFile, jsonParser);
        ArrayList<Draw> miniLottoHistory = DataReader.convertJSONArrayToDrawHistory(miniLottoJSONList);

        this.lotto = new Game ("lotto", "1957-03-07", 49, 6, lottoHistory);
        this.miniLotto = new Game ("miniLotto", "30-12-1981", 42, 5, miniLottoHistory);

        // The same with DataReader class
        // DataReader lottoDataReader = new DataReader(gameOneName);
        // ArrayList<Draw> lottoHistory = lottoDataReader.createDrawHistory();

    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // MENUS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static void menuGame(Game lotto, Game miniLotto){

        int game  = -1;
        while (game != 0) {
            game = menuChooseGame();
            switch (game) {
                case 1:
                    menuStrategy(lotto);
                    break;
                case 2:
                    menuStrategy(miniLotto);
                    break;
            }
        }
    }
    public static int menuChooseGame(){
        Scanner scanner = new Scanner(System.in);
        String massage = "" +
                "\n------------------------------------------------------------------------------------------  " +
                "\n                                     CHOOSE A GAME" +
                "\n------------------------------------------------------------------------------------------  " +
                "\n\nExit ............................................................................press: 0 " +
                "\n\nLotto Game (1957-2020): 49 balls ................................................press: 1 " +
                "\n\nMini-Lotto Game (1981-2020): 42 balls ...........................................press: 2 ";
        System.out.println(massage);
        int choice = scanner.nextInt();
        return choice;
    }

    public static void menuStrategy(Game currentGame){
        String name = currentGame.getName();
        String lastUpdate = currentGame.getHistory().get(0).getDate();
        int strategy = -1;
        while (strategy != 0) {
            strategy = menuChooseStrategy(name);
            switch (strategy) {
                case 1:
                    System.out.print("\n"+ name.toUpperCase()+ " GAME : Last draw " + lastUpdate + "\nBalls best choice for next drawing according to Strategy 1: ");
                    System.out.println("Random choice from twenty most frequently winning\n");
                    Ball[] results1 = currentGame.calculateStrategy1();
                    printArray(results1, "to string");
                    break;
                case 2:
                    System.out.print("\n"+ name.toUpperCase()+ " GAME : Last draw " + lastUpdate + "\nBalls best choice for next drawing according to Strategy 2: ");
                    System.out.println("Random choice from twenty least frequently winning\n");
                    Ball[] results2 = currentGame.calculateStrategy2();
                    printArray(results2, "to string");

                    break;
                case 3:
                    System.out.print("\n"+ name.toUpperCase()+ " GAME : Last draw " + lastUpdate + "\nBalls best choice for next drawing according to Strategy 3: ");
                    System.out.println("Best Accelaration Index from twenty most frequently winning\n");
                    List<Ball> results3 = currentGame.calculateStrategy3();
                    printArray(results3, "to string");
                    break;
                case 4:
                    System.out.print("\n"+ name.toUpperCase()+ " GAME : Last draw " + lastUpdate + "\nBalls best choice for next drawing according to Strategy 4: ");
                    System.out.println("Best Accelaration Index from twenty least frequently winning\n");
                    List<Ball> results4  = currentGame.calculateStrategy4();
                    printArray(results4, "to string");
                    break;
                case 5:
                    System.out.println("\n"+ name.toUpperCase()+ " GAME : Last draw " + lastUpdate + "\nStatistics for twenty most frequently winning\n");
                    List<Ball> balls1 = currentGame.twentyMostFrequentlyWinning();
                    printArray(balls1, "to string");
                    break;
                case 6:
                    System.out.println("\n"+ name.toUpperCase()+ " GAME : Last draw " + lastUpdate + "\nStatistics for twenty least frequently winning\n");
                    List<Ball> balls2 = currentGame.twentyLeastFrequentlyWinning();
                    printArray(balls2, "to string");
                    break;
                case 7:
                    System.out.println("\n"+ name.toUpperCase()+ " GAME : Last draw " + lastUpdate + "\nStatistics for all balls\n");
                    List<Ball> balls3 = currentGame.allBalls();
                    printArray(balls3, "to string");
                    break;
            }
        }

    }

    public static int menuChooseStrategy(String name){
        Scanner scanner = new Scanner(System.in);
        String massage = ""+
                "\n------------------------------------------------------------------------------------------ " +
                "\n                         " + name.toUpperCase() + " GAME :  WHAT DO YOU WANT TO SEE ? " +
                "\n------------------------------------------------------------------------------------------ " +
                "\n\nReturn to choosing game............................................................press 0 " +
                "\nChoose balls according to Strategy 1 ..............................................press 1 " +
                "\nChoose balls according to Strategy 2 ..............................................press 2 " +
                "\nChoose balls according to Strategy 3 ..............................................press 3 " +
                "\nChoose balls according to Strategy 4 ..............................................press 4 " +
                "\nSee statistics for twenty most frequently winning..................................press 5 " +
                "\nSee statistics for twenty least frequently winning.................................press 6 " +
                "\nSee statistics for all balls.......................................................press 7 ";
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

    public static void printArray(Ball[] list, String attribute){
        if (attribute.equals("to string")) {
            for (int i = 0; i < list.length; i++) {
                System.out.println("----------------------------------------------------------------------------------------------------------------------------------------- ");
                System.out.println(list[i].toString());
            }
        }
    }
    public static void printArray(List<Ball> list, String attribute){
        if (attribute.equals("to string")) {
            for (int i = 0; i < list.size(); i++) {
                System.out.println("---------------------------------------------------------------------------------------------------------------------------------------- ");
                System.out.println(list.get(i).toString());
            }
        }
    }
    public static void printArray(ArrayList<Integer> list){
        for (int i = 0; i < list.size()-1; i++ ){
            System.out.print(list.get(i) + ",");
        }
        System.out.println(list.get(list.size()-1));
    }
}
