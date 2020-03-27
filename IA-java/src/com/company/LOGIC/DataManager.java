package com.company.LOGIC;

import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DataManager {

    public Game lotto;
    public Game miniLotto;
    public Game multiLotto;
    public Game euroJack;

    public DataManager() {

        // REFRESH WEB DATA WITH PYTHON
        // DataDownloader1.refreshData();

        // READ JSON DATA
        String path = "";
        JSONParser jsonParser =  new JSONParser();

        String gameOneName = "lotto";
        String fileNameOne = path + gameOneName + "-history.txt";
        BufferedReader lottoFile = DataReader.readFile (fileNameOne);
        ArrayList<String> lottoJSONList = DataReader.parseFileContent (lottoFile);
        ArrayList<Draw> lottoHistory = DataReader.convertStringArrayToDrawHistory(lottoJSONList);

        String gameTwoName = "mini-lotto";
        String fileNameTwo = path + gameTwoName + "-history.txt";
        BufferedReader miniLottoFile = DataReader.readFile (fileNameTwo);
        ArrayList<String> miniLottoJSONList = DataReader.parseFileContent (miniLottoFile);
        ArrayList<Draw> miniLottoHistory = DataReader.convertStringArrayToDrawHistory(miniLottoJSONList);

        String game3Name = "multi-lotto";
        String file3Two = path + gameTwoName + "-history.txt";
        BufferedReader multiLottoFile = DataReader.readFile (fileNameTwo);
        ArrayList<String> multiLottoJSONList = DataReader.parseFileContent (miniLottoFile);
        ArrayList<Draw> multiLottoHistory = DataReader.convertStringArrayToDrawHistory(miniLottoJSONList);

        this.lotto = new Game ("Lotto", "../../../../img/lotto.png", "1957-03-07", 49, 6, lottoHistory);
        this.miniLotto = new Game ("Mini Lotto", "../../../../img/miniLotto.png", "30-12-1981", 42, 5, miniLottoHistory);
        this.multiLotto = new Game ("Multi Lotto", "../../../../img/multiLotto.png", "18-03-1996", 80, 20, multiLottoHistory);


        Draw draw1 = new Draw(1, "21-02-2020", new ArrayList<Integer> (Arrays.asList(1,2,7,11,34,16)));
        Draw draw2 = new Draw(2, "28-02-2020", new ArrayList<Integer> (Arrays.asList(1,22,17,12,24,36)));
        Draw draw3 = new Draw(3, "25-03-2020", new ArrayList<Integer> (Arrays.asList(9,23,5,11,37,6)));

       this.euroJack = new Game ("Euro Jack", "../../../../img/euroJack.png", "30-12-2000", 49, 6, new ArrayList<Draw>(Arrays.asList(draw1, draw2, draw3, draw1, draw2, draw3, draw1, draw2, draw3, draw1, draw2, draw3, draw1, draw2, draw3, draw1, draw2, draw3, draw1, draw2, draw3, draw1, draw2, draw3)));

        // The same with DataReader class
        // DataReader lottoDataReader = new DataReader(gameOneName);
        // ArrayList<Draw> lottoHistory = lottoDataReader.createDrawHistory();

    }
    public static void main(String[] args) {
        DataManager app = new DataManager();
        app.chooseGame();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // MENUS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void chooseGame(){

        int game  = -1;
        while (game != 0) {
            game = menuChooseGame();
            switch (game) {
                case 1:
                    chooseStrategy(this.lotto);
                    break;
                case 2:
                    chooseStrategy(this.miniLotto);
                    break;
                case 3:
                    chooseStrategy(this.multiLotto);
                    break;
                case 4:
                    chooseStrategy(this.euroJack);
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
                "\n\nMini-Lotto Game (1981-2020): 42 balls ...........................................press: 2 " +
                "\n\nMulti-Lotto Game (1998-2020): 80 balls ..........................................press: 3 " +
                "\n\nEuroJack Game (2001-2020): 49 balls .............................................press: 4 ";
        System.out.println(massage);
        int choice = scanner.nextInt();
        return choice;
    }

    public static void chooseStrategy(Game currentGame){
        String name = currentGame.getName();
        String lastUpdate = currentGame.getHistory().get(0).getDate();
        int strategy = -1;
        while (strategy != 0) {
            strategy = menuChooseStrategy(name);
            switch (strategy) {
                case 1:
                    System.out.print("\n"+ name.toUpperCase()+ " GAME : Last draw " + lastUpdate + "\nBalls best choice for next drawing according to Strategy 1: ");
                    System.out.println("Random choice from twenty most frequently winning\n");
                    List<Ball> results1 = currentGame.getBallStrategy1();
                    printArray(results1, "to string");
                    break;
                case 2:
                    System.out.print("\n"+ name.toUpperCase()+ " GAME : Last draw " + lastUpdate + "\nBalls best choice for next drawing according to Strategy 2: ");
                    System.out.println("Random choice from twenty least frequently winning\n");
                    List<Ball> results2 = currentGame.getBallStrategy2();
                    printArray(results2, "to string");

                    break;
                case 3:
                    System.out.print("\n"+ name.toUpperCase()+ " GAME : Last draw " + lastUpdate + "\nBalls best choice for next drawing according to Strategy 3: ");
                    System.out.println("Best Accelaration Index from twenty most frequently winning\n");
                    List<Ball> results3 = currentGame.getBallStrategy3();
                    printArray(results3, "to string");
                    break;
                case 4:
                    System.out.print("\n"+ name.toUpperCase()+ " GAME : Last draw " + lastUpdate + "\nBalls best choice for next drawing according to Strategy 4: ");
                    System.out.println("Best Accelaration Index from twenty least frequently winning\n");
                    List<Ball> results4  = currentGame.getBallStrategy4();
                    printArray(results4, "to string");
                    break;
                case 5:
                    System.out.println("\n"+ name.toUpperCase()+ " GAME : Last draw " + lastUpdate + "\nStatistics for twenty most frequently winning\n");
                    List<Ball> balls1 = currentGame.getTwentyMostFrequentlyWinning();
                    printArray(balls1, "to string");
                    break;
                case 6:
                    System.out.println("\n"+ name.toUpperCase()+ " GAME : Last draw " + lastUpdate + "\nStatistics for twenty least frequently winning\n");
                    List<Ball> balls2 = currentGame.getTwentyLeastFrequentlyWinning();
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
