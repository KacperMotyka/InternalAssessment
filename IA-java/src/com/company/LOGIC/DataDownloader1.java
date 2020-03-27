package com.company.LOGIC;

import java.io.*;

public class DataDownloader1 {

    // class attributes
    public static String shellFilesPath = "../";
    public static String htmlFilesPath = "HTML/";
    public static String pythonFilesPath = "../IA-java/";
    public static String whichPython = "python";

    public static void refreshData()  {

        String shellScriptName = "download.sh";
        String[] command = {"sh", shellScriptName};
        try {
            Runtime rt = Runtime.getRuntime();
            Process pr = rt.exec(command);
            pr.waitFor();
            System.out.println("New TXT files for downloaded");
        } catch (Exception e){
            System.out.println("Problem executing command: "+ command[0] + " " + command[1]);
            System.out.print(e.getMessage());

        }
    }


}
