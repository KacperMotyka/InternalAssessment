package com.company;

import java.util.ArrayList;
import java.util.Comparator;

public class Game {
    private String name;
    private String launchDate;
    private int totalNumberOfBalls;
    private int numberOfSelectedBalls;
    private ArrayList<Draw> drawHistory;
    private ArrayList<Ball> ballStatistics;
    public Comparator<Ball> comparatorByPercentWinningAscending = new Comparator<Ball>() {
        public int compare(Ball ball1, Ball ball2) {
            return ball1.getTotalPercentOfWinning() < ball2.getTotalPercentOfWinning()  ? 1 : ball1.getTotalPercentOfWinning()  > ball2.getTotalPercentOfWinning()  ? -1 : 0;
        }
    };
    public Comparator<Ball> comparatorByPercentWinningDescending = new Comparator<Ball>() {
        public int compare(Ball ball1, Ball ball2) {
            return ball1.getTotalPercentOfWinning() > ball2.getTotalPercentOfWinning()  ? 1 : ball1.getTotalPercentOfWinning()  < ball2.getTotalPercentOfWinning()  ? -1 : 0;
        }
    };
    public Comparator<Ball> comparatorByAccelerationIndexDescending = new Comparator<Ball>() {
        public int compare(Ball ball1, Ball ball2) {
            return ball1.getIndexOfAcceleration() > ball2.getIndexOfAcceleration()  ? 1 : ball1.getIndexOfAcceleration()  < ball2.getIndexOfAcceleration()  ? -1 : 0;
        }
    };

    public Game(String name, String launchDate, int totalNumberOfBalls, int numberOfSelectedBalls, ArrayList<Draw> history) {
        this.name =  name;
        this.launchDate = launchDate;
        this.totalNumberOfBalls = totalNumberOfBalls;
        this.numberOfSelectedBalls = numberOfSelectedBalls;
        this.drawHistory = history;
        /*this.comparatorByPercentWinningAscending = new Comparator<Ball>() {
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
        };*/
        recalculBallsStatistics ();
    }

    // GETTERS
    public String getLaunchDate() {
        return launchDate;
    }
    public int getTotalNumberOfBalls() {
        return totalNumberOfBalls;
    }
    public int getNumberOfSelectedBalls() {
        return numberOfSelectedBalls;
    }
    public ArrayList<Draw> getHistory() {
        return drawHistory;
    }
    public ArrayList<Ball> getBallStatistics() { return ballStatistics; }

    // SETTERS
    public void setLaunchDate(String launchDate) {
        this.launchDate = launchDate;
    }
    public void setHistory(ArrayList<Draw> history) {
        this.drawHistory = history;
        recalculBallsStatistics ();
    }

    private void recalculBallsStatistics (){
        this.ballStatistics = new ArrayList<Ball>(50);
        for (Integer i = 0; i <= 49; i++){
            ballStatistics.add(i, new Ball(i.toString()));
        }
        recalculTotal();
        recalculLastDraws();
        recalculAccelerationIndex();

    }
    private void recalculTotal(){
        for (Draw draw : this.drawHistory){
            for (int number : draw.getResults()) {
                Ball ball = ballStatistics.get(number);
                ball.setTotalNumberOfWinning(ball.getTotalNumberOfWinning() + 1);
            }
        }
        for (Ball ball : ballStatistics){
            ball.setTotalPercentOfWinning(ball.getTotalNumberOfWinning()/this.getHistory().size());
        }
    }
    private void recalculLastDraws(){

        for (Draw draw : this.drawHistory.subList(0,5)){
            for (int number : draw.getResults()) {
                Ball ball = ballStatistics.get(number);
                ball.setLast5drawsWinning(ball.getLast5drawsWinning() + 1);
            }
        }
        for (Draw draw : this.drawHistory.subList(0,10)){
            for (int number : draw.getResults()) {
                Ball ball = ballStatistics.get(number);
                ball.setLast10drawsWinning(ball.getLast10drawsWinning() + 1);
            }
        }
        for (Draw draw : this.drawHistory.subList(0,15)){
            for (int number : draw.getResults()) {
                Ball ball = ballStatistics.get(number);
                ball.setLast15drawsWinning(ball.getLast15drawsWinning() + 1);
            }
        }

    }
    private void recalculAccelerationIndex(){
        for (Ball ball : ballStatistics){
            ball.updateIndexOfAcceleration();
        }

    }

}
