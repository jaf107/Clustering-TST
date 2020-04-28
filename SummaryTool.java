package com.company;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Spliterator;

public class SummaryTool {
    FileInputStream in;
    FileOutputStream out;
    ArrayList<Sentence> sentences,contentSummary;
    ArrayList<Paragraph> paragraphs;
    int noOfSentences,noOfParagraphs;

    String content;
    SummaryTool() {
        this.content = "";
        this.in = null;
        this.out = null;
        noOfParagraphs = 0;
        noOfSentences = 0;
        content = "";
    }

    void init() {
        sentences = new ArrayList<Sentence>();
        paragraphs = new ArrayList<Paragraph>();

        noOfSentences = 0;
        noOfParagraphs = 0;

        try{
            in = new FileInputStream("Documents/Input/Sample.txt");
            out = new FileOutputStream("Documents/Output/Output.txt");


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
    void extractSentenceFromContext(){
        //String tempString ;
        int nextChar,j=0;
        int prevChar = -1;
        try{
            while((nextChar = in.read()) != -1) {
                j=0;
                char[] temp = new char[100000];
                while((char)nextChar != '.'){ // Sentence SEPARATOR
                    //System.out.println(nextChar + " ");

                    temp[j] = (char)nextChar; //Buffer
                    content += temp[j];
                    if((nextChar = in.read()) == -1){
                        break;
                    }
                    if((char)nextChar == '\n' && (char)prevChar == '\n'){
                        noOfParagraphs++;
                    }
                    j++;
                    prevChar = nextChar;
                }
                content += ".";

                //content += new String((temp));
                sentences.add(new Sentence(new String(temp),noOfParagraphs,noOfSentences));
                //sentences.add(new Sentence(noOfSentences,new  String(temp).trim(),(new String(temp)).trim().length(),noOfParagraphs));
                noOfSentences++;
                prevChar = nextChar;
            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }
    void groupSentencesIntoParagraphs(){
        int paraNum = 0;
        Paragraph paragraph = new Paragraph(0);

        for(int i=0;i<noOfSentences;i++){
            if(sentences.get(i).paragraphNumber == paraNum){
                //continue
            }else{
                paragraphs.add(paragraph);
                paraNum++;
                paragraph = new Paragraph(paraNum);

            }
            paragraph.sentences.add(sentences.get(i));
        }

        paragraphs.add(paragraph);
    }


    void printContent()
    {
        System.out.println(content);
    }

    void printSentences(){
        for(Sentence sentence : sentences){
            sentence.printSentences();
        }
    }


    // SCORING
    void scoreWords()
    {
        for (Paragraph myParagraph: paragraphs ) {
            for (Sentence mySentence : sentences) {
                for (Word myWord : mySentence.words) {

                    myWord.scoreTFIDF = tfIdf(mySentence,myParagraph,myWord.value);

                }
            }
        }
    }

    void preprocessContent()
    {


    }

    void scoreSentences()
    {
        for (Sentence mySentence: sentences ) {
            mySentence.scoreSentence();
        }
    }
    void printScoresOfWords()
    {
        for (Paragraph myParagraph: paragraphs ) {
            for (Sentence mySentence : sentences) {
                for (Word myWord : mySentence.words) {

                    myWord.showScoreTFIDF();

                }


            }
        }

    }


    //Ordering
    void createSummary(){

        for(int j=0;j<=noOfParagraphs;j++){
            int primary_set = paragraphs.get(j).sentences.size()/5;

            //Sort based on score (importance)
            Collections.sort(paragraphs.get(j).sentences,new SentenceComparator());
            for(int i=0;i<=primary_set;i++){
                contentSummary.add(paragraphs.get(j).sentences.get(i));
            }
        }

        //To ensure proper ordering
        Collections.sort(contentSummary,new SentenceComparatorForSummary());

    }



    //TF IDF Calculation
    public double tf(Sentence doc, String term) {
        double result = 0;
        for (Word myWord : doc.words) {
            if (term.equalsIgnoreCase(myWord.value))
                result++;
        }
        return result / (double) doc.getNoOfWords();
    }
    public double idf(Paragraph docs, String term) {
        double n = 0;
        for (Sentence doc : docs.sentences) {
            for (Word myWord : doc.words) {
                if (term.equalsIgnoreCase(myWord.value)) {
                    n++;
                    break;
                }
            }
        }
        return Math.log(docs.getTotalNoOfWords() / n);
    }
    public double tfIdf(Sentence doc, Paragraph docs, String term) {
        return tf(doc, term) * idf(docs, term);

    }

}