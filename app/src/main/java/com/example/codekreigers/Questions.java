package com.example.codekreigers;

public class Questions {
    private String answer, location;
    int question;
    public boolean answered;


    public Questions(int question, String answer,boolean answered, String location){
        this.answered = answered;
        this.question = question;
        this.answer = answer;
        this.location = location;
    }

    public boolean isAnswered() {
        return answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }

    public int getQuestion() {
        return question;
    }

    public void setQuestion(int question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
