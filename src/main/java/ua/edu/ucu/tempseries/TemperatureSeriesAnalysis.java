package ua.edu.ucu.tempseries;

import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {
    public static final double ABS_ZERO = -273.0;
    private double[] temperatureSeries;
    private int logicalSize;


    //Default init
    public TemperatureSeriesAnalysis() {

    }



    //Init with parameters
    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        int invalValues = 0;
        for (int i = 0; i < temperatureSeries.length; i++) {
            if (temperatureSeries[i] < ABS_ZERO) {
                invalValues++;
            }
        }
        if (invalValues > 0) {
            throw new InputMismatchException("Values less than -273.0");
        }
        else {
            this.temperatureSeries = temperatureSeries;
            this.logicalSize = this.temperatureSeries.length;
        }
    }



    //Methods

    public double average() {

        if (this.temperatureSeries.length < 1) {
            throw new IllegalArgumentException("Empty array");
        }
        double avg = 0;
        for (int i = 0; i < this.temperatureSeries.length; i++) {
            avg += this.temperatureSeries[i];
        }
        avg /= this.temperatureSeries.length;
        return avg;
    }



    public double deviation() {

        if (this.temperatureSeries.length < 1) {
            throw new IllegalArgumentException("Empty array");
        }

        double dev = 0;
        double avg = this.average();

        for (int i = 0; i < this.temperatureSeries.length; i++) {
            dev += (temperatureSeries[i] - avg)*(temperatureSeries[i] - avg);
        }
        dev /= this.temperatureSeries.length;
        dev = Math.sqrt(dev);
        return dev;
    }



    public double min() {

        if (this.temperatureSeries.length < 1) {
            throw new IllegalArgumentException("Empty array");
        }

        double minVal = Integer.MAX_VALUE;

        for (int i = 0; i < this.temperatureSeries.length; i++) {
            if (this.temperatureSeries[i] < minVal) {
                minVal = this.temperatureSeries[i];
            }
        }

        return minVal;
    }



    public double max() {

        if (this.temperatureSeries.length < 1) {
            throw new IllegalArgumentException("Empty array");
        }

        double maxVal = Integer.MIN_VALUE;

        for (int i = 0; i < this.temperatureSeries.length; i++) {
            if (this.temperatureSeries[i] > maxVal) {
                maxVal = this.temperatureSeries[i];
            }
        }

        return maxVal;
    }



    public double findTempClosestToZero() {

        if (this.temperatureSeries.length < 1) {
            throw new IllegalArgumentException("Empty array");
        }

        else if (this.temperatureSeries.length == 1) {
            return this.temperatureSeries[0];
        }

        double closest = this.temperatureSeries[0];

        for (int i = 1; i < this.temperatureSeries.length; i++) {
            if (Math.abs(this.temperatureSeries[i]) < closest) {
                closest = this.temperatureSeries[i];
            }
        }
        return closest;
    }



    public double findTempClosestToValue(double tempValue) {

        if (this.temperatureSeries.length < 1) {
            throw new IllegalArgumentException("Empty array");
        }

        else if (this.temperatureSeries.length == 1) {
            return this.temperatureSeries[0];
        }

        double closest = this.temperatureSeries[0];

        for (int i = 1; i < this.temperatureSeries.length; i++) {
            if (Math.abs(this.temperatureSeries[i] - tempValue)
                    < Math.abs(closest - tempValue)) {
                closest = this.temperatureSeries[i];
            }
            else if (Math.abs(this.temperatureSeries[i] - tempValue)
                    == Math.abs(closest - tempValue)
                    && this.temperatureSeries[i] == Math.abs(closest)) {
                    closest = this.temperatureSeries[i];
                }
            }
        return closest;
    }



    public double[] findTempsLessThen(double tempValue) {

        int lessLen = 0;

        for (int i = 0; i < this.temperatureSeries.length; i++) {
            if (this.temperatureSeries[i] < tempValue) {
                lessLen++;
            }
        }

        double[] lessVals = new double[lessLen];
        int indx = 0;

        for (int i = 0; i < this.temperatureSeries.length; i++) {
            if (this.temperatureSeries[i] < tempValue) {
                lessVals[indx] = this.temperatureSeries[i];
                indx++;
            }
        }
        return lessVals;
    }



    public double[] findTempsGreaterThen(double tempValue) {

        int grtLen = 0;

        for (int i = 0; i < this.temperatureSeries.length; i++) {
            if (this.temperatureSeries[i] >= tempValue) {
                grtLen++;
            }
        }

        double[] grtVals = new double[grtLen];
        int indx = 0;

        for (int i = 0; i < this.temperatureSeries.length; i++) {
            if (this.temperatureSeries[i] >= tempValue) {
                grtVals[indx] = this.temperatureSeries[i];
                indx++;
            }
        }
        return grtVals;
    }



    public TempSummaryStatistics summaryStatistics() {

        if (this.temperatureSeries.length < 1) {
            throw new IllegalArgumentException("Empty array");
        }

        final TempSummaryStatistics STATISTICS = new TempSummaryStatistics(
                this.average(), this.deviation(), this.min(), this.max()
        );
        return STATISTICS;
    }



    public int addTemps(double... temps) {

        if (temps.length > 0 && this.logicalSize == this.temperatureSeries.length) {

                double[] newSeries = new double[this.temperatureSeries.length * 2];
                this.logicalSize += temps.length;

                for (int i = 0; i < this.logicalSize; i++) {
                    if (i < this.temperatureSeries.length) {
                        newSeries[i] = this.temperatureSeries[i];
                    }
                    else {
                        newSeries[i] = temps[i - this.temperatureSeries.length];
                    }
                }

                this.temperatureSeries = newSeries;
        }
        return this.logicalSize;
    }

}
