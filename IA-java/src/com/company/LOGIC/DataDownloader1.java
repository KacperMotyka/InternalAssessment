package com.company.LOGIC;

import java.io.*;

public class DataDownloader1 {

    // class attributes
    public static String shellFilesPath = "../";
    public static String htmlFilesPath = "HTML/";
    public static String pythonFilesPath = "../IA-java/";
    public static String whichPython = "python";

    public static void refreshData() {
        String pythonScriptName = pythonFilesPath + "download_data.py";
        System.out.println(pythonScriptName);


        ProcessBuilder pb = new ProcessBuilder( pythonScriptName);
        try {
            pb.directory(new File(pythonFilesPath));
            Process p = pb.start();
            int result = p.waitFor();
        } catch(Exception e){



            //System.out.print(e.getMessage());
        }
    }



}
