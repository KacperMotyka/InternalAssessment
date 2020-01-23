package com.company;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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
}
