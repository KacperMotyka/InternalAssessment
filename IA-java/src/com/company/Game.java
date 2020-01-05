package com.company;

import com.company.Ball;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

public class Game {
    private String name;
    private long launchDate;
    private int totalNumberOfBalls;
    private int numberOfSelectedBalls;
    private ArrayList<Draw> history;
    private ArrayList<Ball> ballStatistics;
    public Comparator<Ball> comparatorByPercentWinningAscending;
    public Comparator<Ball> comparatorByPercentWinningDescending;
    public Comparator<Ball> comparatorByAccelerationIndexDescending;

    public Game(String name, String launchDate, int totalNumberOfBalls, int numberOfSelectedBalls, ArrayList<Draw> history) {
        this.name =  name;
        this.launchDate = Date.parse(launchDate);
        this.totalNumberOfBalls = totalNumberOfBalls;
        this.numberOfSelectedBalls = numberOfSelectedBalls;
        this.history = history;
        this.ballStatistics = new ArrayList<Ball>(this.totalNumberOfBalls);
        this.comparatorByPercentWinningAscending = new Comparator<Ball>() {
            public int compare(Ball ball1, Ball ball2) {
                return ball1.getTotalPercentOfWinning() < ball2.getTotalPercentOfWinning()  ? 1 : ball1.getTotalPercentOfWinning()  > ball2.getTotalPercentOfWinning()  ? -1 : 0;
            }
        };
        this.comparatorByPercentWinningDescending = new Comparator<Ball>() {
            public int compare(Ball ball1, Ball ball2) {
                return ball1.getTotalPercentOfWinning() > ball2.getTotalPercentOfWinning()  ? 1 : ball1.getTotalPercentOfWinning()  < ball2.getTotalPercentOfWinning()  ? -1 : 0;
            }
        };

        this.comparatorByAccelerationIndexDescending = new Comparator<Ball>() {
            public int compare(Ball ball1, Ball ball2) {
                return ball1.getIndexOfAcceleration() > ball2.getIndexOfAcceleration()  ? 1 : ball1.getIndexOfAcceleration()  < ball2.getIndexOfAcceleration()  ? -1 : 0;
            }
        };
    }

    // GETTERS
    public long getLaunchDate() {
        return launchDate;
    }
    public int getTotalNumberOfBalls() {
        return totalNumberOfBalls;
    }
    public int getNumberOfSelectedBalls() {
        return numberOfSelectedBalls;
    }
    public ArrayList<Draw> getHistory() {
        return history;
    }
    public ArrayList<Ball> getBallStatistics() { return ballStatistics; }

    // SETTERS
    public void setLaunchDate(long launchDate) {
        this.launchDate = launchDate;
    }
    public void setTotalNumberOfBalls(int totalNumberOfBalls) {
        this.totalNumberOfBalls = totalNumberOfBalls;
    }
    public void setNumberOfSelectedBalls(int numberOfSelectedBalls) { this.numberOfSelectedBalls = numberOfSelectedBalls; }
    public void setHistory(ArrayList<Draw> history) {
        this.history = history;
    }

}
