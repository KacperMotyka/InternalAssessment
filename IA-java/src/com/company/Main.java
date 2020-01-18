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

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // READ THE DATA
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // MENUS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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
        }
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
}
