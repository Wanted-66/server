package dev.changuii.project.enums;

public enum WantedStatus {

    ARREST("체포완료"),
    SUCCESS("성공"),
    PROGRESS("진행중");

    private String description;

    private WantedStatus(String description){
        this.description=description;
    }
}
