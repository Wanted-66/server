package dev.changuii.project.enums;

public enum ReportStatus {

    VOTING("투표중"),
    ARREST_SUCCESS("검거성공"),
    ARREST_FAIL("검거실패");


    private String description;


    private ReportStatus(String description){
        this.description = description;
    }
}
