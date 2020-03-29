package com.company.DATA_ACCESS;

import com.company.LOGIC.Draw;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;


public class DataReader {


    public static BufferedReader readFile (String filename){
        FileReader simpleReader;
        BufferedReader advancedReader;


        try  {
            simpleReader = new FileReader(filename);
            advancedReader = new BufferedReader(simpleReader);
            return advancedReader;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        return null;
    }



    public static ArrayList<Draw> parseFileContent (BufferedReader reader){
        ArrayList<Draw> drawHistory = new ArrayList<>();
        try  {
            String line = reader.readLine();
            while (line != null) {
                String[] columns = line.split(" ");
                Draw draw = convertColumnsToDraw(columns);
                drawHistory.add(draw);
                line = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return drawHistory;
    }



    private static Draw convertColumnsToDraw(String[] columns) {
        String col1;
        int id = -1;
        String date = "";
        String[] textResults = {};
        Draw draw;

        try {
            col1 = columns[0].replace('.', ' ').trim();
            id = Integer.parseInt(col1);
            date =  columns[1];
            textResults = columns[2].split(",");
            ArrayList<Integer> numericResults = new ArrayList<Integer>();
            if (textResults.length>0) {
                int len = textResults.length;
                for (int i = 0; i < len; i++) {
                    numericResults.add(Integer.parseInt(textResults[i]));
                }
            }
            draw = new Draw(id, date, numericResults);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Problem create new draw id " + id);
            return null;
        }
        return draw;
    }

}

