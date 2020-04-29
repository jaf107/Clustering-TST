package com.company;

import java.util.ArrayList;

public class KMeans {
    int Num_Clusters ;
    int totalData ;

    ArrayList<Sentence> sample;

    private static ArrayList<Data>  dataSet= new ArrayList<Data>();
    private static ArrayList<Centroid> centroids = new ArrayList<Centroid>();

    KMeans(ArrayList<Sentence> sample,int k)
    {
        this.Num_Clusters = k;
        this.sample = sample;
        this.totalData = sample.size();
    }


    void initialize()
    {
        System.out.println("Centroids initialized at:");

        //Randomize a centroid

        //Add it to Centroid ArrayList

        System.out.print("\n");
        return;
    }

    private void kMeanCluster()
    {
        final double bigNumber = Math.pow(10, 10);    // some big number that's sure to be larger than our data range.
        double minimum = bigNumber;                   // The minimum value to beat.
        double distance = 0.0;                        // The current minimum value.
        int sampleNumber = 0;
        int cluster = 0;
        boolean isStillMoving = true;
        Data newData = null;

        // Add in new data, one at a time, recalculating centroids with each new one.
        while(dataSet.size() < totalData)
        {
            newData = new Data(sample.get(sampleNumber).score);
            dataSet.add(newData);
            minimum = bigNumber;
            for(int i = 0; i < Num_Clusters; i++)
            {
                distance = distance(newData, centroids.get(i));
                if(distance < minimum){
                    minimum = distance;
                    cluster = i;
                }
            }
            newData.setClusterNumber(cluster);

            // calculate new centroids.
            for(int i = 0; i < Num_Clusters; i++)
            {
                int totalX = 0;
                int totalInCluster = 0;
                for(int j = 0; j < dataSet.size(); j++)
                {
                    if(dataSet.get(j).getClusterNumber() == i){
                        totalX += dataSet.get(j).getValue();
                        totalInCluster++;
                    }
                }
                if(totalInCluster > 0){
                    centroids.get(i).X(totalX / totalInCluster);
                }
            }
            sampleNumber++;
        }

        // Now, keep shifting centroids until equilibrium occurs.
        while(isStillMoving)
        {
            // calculate new centroids.
            for(int i = 0; i < Num_Clusters; i++)
            {
                int totalX = 0;

                int totalInCluster = 0;
                for(int j = 0; j < dataSet.size(); j++)
                {
                    if(dataSet.get(j).getClusterNumber() == i){
                        totalX += dataSet.get(j).getValue();
                        totalInCluster++;
                    }
                }
                if(totalInCluster > 0){
                    centroids.get(i).X(totalX / totalInCluster);
                }
            }

            // Assign all data to the new centroids
            isStillMoving = false;

            for(int i = 0; i < dataSet.size(); i++)
            {
                Data tempData = dataSet.get(i);
                minimum = bigNumber;
                for(int j = 0; j < Num_Clusters; j++)
                {
                    distance = distance(tempData, centroids.get(j));
                    if(distance < minimum){
                        minimum = distance;
                        cluster = j;
                    }
                }
                tempData.setClusterNumber(cluster);
                if(tempData.getClusterNumber() != cluster){
                    tempData.setClusterNumber(cluster);
                    isStillMoving = true;
                }
            }
        }
        return;
    }


    double distance(Data d,Centroid c)
    {
        return Math.abs(d.getValue() - c.X());
    }

    void kMeansMain()
    {
        initialize();
        kMeanCluster();


        for(int i = 0; i < Num_Clusters; i++)
        {
            System.out.println("Cluster " + i + " includes:");
            for(int j = 0; j < totalData; j++)
            {
                if(dataSet.get(j).getClusterNumber() == i){
                    System.out.println("     (" + dataSet.get(j).getValue() + ")");
                }
            } // j
            System.out.println();
        } // i

        // Print out centroid results.
        System.out.println("Centroids finalized at:");
        for(int i = 0; i < Num_Clusters; i++)
        {
            System.out.println("     (" + centroids.get(i).X() );
        }
        System.out.print("\n");
        return;
    }
}
