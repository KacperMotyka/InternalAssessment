package com.company.DATA_ACCESS;

import com.company.LOGIC.Draw;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;

public class DataReader {

    public static BufferedReader readFile (String filename){
        try  {
            //FileReader reader = new FileReader(filename);
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            return reader;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


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

    private static Draw convertTEXTToDraw(String[] elements) {
        Long id = (Long) Long.parseLong(elements[0].replace('.',' ').trim());
        System.out.print(" Id " + id + ", ");
        String date = (String) elements[1];
        String[] textResults = elements[2].split(",");
        ArrayList<Integer> numericResults = new ArrayList<Integer>();
        if (textResults != null) {
            int len = textResults.length;
            for (int i = 0; i < len; i++) {
                numericResults.add(Integer.parseInt(textResults[i]));
            }
        }
        //System.out.println(" numericResults " + numericResults.toString());
        try {
            return new Draw(id, date, numericResults);
        } catch (Exception e){
            System.out.println("Problem create new draw id "+ id);
            return null;
        }
    }

}
