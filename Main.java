package com.company;

public class Main {

    public static void main(String[] args) {
        SummaryTool summary = new SummaryTool();

        summary.init();

        summary.extractSentenceFromContext();
        summary.groupSentencesIntoParagraphs();


//        summary.printContent();
//        summary.printSentences();
        summary.scoreWords();
        summary.scoreSentences();
        summary.printScoresOfWords();

//        summary.createSummary();
        summary.printSentences();
//        summary.printScoresOfWords();`
    }

}
