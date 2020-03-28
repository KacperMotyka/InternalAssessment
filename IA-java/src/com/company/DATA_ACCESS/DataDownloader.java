

package com.company.DATA_ACCESS;

public class DataDownloader {

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


