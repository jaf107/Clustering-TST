package com.company;

public class Main {

    public static void main(String[] args) {
        SummaryTool summary = new SummaryTool();

        summary.init();
        //summary.extractContentFromContext();
        summary.extractSentenceFromContext();
        summary.groupSentencesIntoParagraphs();

        //summary.tokenization();
        //summary.printContent();
        summary.printSentences();
        summary.scoreWords();
        //summary.printScores();

    }

}
