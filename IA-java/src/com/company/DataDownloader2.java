package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import com.fasterxml.jackson.databind.ObjectMapper;
public class DataDownloader2 {


    // class attributes
    public static String htmlFilesPath = "HTML/";
    public static String shellFilesPath = "../";



    public static void refreshHTMLFiles(String gameName)  {

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

        }
    }


    public static void extractDataFromHTML(String gameName) {
        //Map<String, Map<String,String>> whole_results_dict = new TreeMap<>();
        List<Map<String, String>> whole_results_list = new ArrayList();
        for (int i = 1957; i < 2021; i++) {
            String year = String.valueOf(i);
            String content = extract_html_list_content(htmlFilesPath, year);
            List<String> ul_list = extract_html_lists_elements(content);
            Map<String,<Map<String, Object>>> results_dict_year = extract_values_create_dict(ul_list);
            // System.out.println("year: ", year, "dict :", len(results_dict_year), "list :", len(results_list_year));
            whole_results_list.extend(results_list_year);
            //whole_results_dict.update(results_dict_year);
        }
        System.out.println("Nombre tirages liste :", len(whole_results_list));
        //System.out.println("Nombre tirages dict :", whole_results_dict.size());
        // save_json(whole_results_dict, "IA-java/lotto-history-d.json");
        save_json(whole_results_list, "IA-java/lotto-history-l.json");
        System.out.println("Values saved to json files")

    }

    public static void save_json(Map<String, Object> my_dict, String filename) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new FileWriter(filename), my_dict);

    }


    public static String extract_html_list_content (String HTML_path, String year){
            String first_line = "class=\"lista_ostatnich_losowan\"";
            String last_line = "<!-- lista_ostatnich_losowan -->";
            String filename = HTML_path + year + "-wyniki-lotto.html"
            //with open (file = filename, mode = "r", encoding = "utf-8")as file{
            String fileContent = file.read();
            String htmlListContent = fileContent.split(first_line)[1].split(last_line)[0];
            return htmlListContent;
    }

    public static List<String> extract_html_lists_elements (String content){
            String[] ul_list = content.split("</ul>");
            // System.out.println(ul_list.size())
            // System.out.println(ul_list[0])
            // System.out.println(ul_list[1])
            // System.out.println(ul_list[len(ul_list) - 2])
            // System.out.println(ul_list[len(ul_list) - 1])
            String text = "ul style=\"position: relative;\">";
            List<String> ul_list2 = new ArrayList<>();

                   // [el.split(text)[1] for el in ul_list if "ul" in el]
            // System.out.println(ul_list.size())
            // System.out.println(ul_list[0])
            // System.out.println(ul_list[1])
            // System.out.println(ul_list[len(ul_list) - 2])
            // System.out.println(ul_list[len(ul_list) - 1])
            return ul_list2;
    }

    public static List<Map<String, Object>> extract_values_create_dict (List < String > ul_list2) {
            Map<String, Map<String, Object>> results_dict = new TreeMap<>();
            List<Map<String, Object>> results_list = new ArrayList<>(0);
            for (String ul : ul_list2) {
                String[] li_list = ul.split("</li>");
                Map<String, Object> li_dict = new TreeMap<>();
                //li_dict.put("numbers", new ArrayList<String>());
                for (String li : li_list) {
                    if (li.contains("numbers")) {
                        String[] numbers = (li.split(">")[1].trim().replace(".", ""));
                        li_dict.put("numbers", numbers);
                    }
                    if (li.contains("date")) {
                        String date = li.split(">")[1].trim().replace(".", "");
                        li_dict.put("date", date);
                    }
                    if (li.contains("nr")) {
                        int id = Integer.parseInt(li.split(">")[1].trim().replace(".", ""));
                        li_dict.put("id", id);
                    }
                }
                System.out.print(li_dict);
                results_list.add(li_dict);
                results_dict.put("id", li_dict);
            }
            return results_list;

    }

}