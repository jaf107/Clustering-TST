package com.company;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class SummaryTool {
    FileInputStream in;
    FileOutputStream out;
    ArrayList<Sentence> sentences;
    ArrayList<Paragraph> paragraphs;
    int noOfSentences,noOfParagraphs;

    String content;
    SummaryTool()
    {
        this.content = "";
        this.in = null;
        this.out = null;
        noOfParagraphs = 0;
        noOfSentences = 0;
    }

    void init()
    {
        sentences = new ArrayList<Sentence>();
        paragraphs = new ArrayList<Paragraph>();

        noOfSentences = 0;
        noOfParagraphs = 0;

        try{
            in = new FileInputStream("Clustering TST/Input/Sample.txt");
            out = new FileOutputStream("Output.txt");


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    void extractContentFromContext() {



    }
}
