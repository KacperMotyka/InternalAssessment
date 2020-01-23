package com.company;

import java.sql.Date;
import java.util.ArrayList;

public class Draw {
    private long id;
    private String date;
    private int year;
    private int week;
    private ArrayList<Integer> results;



    // CONSTRUCTOR
    public Draw(long id, int year, int week, ArrayList<Integer> results) {
        this.id = id;
        this.date = "";
        this.year = year;
        this.week = week;
        this.results = results;
    }

    // GETTERS
    public ArrayList<Integer> getResults() {
        return results;
    }
    public int getYear() {
        return year;
    }
    public int getWeek() {
        return week;
    }
    public long getId() {
        return id;
    }

    // SETTERS
    public void setResults(ArrayList<Integer> results) {
        this.results = results;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public void setWeek(int week) {
        this.week = week;
    }
    public void setId(int id) {
        this.id = id;
    }
}
