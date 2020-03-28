package com.company.LOGIC;

import java.util.*;

public class Game {

    private String name;
    private String url;
    private String launchDate;
    private int totalNumberOfBalls;
    private int numberOfSelectedBalls;
    private ArrayList<Draw> drawHistory;
    private ArrayList<Ball> ballStatistics;
    private ArrayList<Ball> twentyMostFrequentlyWinning;
    private ArrayList<Ball> twentyLeastFrequentlyWinning;
    private ArrayList<Ball> ballStrategy1;
    private ArrayList<Ball> ballStrategy2;
    private ArrayList<Ball> ballStrategy3;
    private ArrayList<Ball> ballStrategy4;


    // CONSTRUCTOR
    public Game(String name, String url, String launchDate, int totalNumberOfBalls, int numberOfSelectedBalls, ArrayList<Draw> history) {
        this.name =  name;
        this.url = url;
        this.launchDate = launchDate;
        this.totalNumberOfBalls = totalNumberOfBalls;
        this.numberOfSelectedBalls = numberOfSelectedBalls;
        this.drawHistory = history;
        if (! drawHistory.isEmpty() && history != null) {
            this.drawHistory.sort(comparatorByIdDescending);
            recalculateBallsStatistics();
        }
        //System.out.println(" in Constructor ball statistics size :" + this.ballStatistics.size());
        this.twentyMostFrequentlyWinning = new ArrayList(twentyMostFrequentlyWinning());
        //System.out.println(" in Constructor ball statistics size :" + this.ballStatistics.size());
        this.twentyLeastFrequentlyWinning = new ArrayList(twentyLeastFrequentlyWinning());
        //System.out.println(" in Constructor ball statistics size :" + this.ballStatistics.size());
        this.ballStrategy1 = calculateStrategy1();
        this.ballStrategy2 = calculateStrategy2();
        this.ballStrategy3 = calculateStrategy3();
        this.ballStrategy4 = calculateStrategy4();

    }

    // GETTERS

    public String getName() {
        return name;
    }
    public String getUrl() {
        return url;
    }
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
    public ArrayList<Ball> getTwentyMostFrequentlyWinning() {
        return twentyMostFrequentlyWinning;
    }
    public ArrayList<Ball> getTwentyLeastFrequentlyWinning() {
        return twentyLeastFrequentlyWinning;
    }


    public ArrayList<Ball> getBallStrategy1() {
        ArrayList<Ball> temp = this.ballStrategy1;
        //System.out.println("get and recalculate strategy 1");
        this.ballStrategy1 = calculateStrategy1();
        return temp;
    }
    public ArrayList<Ball> getBallStrategy2() {
        ArrayList<Ball> temp = this.ballStrategy2;
        //System.out.println("get and recalculate strategy 2");
        this.ballStrategy2 = calculateStrategy2();
        return temp;
    }

    public ArrayList<Ball> getBallStrategy3() {
        System.out.println("get strategy 3");
        return this.ballStrategy3;
    }
    public ArrayList<Ball> getBallStrategy4() {
        System.out.println("get  strategy 4");
        return this.ballStrategy4;
    }

    // SETTERS
    public void setLaunchDate(String launchDate) {
        this.launchDate = launchDate;
    }
    public void setHistory(ArrayList<Draw> history) {
        this.drawHistory = history;
        this.drawHistory.sort(comparatorByIdDescending);
        recalculateBallsStatistics();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // COMPARATORS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private Comparator<Ball> comparatorByPercentWinningDescending = new Comparator<Ball>() {
        public int compare(Ball ball1, Ball ball2) {
            return ball1.getTotalPercentOfWinning() < ball2.getTotalPercentOfWinning()  ? 1 : ball1.getTotalPercentOfWinning()  > ball2.getTotalPercentOfWinning()  ? -1 : 0;
        }
    };

