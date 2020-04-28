package com.company;

import java.util.List;

public class Word {
    int length;
    double scoreTFIDF;
    String value;
    Word(String value)
    {
        this.value = value;
        this.length = value.length();
        this.scoreTFIDF = 0.0;
    }

    void showScoreTFIDF(){
        System.out.println(" \t\tScore =" + this.scoreTFIDF +" ,Word - "+ this.value + ", Size = "+ this.length);
    }



}
