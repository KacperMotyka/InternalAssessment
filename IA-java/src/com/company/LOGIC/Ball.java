package com.company.LOGIC;

import java.text.DecimalFormat;

public class Ball {

    // PRIVATE ATTRIBUTES
    private String number;
    private int totalNumberOfWinning;
    private double totalPercentOfWinning;
    private int last15drawsWinning;
    private int last10drawsWinning;
    private int last5drawsWinning;
    private double indexOfAcceleration;
    private static DecimalFormat df = new DecimalFormat("0.000");
    private static DecimalFormat df2 = new DecimalFormat("0");


    // CONSTRUCTOR
    public Ball (String number){
        this.number = number;
        this.totalNumberOfWinning = 0;
        this.totalPercentOfWinning = 0;
        this.last15drawsWinning = 0;
        this.last10drawsWinning = 0;
        this.last5drawsWinning = 0;
        this.indexOfAcceleration = 0;
    }

    // GETTERS
    public String getNumber() {
        return number;
    }
    public int getTotalNumberOfWinning() {
        return totalNumberOfWinning;
    }
    public double getTotalPercentOfWinning() {
        return totalPercentOfWinning;
    }
    public int getLast15drawsWinning() {
        return last15drawsWinning;
    }
    public int getLast10drawsWinning() {
        return last10drawsWinning;
    }
    public int getLast5drawsWinning() {
        return last5drawsWinning;
    }
    public double getIndexOfAcceleration() {
        return indexOfAcceleration;
    }

    // SETTERS
    public void setTotalNumberOfWinning(int totalNumberOfWinning) {
        this.totalNumberOfWinning = totalNumberOfWinning;
    }
    public void setTotalPercentOfWinning(double totalPercentOfWinning) {
        this.totalPercentOfWinning = totalPercentOfWinning;
    }
    public void setLast15drawsWinning(int last15drawsWinning) {
        this.last15drawsWinning = last15drawsWinning;
        updateIndexOfAcceleration();
    }
    public void setLast10drawsWinning(int last10drawsWinning) {
        this.last10drawsWinning = last10drawsWinning;
        updateIndexOfAcceleration();
    }
    public void setLast5drawsWinning(int last5drawsWinning) {
        this.last5drawsWinning = last5drawsWinning;
        updateIndexOfAcceleration();
    }

    @Override
    public String toString() {
        return  "Ball number: " + String.format("%2s",number)+
                ",  Winning Times: " + df2.format(totalNumberOfWinning) +
                ",  Winning Percent: " + df.format(totalPercentOfWinning) +
                ",  Last 15 draws: " + last15drawsWinning +
                ",  Last 10 draws: " + last10drawsWinning +
                ",  Last 5 draws: " + last5drawsWinning +
                ",  Index of Acc: " + df.format(indexOfAcceleration);
    }

    // METHODS
    public void updateIndexOfAcceleration() {
        this.indexOfAcceleration = last15drawsWinning/15 + 1.25 * last10drawsWinning/10 + 1.5 * last5drawsWinning/5;
    }
}
