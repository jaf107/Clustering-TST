package com.company;

import java.util.ArrayList;

public class Sentence {
    private int paragraphNumber;
    private int sentenceLength;

//    private int uppercaseWords;
//    private int sentencePosition;
//    private int sentenceCentrality;
//    private int sentenceSimilarity;

    private double score;
    private int noOfWords;
    private String value;

    ArrayList<Word> words;
    public Sentence(String value,int paragraphNumber)
    {
        this.value = new String(value);
        this.paragraphNumber = paragraphNumber;
        this.sentenceLength = value.length();
        this.noOfWords = 0;

        for(int i=0;i<sentenceLength;i++) {
            if(value.charAt(i) == ' ')
                noOfWords++;
        }
        noOfWords++;
        this.score = 0.0;

        words = new ArrayList<Word>();

        //Uninitialized;


    }



}
