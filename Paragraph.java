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
    int getTotalNoOfWords()
    {
        int noOfWords = 0;
        for (Sentence temp: sentences ) {
            noOfWords += temp.noOfWords;
        }
        return  noOfWords;
    }

    /*
    public double tf(Sentence doc, String term) {
        double result = 0;
        for (Word myWord : doc.words) {
            if (term.equalsIgnoreCase(myWord.value))
                result++;
        }
        return result / (double) doc.getNoOfWords();
    }

    /**
     * @param docs list of list of strings represents the dataset
     * @param term String represents a term
     * @return the inverse term frequency of term in documents

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


     * @param doc  a text document
     * @param docs all documents
     * @param term term
     * @return the TF-IDF of term

    public double tfIdf(Sentence doc, Paragraph docs, String term) {
        return tf(doc, term) * idf(docs, term);

    }

    void scoreWords()
    {
        for (Sentence mySentence: sentences ) {
            for (Word myWord: mySentence.words ) {

                myWord.scoreTFIDF = tfIdf(mySentence,,myWord);

            }

        }
    }*/
}