package ua.edu.ucu.tempseries;

public class TempSummaryStatistics {
    private double avgTemp;
    private double devTemp;
    private double minTemp;
    private double maxTemp;

    public TempSummaryStatistics(
            double avgTemp, double devTemp, double minTemp, double maxTemp
    ) {
        this.avgTemp = avgTemp;
        this.devTemp = devTemp;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
    }

    //Getters

    public double getAvgTemp() {
        return avgTemp;
    }

    public double getDevTemp() {
        return devTemp;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public double getMinTemp() {
        return minTemp;
    }

    //Setters

    public void setAvgTemp(double avg) {
        this.avgTemp = avg;
    }

    public void setDevTemp(double dev) {
        this.devTemp = dev;
    }

    public void setMaxTemp(double max) {
        this.maxTemp = max;
    }

    public void setMinTemp(double min) {
        this.minTemp = min;
    }
}
