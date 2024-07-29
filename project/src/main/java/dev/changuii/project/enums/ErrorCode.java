package dev.changuii.project.enums;

public enum ErrorCode {

    // 로그인, 회원가입
    DUPLICATION_EMAIL_EXCEPTION("이메일 중복 에러"),

    // S3
    S3_IMAGE_PUT_EXCEPTION("이미지 업로드 중 에러");


    // wanted


    private String errorData;

    private ErrorCode(String errorData){
        this.errorData = errorData;
    }

    public String getErrorDescription(){
        return this.errorData;
    }

}
