package com.company;

import java.io.IOException;

public class DataExtractor {


    // class attributes
    public static String pythonFilesPath = "../";

    public static void extractDataFromHTML_Python(String gameName)  {

        String pythonScriptName = pythonFilesPath + "extract_" + gameName + ".py";
        String[] command = {"python", pythonScriptName};

        try {
            Runtime rt = Runtime.getRuntime();
            Process pr = rt.exec(command);
            pr.waitFor();
            //System.out.println("Data from HTML files for " + gameName +" extracted to " + gameName + "_history.json");
        } catch (IOException e){
            System.out.println("Problem executing command: "+ command[0] + " " + command[1]);
            System.out.print(e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("Problem executing command: "+ command[0] + " " + command[1]);
            System.out.print(e.getMessage());
        }

    }

    public static void extractDataFromHTML_Java(String gameName)  {

    }


    public static void extractDataFromHTML(String gameName) {
        try {
            extractDataFromHTML_Python(gameName);
        } catch (Exception e){
            extractDataFromHTML_Java(gameName);
        }
    }


}
