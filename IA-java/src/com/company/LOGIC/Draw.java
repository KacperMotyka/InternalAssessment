package com.company.LOGIC;

import java.util.ArrayList;

public class Draw {
    private long id;
    private String date;
    private ArrayList<Integer> results;

    // CONSTRUCTOR
    public Draw(long id, String date, ArrayList<Integer> results){
        //this.id = Integer.parseInt(id);
        this.id = id;
        this.date = date;
        this.results = results;
    }

    // GETTERS
    public String getDate() {
        return date;
    }
    public ArrayList<Integer> getResults() {
        return results;
    }
    public long getId() {
        return id;
    }

    // SETTERS
    public void setResults(ArrayList<Integer> results) {

        this.results = results;
    }
    public void setId(int id) {

        this.id = id;
    }

}
