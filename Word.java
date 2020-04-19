package com.company;

public class Word {
    private int length;
    private int scoreTF;
    private int scoreIDF;

    private String value;
    Word(String value)
    {
        this.value = value;
        this.length = value.length();

    }
}
