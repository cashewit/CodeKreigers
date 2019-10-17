package com.example.codekreigers;

public class DevCard {
    String name, year;
    int devImg;

    DevCard(String name, String year, int devImg){
        this.name = name;
        this.year = year;
        this.devImg = devImg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getDevImg() {
        return devImg;
    }

    public void setDevImg(int devImg) {
        this.devImg = devImg;
    }

}
