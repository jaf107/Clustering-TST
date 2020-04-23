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

    public Sentence(String value, int paragraphNumber, int number) {
        this.value = new String(value);
        this.paragraphNumber = paragraphNumber;
        this.sentenceLength = value.length();
        this.noOfWords = 0;
        this.number = number;

        this.noOfWords =  getNoOfWords();

        this.score = 0.0;

        words = new ArrayList<Word>();
        extractWordsFromSentence();
        //Uninitialized;


    }

    String punctuationRemover(String content) {
        String noPunctuationSentence = "";
        //String noPunctuationContent = "";
        for (int i = 0; i < content.length(); i++) {
            if ((content.charAt(i) != '.') && (content.charAt(i) != ',') &&
                    (content.charAt(i) != '!') && (content.charAt(i) != ':') &&
                    (content.charAt(i) != ';') && (content.charAt(i) != '"') &&
                    (content.charAt(i) != '?') && (content.charAt(i) != '-') &&
                    (content.charAt(i) != '.') && (content.charAt(i) != '(') &&
                    (content.charAt(i) != ')') && (content.charAt(i) != '*')) {
                noPunctuationSentence += content.charAt(i);
            } else
                noPunctuationSentence += " ";

        }
        return noPunctuationSentence;
    }

    void extractWordsFromSentence() {
        String[] stop_words = { "a","able","about","after","all","also","am"," ","The",
                "an","and","any","are","as","at","be","because","been","but","by","can","cannot","could","did",
                "do","does","either","else","ever","every","for","from","get","got","had","has","have","he","her","hers","him","his","how","I",
                "if","in","into","is","it","its","just","let","like","likely","may","me",
                "might","most","must","my","neither","no","nor","not","of","off",
                "often","on","only","or","other","our","own","said","say","says","she",
                "should","so","some","than","that","the","their","them","then","there",
                "these","they","this","they're","to","too","that's","us","was","we","were",
                "what","when","where","which","while","who","whom","why","will","with",
                "would","yet","you","your", "you're" };
        String noPunctuation = punctuationRemover(value);
        String[] word = noPunctuation.toLowerCase().split(" ");

        boolean temp = true;

        for (int i = 0; i < word.length; i++) {

            temp = true;
            for (String s: stop_words) {
                if(word[i].equalsIgnoreCase(s))
                    temp = false;

            }
            if(temp == true)
                words.add(new Word(word[i]));
        }
    }
    void scoreSentence()
    {
        for (Word myWord: words) {
            score += myWord.scoreTFIDF;
        }
    }
    void printSentences() {
        for (Word word : words) {
            System.out.print(word.value + " " );
        }
        //System.out.println("Score is " + score);

    }

    public int getNoOfWords() {
        int noOfTempWords = 0;
        for (int i = 0; i < sentenceLength; i++) {
            if (value.charAt(i) == ' ')
                noOfTempWords++;
        }
        noOfTempWords++;
        return noOfTempWords;
    }
}

