package com.company;

import java.util.ArrayList;

public class KMeans {
    int Num_Clusters = 3;
    int totalData ;

    ArrayList<Sentence> sample;

    private static ArrayList<Data>  dataSet= new ArrayList<Data>();
    private static ArrayList<Centroid> centroids = new ArrayList<Centroid>();



    double distance(Data d,Centroid c)
    {
        return Math.abs(d.getValue() - c.X());
    }
}
