package dev.changuii.project.enums;

public enum ErrorCode {

    // 로그인, 회원가입, 유저관련
    DUPLICATION_EMAIL_EXCEPTION("이메일 중복 에러"),
    EMAIL_NOT_FOUND("존재하지 않는 이메일"),
    USER_NOT_FOUND("존재하지 않는 사용자"),

    // S3
    S3_IMAGE_PUT_EXCEPTION("이미지 업로드 중 에러"),


    // wanted
    WANTED_ALREADY_PROGRESS("이미 wanted가 진행중입니다."),
    WANTED_NOT_FOUND("존재하지 않는 Wanted입니다."),

    // report
    REPORT_NOT_FOUND("존재하지 않는 report입니다."),

    // idempotent
    DUPLICATION_REQUEST("중복된 요청입니다."),


    //TOKEN
    INVALID_TOKEN("유효하지 않은 토큰");


    private String errorData;

    private ErrorCode(String errorData){
        this.errorData = errorData;
    }

    public String getErrorDescription(){
        return this.errorData;
    }

}
