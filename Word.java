package com.company;

import java.util.List;

public class Word {
    int length;
    double scoreTFIDF;
    ;

    String value;
    Word(String value)
    {
        this.value = value;
        this.length = value.length();
        this.scoreTFIDF = 0.0;

    }



}
