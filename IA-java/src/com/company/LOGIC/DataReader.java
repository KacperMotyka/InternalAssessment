package com.company.LOGIC;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.InputStreamReader;


import java.util.ArrayList;
import java.util.Arrays;

public class DataReader {

    // class attributes
    public static String jsonFilesPath = ".";
    public static JSONParser jsonParser =  new JSONParser();

    // object  attributes
    private String gameName;
    private String fileName;


    /// Constructor
    public DataReader(String gameName) {

        this.gameName = gameName;
        this.fileName = jsonFilesPath + gameName + "-history.json";
    }


    public static BufferedReader readFile (String filename){
        // FileReader to read file
        try  {

            //FileReader reader = new FileReader(filename);
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            return reader;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


/*
    public static JSONArray parseFileContent (FileReader reader, JSONParser jsonParser){
        try  {

            //JSON object to parse JSON file
            Object obj = jsonParser.parse(reader);
            JSONArray jsonList = (JSONArray) obj;
            return jsonList;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
*/

    public static ArrayList<String> parseFileContent (BufferedReader reader){
        ArrayList<String> textliste = new ArrayList<>();
        try  {
            String line = reader.readLine();
            while (line != null) {
                textliste.add(line);
                line = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return textliste;
    }

    public  static ArrayList<Draw> convertStringArrayToDrawHistory(ArrayList<String> textliste) {
        ArrayList<Draw> history = new ArrayList<Draw>();
        for (String line : textliste){
            String[] columns = line.split(" ");
            Draw draw = convertTEXTToDraw(columns);
            history.add(draw);
        }

        return history;
    }
    /*
    //System.out.println(jsonList);
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
*/
    public static Draw convertTEXTToDraw(String[] elements) {
        Long id = (Long) Long.parseLong(elements[0].replace('.',' ').trim());
        System.out.print(" Id " + id);
        String date = (String) elements[1];
        System.out.print(" Date " + date);
        String[] textResults = elements[2].split(",");
        System.out.print(" textResults " + Arrays.asList(textResults).toString());
        ArrayList<Integer> numericResults = new ArrayList<Integer>();
        if (textResults != null) {
            int len = textResults.length;
            for (int i = 0; i < len; i++) {
                numericResults.add(Integer.parseInt(textResults[i]));
            }
        }
        System.out.println(" numericResults " + numericResults.toString());
        try {
            return new Draw(id, date, numericResults);
        } catch (Exception e){
            System.out.println("Problem create new draw id "+ id);
            return null;
        }

    }

/*
    public static Draw convertJSONObjectToDraw(JSONObject element) {
        //Get date
        String date = (String) element.get("date");
        //System.out.println(date);
        //Get id
        Long id = (Long)element.get("id");
        //System.out.println(id);
        //Get numbers
        JSONArray jsonArray = (JSONArray) element.get("numbers");
        //System.out.println(jsonArray);
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
    */

}
