public class Ball {
    private int number;
    private int totalFrequency;
    private int last15draws;
    private int last10draws;
    private int last5draws;
    private double indexOfAcceleration;

    public Ball(int number, int totalFrequency, int last15draws, int last10draws, int last5draws) {
        this.number = number;
        this.totalFrequency = totalFrequency;
        this.last15draws = last15draws;
        this.last10draws = last10draws;
        this.last5draws = last5draws;
        this.indexOfAcceleration = indexOfAcceleration;
    }

    public int getNumber() {
        return number;
    }

    public int getTotalFrequency() {
        return totalFrequency;
    }

    public int getLast15draws() {
        return last15draws;
    }

    public int getLast10draws() {
        return last10draws;
    }

    public int getLast5draws() {
        return last5draws;
    }

    public double getIndexOfAcceleration() {
        return indexOfAcceleration;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setTotalFrequency(int totalFrequency) {
        this.totalFrequency = totalFrequency;
    }

    public void setLast15draws(int last15draws) {
        this.last15draws = last15draws;
    }

    public void setLast10draws(int last10draws) {
        this.last10draws = last10draws;
    }

    public void setLast5draws(int last5draws) {
        this.last5draws = last5draws;
    }

    public void setIndexOfAcceleration(double indexOfAcceleration) {
        this.indexOfAcceleration = indexOfAcceleration;
    }
}
