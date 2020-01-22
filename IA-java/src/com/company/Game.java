package com.company;

import java.util.*;

public class Game {
    private String name;
    private String launchDate;
    private int totalNumberOfBalls;
    private int numberOfSelectedBalls;
    private ArrayList<Draw> drawHistory;
    private ArrayList<Ball> ballStatistics;

    // CONSTRUCTOR
    public Game(String name, String launchDate, int totalNumberOfBalls, int numberOfSelectedBalls, ArrayList<Draw> history) {
        this.name =  name;
        this.launchDate = launchDate;
        this.totalNumberOfBalls = totalNumberOfBalls;
        this.numberOfSelectedBalls = numberOfSelectedBalls;
        this.drawHistory = history;
        recalculateBallsStatistics();
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
        recalculateBallsStatistics();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // COMPARATORS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // BALL STATISTICS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void recalculateBallsStatistics(){
        this.ballStatistics = new ArrayList<Ball>(this.totalNumberOfBalls + 1);
        for (Integer i = 0; i <= this.totalNumberOfBalls; i++){
            ballStatistics.add(i, new Ball(i.toString()));
        }
        recalculateTotal();
        recalculateLastDraws();
        recalculateAccelerationIndex();
    }

    private void recalculateTotal(){
        for (Draw draw : this.drawHistory){
            for (int number : draw.getResults()) {
                Ball ball = ballStatistics.get(number);
                ball.setTotalNumberOfWinning(ball.getTotalNumberOfWinning() + 1);
            }
        }

        for (int i = 1; i < this.ballStatistics.size(); i++) {
            Ball ball = this.ballStatistics.get(i);
            double d = this.getHistory().size();
            ball.setTotalPercentOfWinning(ball.getTotalNumberOfWinning() * 100 / d);
        }
    }

    private void recalculateLastDraws(){

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

    private void recalculateAccelerationIndex(){
        for (Ball ball : ballStatistics){
            ball.updateIndexOfAcceleration();
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Based on the historical data program will determine th following STATISTICS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // Entire History Winning Frequency for a number
    public double EHWinningFrequency (String number){
        int index = Integer.parseInt(number) - 1;
        return this.ballStatistics.get(index).getTotalNumberOfWinning();
    }
    //  Entire History Winning Percent for a number = Number of draws in which the number won /  Number of all draws
    public double EHWinningPercent (String number){
        int index = Integer.parseInt(number) - 1;
        return this.ballStatistics.get(index).getTotalPercentOfWinning();
    }

    // Winning Frequency for the last 5, 10 and 15 draws for a number
    public int[] WinningFrequencyForLastDraws(String number) {
        int index = Integer.parseInt(number) - 1;
        Ball ball =  this.ballStatistics.get(index);
        int[] result = new int[3];
        result[0] = ball.getLast5drawsWinning();
        result[1] = ball.getLast10drawsWinning();
        result[2] = ball.getLast15drawsWinning();
        return result;
    }
    // Winning Percent for the last 5, 10 and 15 draws for a number
    public double[] WinningPercentForLastDraws(String number) {
        int index = Integer.parseInt(number) - 1;
        Ball ball =  this.ballStatistics.get(index);
        double[] result = new double[3];
        result[0] = ball.getLast5drawsWinning()/5;
        result[1] = ball.getLast10drawsWinning()/10;
        result[2] = ball.getLast15drawsWinning()/15;
        return result;
    }

    // Entire History Winning Frequency for each number
    public double[] EHWinningFrequency (){
        int n = this.totalNumberOfBalls;
        double[] array = new double[n+1];
        for (int index = 1; index <= n; index++){
            array[index] = this.ballStatistics.get(index).getTotalNumberOfWinning();
        }
        return array;
    }
    //  Entire History Winning Percent for each number = Number of draws in which the number won /  Number of all draws
    public double[] EHWinningPercent (){
        int n = this.totalNumberOfBalls;
        double[] array = new double[n+1];
        for (int i = 1; i <= n; i++){
            array[i] = this.ballStatistics.get(i).getTotalPercentOfWinning();
        }
        return array;
    }
    // 20 most frequently winning numbers in the entire history
    public List<Ball> twentyMostFrequentlyWinning() {
        ArrayList<Ball> array = (ArrayList<Ball>) this.ballStatistics.clone();
        // System.out.println("Array size:" + array.size());
        array.sort(this.comparatorByPercentWinningDescending);
        // System.out.println("Array size:" + array.size());
        return array.subList(0, 20);
    }

    // 20 least frequently winning numbers in the entire history
    public List<Ball>  twentyLeastFrequentlyWinning() {
        ArrayList<Ball> array = (ArrayList<Ball>) this.ballStatistics.clone();
        // System.out.println("Array size:" + array.size());
        array.sort(this.comparatorByPercentWinningAscending);
        // System.out.println("Array size:" + array.size());
        return array.subList(0, 20);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Program will allow user to select a STRATEGY:
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //    Most Frequent Random
    //    From the 20 most frequently winning numbers program will randomly choose a set of numbers, containing 6 numbers each for Lotto and ... numbers each for EuroJackpot.
    public Ball[] calculateStrategy1(){
        List<Ball> balls = this.twentyMostFrequentlyWinning();
        // printArray(balls);
        Random rand = new Random();
        Ball[] result = new Ball[this.numberOfSelectedBalls];
        for (int i=0; i < result.length; i++) {
            int index = rand.nextInt(balls.size());
            result[i] = balls.get(index);
            balls.remove(index);
        }
        return result;
    }

    //    Least Frequent Random
    //    From the 20 least frequently winning program will randomly choose a set of numbers, containing 6 numbers each.
    public Ball[] calculateStrategy2(){
        List<Ball> balls = this.twentyLeastFrequentlyWinning();
        // printArray(balls);
        Random randomDrawer = new Random();
        Ball[] result = new Ball[this.numberOfSelectedBalls];
        for (int i=0; i < result.length; i++) {
            int index = randomDrawer.nextInt(balls.size());
            result[i] = balls.get(index);
            balls.remove(index);
        }
        return result;
    }

    //    Most Frequent Random with “Acceleration”
    //    For each number in set of “20 most frequently winning numbers” the program will calculate Index of Acceleration using the following formula:
    //    Index of acceleration = [Winning Percent from the last 15 draws] + 1.25 * [Winning Percent from the last 10 draws] + 1.5 * [Winning Percent from the last 5 draws]
    //    Program will then choose 6 numbers with the highest IoA.
    public List<Ball> calculateStrategy3(){
        List<Ball> twentyMost = this.twentyMostFrequentlyWinning();
        //printArray(twentyMost);
        twentyMost.sort(this.comparatorByAccelerationIndexDescending);
        return twentyMost.subList(0, this.numberOfSelectedBalls);
    }

    //    Least Frequent Random with “Acceleration”
    //    For each number in set of “20 least frequently winning numbers” the program will calculate Index of Acceleration (IoA) using the following formula:
    //    Index of acceleration = [Winning Percent from the last 15 draws] + 1.25 * [Winning Percent from the last 10 draws] + 1.5 * [Winning Percent from the last 5 draws]
    //    Program will then choose 6 numbers with the highest IoA.
    public List<Ball> calculateStrategy4(){
        List<Ball> twentyLeast = this.twentyLeastFrequentlyWinning();
        //printArray(twentyLeast);
        twentyLeast.sort(this.comparatorByAccelerationIndexDescending);
        return twentyLeast.subList(0, this.numberOfSelectedBalls);
    }
}
