public class Ball {
    private String number;
    private int totalNumberOfWinning;
    private float totalPercentOfWinning;
    private int last15drawsWinning;
    private int last10drawsWinning;
    private int last5drawsWinning;
    private double indexOfAcceleration;

    // CONSTRUCTOR
    public Ball(String number, int totalNumberOfWinning, float totalPercentOfWinning, int last15drawsWinning, int last10drawsWinning, int last5drawsWinning) {
        this.number = number;
        this.totalNumberOfWinning = totalNumberOfWinning;
        this.totalPercentOfWinning = totalPercentOfWinning;
        this.last15drawsWinning = last15drawsWinning;
        this.last10drawsWinning = last10drawsWinning;
        this.last5drawsWinning = last5drawsWinning;
        this.indexOfAcceleration = last15drawsWinning/15 + 1.25 * last10drawsWinning/10 + 1.5 * last5drawsWinning/5;
    }


    // GETTERS
    public String getNumber() {
        return number;
    }
    public int getTotalNumberOfWinning() {
        return totalNumberOfWinning;
    }
    public float getTotalPercentOfWinning() {
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
    public void setNumber(String number) {
        this.number = number;
    }
    public void setTotalNumberOfWinning(int totalNumberOfWinning) {
        this.totalNumberOfWinning = totalNumberOfWinning;
    }
    public void setTotalPercentOfWinning(float totalPercentOfWinning) {
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

    // METHODS
    public void updateIndexOfAcceleration() {
        this.indexOfAcceleration = last15drawsWinning/15 + 1.25 * last10drawsWinning/10 + 1.5 * last5drawsWinning/5;
    }
}
