package com.example.codekreigers;

public class Questions {
    private String question, answer;
    public boolean answered;


    public Questions(String question, String answer,boolean answered){
        this.answered = answered;
        this.question = question;
        this.answer = answer;
    }

    public boolean isAnswered() {
        return answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
