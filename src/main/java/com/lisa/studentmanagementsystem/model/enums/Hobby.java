package com.lisa.studentmanagementsystem.model.enums;

public enum Hobby {
    SWIMMING("swimming"),
    PAINTING("painting"),
    VIDEO_GAME("video game"),
    BIKE_RIDE("Bike Ridding");

    final String hobbyName;

    Hobby(String hobbyName) {
        this.hobbyName = hobbyName;
    }
    String getTheHobbyDetail(Hobby hobby){
        return hobby.hobbyName;
    }
}
