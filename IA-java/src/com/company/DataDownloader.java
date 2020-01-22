package com.company;

import java.io.*;

public class DataDownloader {

    // class attributes
    public static String shellFilesPath = "../";

    public static void refreshHTMLFilesShell(String gameName) throws Exception {

        String shellScriptName = shellFilesPath + "download_" + gameName + ".sh";
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
    public static void refreshHTMLFilesJava(String gameName)   {
        // Create a folder
        String[] command = {"mkdir", "HTML/"};
        try {
            Runtime rt = Runtime.getRuntime();
            Process pr = rt.exec(command);
            pr.waitFor();
            System.out.println(command[1] + "folder created ");
        } catch (Exception e){
            System.out.println("Command: "+ command[0] + " " + command[1] + "not executed");
            System.out.print(e.getMessage());

        }

        // Download html pages
        String[] download = {"wget","curl"};
        //String shellScriptName = shellFilesPath + "download_" + gameName + ".sh";
        for (int year = 1957; year < 2021; year++) {
            String url = "http://megalotto.pl/wyniki/"+gameName+"/losowania-z-roku-" ;
            String filename = command[1] + year + "-wyniki-" + gameName + ".html";
            String[] command2 = {download[1], url+year};
            System.out.println(command2[0] + command2[1]);
            String content = "";
            try {
                Runtime rt2 = Runtime.getRuntime();
                Process pr = rt2.exec(command);
                pr.waitFor();
                //InputStreamReader reader =  new InputStreamReader(pr.getInputStream());
                BufferedReader bfr = new BufferedReader(new InputStreamReader(pr.getInputStream()));

                String line = bfr.readLine();
                while (line != null) {
                    line = bfr.readLine();
                    content = content + line;
                }
                System.out.println("New HTML files for " + filename + " downloaded");
            } catch (Exception e1) {
                System.out.println("Problem executing command: " + command[0] + " " + command[1]);
                System.out.print(e1.getMessage());

            }
            try {
                Writer writer = new FileWriter("HTMLfilename");
                writer.write(content);
                writer.close();
                System.out.println("File " + filename + " saved");
            } catch (Exception e2){
                System.out.println("Problem saving file" + filename);
                System.out.print(e2.getMessage());

            }


        }
    }


    public static void refreshHTMLFiles(String gameName) {
        try {
            refreshHTMLFilesShell(gameName);
        } catch (Exception e){
            refreshHTMLFilesJava(gameName);
        }
    }

}
