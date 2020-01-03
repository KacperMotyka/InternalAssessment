public class Ball {
    private int number;
    private int totalWinning;
    private int last15drawsWinning;
    private int last10drawsWinning;
    private int last5drawsWinning;
    private double indexOfAcceleration;

    public Ball(int number, int totalWinning, int last15drawsWinning, int last10drawsWinning, int last5drawsWinning) {
        this.number = number;
        this.totalWinning = totalWinning;
        this.last15drawsWinning = last15drawsWinning;
        this.last10drawsWinning = last10drawsWinning;
        this.last5drawsWinning = last5drawsWinning;
        this.indexOfAcceleration = last15drawsWinning/15 + 1.25 * last10drawsWinning/10 + 1.5 * last5drawsWinning/5;
    }

    public int getNumber() {
        return number;
    }

    public int getTotalFrequency() {
        return totalWinning;
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

    public void setNumber(int number) {
        this.number = number;
    }

    public void setTotalFrequency(int totalFrequency) {
        this.totalWinning = totalFrequency;
    }

    public void setLast15drawsWinning(int last15drawsWinning) {
        this.last15drawsWinning = last15drawsWinning;
    }

    public void setLast10drawsWinning(int last10drawsWinning) {
        this.last10drawsWinning = last10drawsWinning;
    }

    public void setLast5drawsWinning(int last5drawsWinning) {
        this.last5drawsWinning = last5drawsWinning;
    }

    public void setIndexOfAcceleration(double indexOfAcceleration) {
        this.indexOfAcceleration = indexOfAcceleration;
    }
}
