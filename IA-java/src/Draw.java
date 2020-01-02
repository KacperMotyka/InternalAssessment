public class Draw {
    private int [] results;
    private int year;
    private int week;
    private int id;

    public Draw(int[] results, int year, int week, int id) {
        this.results = results;
        this.year = year;
        this.week = week;
        this.id = id;
    }

    public int[] getResults() {
        return results;
    }

    public int getYear() {
        return year;
    }

    public int getWeek() {
        return week;
    }

    public int getId() {
        return id;
    }

    public void setResults(int[] results) {
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