    private Comparator<Ball> comparatorByPercentWinningAscending = new Comparator<Ball>() {
        public int compare(Ball ball1, Ball ball2) {
            return ball1.getTotalPercentOfWinning() > ball2.getTotalPercentOfWinning()  ? 1 : ball1.getTotalPercentOfWinning()  < ball2.getTotalPercentOfWinning()  ? -1 : 0;
        }
    };

    private Comparator<Ball> comparatorByAccelerationIndexDescending = new Comparator<Ball>() {
        public int compare(Ball ball1, Ball ball2) {
            return ball1.getIndexOfAcceleration() < ball2.getIndexOfAcceleration()  ? 1 : ball1.getIndexOfAcceleration()  > ball2.getIndexOfAcceleration()  ? -1 : 0;
        }
    };
    private Comparator<Draw> comparatorByIdDescending = new Comparator<Draw>() {
        public int compare(Draw draw1, Draw draw2) {
            return draw1.getId() < draw2.getId()  ? 1 : draw1.getId()  > draw2.getId()  ? -1 : 0;
        }
    };

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // BALL STATISTICS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void recalculateBallsStatistics(){
        this.ballStatistics = new ArrayList<Ball>(this.totalNumberOfBalls);
        for (int i = 0; i < this.totalNumberOfBalls; i++){
            Integer a = i+1;
            ballStatistics.add(i, new Ball(a.toString()));
        }
        recalculateTotal();
        recalculateLastDraws();
        recalculateAccelerationIndex();
    }


    private void recalculateTotal(){
        // calcul frequency of each number
        List<Draw> drawList = this.drawHistory;
        for (int i =0; i < drawList.size(); i++){
            Draw draw = drawList.get(i);
            List<Integer> results = draw.getResults();
            for (int j = 0; j < results.size(); j++) {
                int winningNumber = results.get(j);
                Ball winningball = ballStatistics.get(winningNumber-1);
                // incrementation of winning frequency
                winningball.setTotalNumberOfWinning(winningball.getTotalNumberOfWinning() + 1);
            }
        }
        // calcul winning percent for each number
        for (int i = 0; i < this.ballStatistics.size(); i++) {
            Ball ball = this.ballStatistics.get(i);
            double d = this.getHistory().size();
            ball.setTotalPercentOfWinning(ball.getTotalNumberOfWinning() * 100 / d);
        }
    }

    private void recalculateLastDraws(){

        List<Draw> drawList = this.drawHistory.subList(0,5);

        for (Draw draw : drawList){
            for (int number : draw.getResults()) {
                Ball ball = ballStatistics.get(number-1);
                ball.setLast5drawsWinning(ball.getLast5drawsWinning() + 1);
            }
        }
        for (Draw draw : this.drawHistory.subList(0,10)){
            for (int number : draw.getResults()) {
                Ball ball = ballStatistics.get(number-1);
                ball.setLast10drawsWinning(ball.getLast10drawsWinning() + 1);
            }
        }
        for (Draw draw : this.drawHistory.subList(0,15)){
            for (int number : draw.getResults()) {
                Ball ball = ballStatistics.get(number-1);
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
    // Based on the historical data program will determine the following STATISTICS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    // 20 most frequently winning numbers in the entire history
    //System.out.println(this.name + " in twenty most frequently winning");

    public List<Ball> twentyMostFrequentlyWinning() {

        ArrayList<Ball> array = (ArrayList<Ball>) this.ballStatistics.clone();
        //System.out.println("Cloned  Array size:" + array.size());
        //DataManager.printArray(array);
        array.sort(this.comparatorByPercentWinningDescending);
        //System.out.println("Sorted Array size:" + array.size());
        //DataManager.printArray(array);
        return array.subList(0, 20);
    }

    // 20 least frequently winning numbers in the entire history
    //System.out.println(this.name + " in twenty least frequently winning");

    public List<Ball>  twentyLeastFrequentlyWinning() {

        ArrayList<Ball> array = (ArrayList<Ball>) this.ballStatistics.clone();
        //System.out.println("Cloned  Array size:" + array.size());
        //DataManager.printArray(array);
        array.sort(this.comparatorByPercentWinningAscending);
        //System.out.println("Sorted Array size:" + array.size());
        //DataManager.printArray(array);
        return array.subList(0, 20);

    }



    // all Balls sorted
    public ArrayList<Ball> allBalls() {
        //System.out.println(this.name + " in all balls");
        ArrayList<Ball> array = (ArrayList<Ball>) this.ballStatistics.clone();
        //System.out.println("all Balls sorted");
        // System.out.println("Array size:" + array.size());
        array.sort(this.comparatorByPercentWinningAscending);
        // System.out.println("Array size:" + array.size());
        return array;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Program will allow user to select a STRATEGY:
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    //    Most Frequent Random
    //    From the 20 most frequently winning numbers program will randomly choose a set of numbers,
    //    containing 6 numbers each for Lotto and ... numbers each for EuroJackpot.

    public ArrayList<Ball> calculateStrategy1(){
        ArrayList<Ball> twentyMost = (ArrayList<Ball>)this.twentyMostFrequentlyWinning.clone();
        Random randomDrawer = new Random();
        ArrayList<Ball> result = new ArrayList<Ball>();
        for (int i=0; i < this.numberOfSelectedBalls; i++) {
            int index = randomDrawer.nextInt(twentyMost.size());
            Ball drawn = twentyMost.get(index);
            result.add(drawn);
            twentyMost.remove(index);
        }
        return result;
    }



    //    Least Frequent Random
    //    From the 20 least frequently winning program will randomly choose a set of numbers,
    //    containing 6 numbers each.

    public ArrayList<Ball> calculateStrategy2(){
        ArrayList<Ball> twentyLeast = (ArrayList<Ball>)this.twentyLeastFrequentlyWinning.clone();
        Random randomDrawer = new Random();
        ArrayList<Ball> result = new ArrayList<Ball>();
        for (int i=0; i < this.numberOfSelectedBalls; i++) {
            int index = randomDrawer.nextInt(twentyLeast.size());
            Ball drawn = twentyLeast.get(index);
            result.add(drawn);
            twentyLeast.remove(index);
        }
        return result;
    }



    //    Most Frequent Random with “Acceleration”
    //    For each number in set of “20 most frequently winning numbers” the program will calculate
    //    Index of Acceleration using the following formula:
    //    Index of acceleration = [Winning Percent from the last 15 draws] +
    //    1.25 * [Winning Percent from the last 10 draws] + 1.5 * [Winning Percent from the last 5 draws]
    //    Program will then choose 6 numbers with the highest IoA.


    public ArrayList<Ball> calculateStrategy3(){
        ArrayList<Ball> twentyMost = (ArrayList<Ball>)this.twentyMostFrequentlyWinning.clone();
        twentyMost.sort(this.comparatorByAccelerationIndexDescending);
        ArrayList<Ball> result = new ArrayList<>(twentyMost.subList(0, this.numberOfSelectedBalls));
        return result;
    }


    //    Least Frequent Random with “Acceleration”
    //    For each number in set of “20 least frequently winning numbers” the program will calculate
    //    Index of Acceleration (IoA) using the following formula:
    //    Index of acceleration = [Winning Percent from the last 15 draws] +
    //    1.25 * [Winning Percent from the last 10 draws] + 1.5 * [Winning Percent from the last 5 draws]
    //    Program will then choose 6 numbers with the highest IoA.


    public ArrayList<Ball> calculateStrategy4(){
        ArrayList<Ball> twentyLeast = (ArrayList<Ball>)this.twentyLeastFrequentlyWinning.clone();
        twentyLeast.sort(this.comparatorByAccelerationIndexDescending);
        ArrayList<Ball> result = new ArrayList<Ball>(twentyLeast.subList(0, this.numberOfSelectedBalls));
        return result;
    }


}
