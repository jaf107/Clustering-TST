package com.company;

public class Word {
     int length;
     int scoreTF;
     int scoreIDF;

     String value;
    Word(String value)
    {
        this.value = value;
        this.length = value.length();
    }
}
