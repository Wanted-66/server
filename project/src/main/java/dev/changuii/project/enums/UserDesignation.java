package dev.changuii.project.enums;

public enum UserDesignation {
    CRIME_KING("범죄왕"),
    NEWBIE("뉴비"),
    INVESTIGATION_KING("수사왕"),
    DETECTIVE_KING("탐정왕"),
    INFORMATION_KING("정보왕");

    private String name;

    private UserDesignation(String name){
        this.name=name;
    }
}
