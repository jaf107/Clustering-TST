package com.company;

public class Data {
    private double value;
    private int clusterNumber;

    Data()
    {

    }

    Data(double v,int n)
    {
        this.clusterNumber = n;
        this.value = v;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getClusterNumber() {
        return clusterNumber;
    }

    public void setClusterNumber(int clusterNumber) {
        this.clusterNumber = clusterNumber;
    }
}
