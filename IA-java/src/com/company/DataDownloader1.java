package com.company;

import java.io.*;

public class DataDownloader {

    // class attributes
    public static String shellFilesPath = "../";
    public static String htmlFilesPath = "HTML/";
    public static String pythonFilesPath = "../IA-java/";
    public static String whichPython = "python";

    public static void refreshData() {
        String pythonScriptName = pythonFilesPath + "download_data.py";
        System.out.println(pythonScriptName);
        ProcessBuilder pb = new ProcessBuilder(whichPython, pythonScriptName);
        try {
            pb.directory(new File(pythonFilesPath));
            Process p = pb.start();
            int result = p.waitFor();
        } catch(Exception e){
            //System.out.print(e.getMessage());
        }
    }

    
    /*
    public static void refreshHTMLFiles(String gameName) throws Exception {

        String shellScriptName = shellFilesPath + "download-" + gameName + ".sh";
        String[] command = {"sh", shellScriptName};
        try {
            Runtime rt = Runtime.getRuntime();
            Process pr = rt.exec(command);
            pr.waitFor();
            System.out.println("New HTML files for " + gameName +" downloaded");
        } catch (Exception e){
            System.out.println("Problem executing command: "+ command[0] + " " + command[1]);
            System.out.print(e.getMessage());
            throw new Exception();
        }
    }
*/
}
