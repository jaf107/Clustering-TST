package com.company;

import java.util.ArrayList;

public class Sentence {
     int paragraphNumber;
     int sentenceLength;
     int number;

//    private int uppercaseWords;
//    private int sentencePosition;
//    private int sentenceCentrality;
//    private int sentenceSimilarity;

     double score;
     int noOfWords;
     String value;

    ArrayList<Word> words;
    public Sentence(String value,int paragraphNumber,int number)
    {
        this.value = new String(value);
        this.paragraphNumber = paragraphNumber;
        this.sentenceLength = value.length();
        this.noOfWords = 0;
        this.number = number;

        for(int i=0;i<sentenceLength;i++) {
            if(value.charAt(i) == ' ')
                noOfWords++;
        }
        noOfWords++;
        this.score = 0.0;

        words = new ArrayList<Word>();
        extractWordsFromSentence();
        //Uninitialized;


    }
    String punctuationRemover(String content)
    {
        String noPunctuationSentence = "";
        //String noPunctuationContent = "";
        for(int i=0;i<content.length();i++) {
            if ((content.charAt(i) != '.') && (content.charAt(i) != ',') &&
                    (content.charAt(i) != '!') && (content.charAt(i) != ':') &&
                    (content.charAt(i) != ';') && (content.charAt(i) != '"') &&
                    (content.charAt(i) != '?') && (content.charAt(i) != '-') &&
                    (content.charAt(i) != '.') && (content.charAt(i) != '(') &&
                    (content.charAt(i) != ')') && (content.charAt(i) != '*'))
            {
                noPunctuationSentence += content.charAt(i);
            }
            else
                noPunctuationSentence += " ";

        }
        return noPunctuationSentence;
    }

    void extractWordsFromSentence()
    {
        String noPunctuation = punctuationRemover(value);
        String[] word = noPunctuation.split(" ");
        for(int i=0;i<word.length;i++)
        {
            words.add(new Word(word[i]));
        }
    }

    void printSentences()
    {
        for (Word word: words ) {
            System.out.print(word.value + " ");
        }

    }


}
