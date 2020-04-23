package com.company;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Spliterator;

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
        content = "";
    }

    void init()
    {
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
            // System.out.println(sentence.number + " => " + sentence.value + " => " + sentence.noOfWords + " => " + sentence.paragraphNumber);
            sentence.printSentences();
            System.out.println("->" + sentence.score);
            System.out.println();
        }
    }
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

    void scoreWords()
    {
        for (Paragraph myParagraph: paragraphs ) {
            for (Sentence mySentence : sentences) {
                for (Word myWord : mySentence.words) {

                    myWord.scoreTFIDF = tfIdf(mySentence,myParagraph,myWord.value);

                }
                mySentence.scoreSentence();
            }
        }
    }
    void printScores()
    {
        for (Paragraph myParagraph: paragraphs ) {
            for (Sentence mySentence : sentences) {
                for (Word myWord : mySentence.words) {

                    System.out.println(myWord.value + " -> " + myWord.scoreTFIDF);

                }
                System.out.println();

            }
        }

    }
}