package com.itDidacticsTest;

import javax.swing.*;

public class testAssets {

    private String testQuestion, answer1,answer2,answer3,answer4, correctAnswer;

    public testAssets(String testQuestion, String answer1, String answer2, String answer3, String answer4, String correctAnswer) {
        this.correctAnswer = correctAnswer;
        this.testQuestion = testQuestion;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
    }

    @Override
    public String toString() {
        return "testAssets{" +
                "correctAnswer=" + correctAnswer +
                ", testQuestion='" + testQuestion + '\'' +
                ", answer1='" + answer1 + '\'' +
                ", answer2='" + answer2 + '\'' +
                ", answer3='" + answer3 + '\'' +
                ", answer4='" + answer4 + '\'' +
                '}';
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getTestQuestion() {
        return testQuestion;
    }

    public void setTestQuestion(String testQuestion) {
        this.testQuestion = testQuestion;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }
}
