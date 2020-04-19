package com.company;

import java.util.ArrayList;

public class Paragraph {
    private int number;
    ArrayList<Sentence> sentences;

    Paragraph(int number){
        this.number = number;
        sentences = new ArrayList<Sentence>();
    }
}
