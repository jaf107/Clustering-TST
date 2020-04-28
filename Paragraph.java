package com.company;

import java.util.ArrayList;

public class Paragraph {
    int number;
    int noOfTotalWords;
    ArrayList<Sentence> sentences;

    Paragraph(int number){
        this.number = number;
        this.noOfTotalWords = 0;
        sentences = new ArrayList<Sentence>();
        this.noOfTotalWords = getTotalNoOfWords();
    }
    int getTotalNoOfWords() {
        int noOfWords = 0;
        for (Sentence temp: sentences ) {
            noOfWords += temp.noOfWords;
        }
        return  noOfWords;
    }
}