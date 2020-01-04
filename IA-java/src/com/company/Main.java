package com.company;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class Main {

    public static void main(String[] args) {


        
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Based on the historical data program will determine:
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // Entire History Winning Frequency for a number
    public static double EHWinningFrequency (Game game, String number){
        int index = Integer.parseInt(number) - 1;
        return game.getBallStatistics().get(index).getTotalNumberOfWinning();
    }
    //  Entire History Winning Percent for a number = Number of draws in which the number won /  Number of all draws
    public static double EHWinningPercent (Game game, String number){
        int index = Integer.parseInt(number) - 1;
        return game.getBallStatistics().get(index).getTotalPercentOfWinning();
    }

    // Winning Frequency for the last 5, 10 and 15 draws for a number
    public static int[] WinningFrequencyForLastDraws(Game game, String number) {
        int index = Integer.parseInt(number) - 1;
        Ball ball =  game.getBallStatistics().get(index);
        int[] result = new int[3];
        result[0] = ball.getLast5drawsWinning();
        result[0] = ball.getLast10drawsWinning();
        result[0] = ball.getLast15drawsWinning();
        return result;
    }
    // Winning Percent for the last 5, 10 and 15 draws for a number
    public static int[] WinningPercentForLastDraws(Game game, String number) {
        int index = Integer.parseInt(number) - 1;
        Ball ball =  game.getBallStatistics().get(index);
        int[] result = new int[3];
        result[0] = ball.getLast5drawsWinning()/5;
        result[0] = ball.getLast10drawsWinning()/10;
        result[0] = ball.getLast15drawsWinning()/15;
        return result;
    }

    // Entire History Winning Frequency for each number
    public static int[] EHWinningFrequency (Game game){
        int n = game.getTotalNumberOfBalls();
        int[] array = new int[n+1];
        for (int index = 1; index <= n; index++){
            array[index] = game.getBallStatistics().get(index).getTotalNumberOfWinning();
        }
        return array;
    }
    //  Entire History Winning Percent for each number = Number of draws in which the number won /  Number of all draws
    public static float[] EHWinningPercent (Game game){
        int n = game.getTotalNumberOfBalls();
        float[] array = new float[n+1];
        for (i = 1; i <= n; i++){
            array[i] = game.getBallStatistics()[i].getTotalPercentOfWinning();
        }
        return array;
    }
    // 20 most frequently winning numbers in the entire history
    public static ArrayList<Ball> twentyMostFrequentlyWinning(Game game) {
        ArrayList<Ball> array = game.getBallStatistics();
        array.sort(new Comparator<Ball>() {
            public int compare(Ball ball1, Ball ball2) {
                return ball1.getTotalPercentOfWinning() > ball2.getTotalPercentOfWinning()  ? 1 : ball1.getTotalPercentOfWinning()  < ball2.getTotalPercentOfWinning()  ? -1 : 0;
            }
        });
        return (ArrayList<Ball>) array.subList(0, 20);
    }

    // 20 least frequently winning numbers in the entire history
    public static ArrayList<Ball>  twentyLeastFrequentlyWinning(Game game) {
        ArrayList<Ball> array = game.getBallStatistics();
        array.sort(new Comparator<Ball>() {
            public int compare(Ball ball1, Ball ball2) {
                return ball1.getTotalPercentOfWinning() < ball2.getTotalPercentOfWinning()  ? 1 : ball1.getTotalPercentOfWinning()  > ball2.getTotalPercentOfWinning()  ? -1 : 0;
            }
        });
        return (ArrayList<Ball>) array.subList(0, 20);
    }
    

}
