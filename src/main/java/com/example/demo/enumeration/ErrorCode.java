package com.example.demo.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    //Common
    INTERNAL_SERVER_ERROR(500, "C001", "internal server error"),
    INVALID_INPUT_VALUE(400, "C002", "잘못된 입력 값입니다."),
    METHOD_NOT_ALLOWED(405, "C003", "지원하지 않는 HTTP요청 메서드 입니다."),
    INVALID_TYPE_VALUE(400, "C004", "잘못된 값 타입입니다."),
    UNAUTHORIZED(401, "C005", "자격증명에 실패하였습니다."),
    USER_NUMBER_ALREADY_EXISTS(409, "C006", "이미 존재하는 핸드폰 번호입니다."),
    AUTH_CHECK_TIMEOUT(400, "C007", "인증 시간이 초과하였습니다"),
    AUTHENTICATION_RESOURCE_NOT_FOUND(401, "C008", "인증 리소스를 찾을 수 없습니다."),
    UNCERTIFIED_PHONE_NUMBER(401, "C009", "SMS인증이 필요합니다."),
    DATA_NOT_EXIST(404, "C010", "데이터가 존재하지 않습니다."),
    MESSAGE_SEND_ERROR(500, "C010", "메시지 전송 에러."),
    SNS_LOGIN_PROCESSING_ERROR(400, "C011", "SNS로그인을 처리할 수 없습니다."),
    INTERNAL_AUTHENTICATION(500, "C012", "내부적으로 발생한 시스템 문제로 인해 인증 요청을 처리할 수 없습니다."),
    ILLEGAL_SMS_CHECK_CODE(401, "C013", "SMS 확인 코드가 맞지 않습니다."),
    VERIFICATION_PASSWORD(401, "C014", "패스워드가 일치하지 않습니다."),
    MISSING_PARAMETER(400, "C015", "파라미터값이 누락되었습니다."),
    UNSUPPORTED_MEDIA_TYPE(400, "C016", "지원하지 않는 미디어 타입입니다. Content-Type을 application/json 로 설정해 주세요"),
    CONVERTED_FAIL(500, "C017", "회원정보 디코딩에 실패하였습니다. 회원 정보가 암호화 되어 있지 않거나, 컨버터를 확인하십시오"),
    DATA_INTEGRITY_CONSTRAINT_VIOLATION(409, "C018", "무결성 제약조건에 위배되는 요청입니다. 요청값을 확인하십시오"),
    EXCEEDED_SEND_SMS(400, "C019", ""), //에러 메시지 변수 처리로 Exception발생 위치에 존재
    SEND_MAIL_EXCEPTION(500, "C020", "메일 전송 에러"),
    ILLEGAL_ARGUMENT(500, "C021", "허용되지 않은 값 타입입니다."),

    //User
    USER_ALREADY_EXIST(409, "U001", "이미 존재하는 회원입니다."),
    USER_LOGIN_ID_ALREADY_EXISTS(409, "U002", "이미 가입되어 있는 유저입니다."),
    NO_AUTHORITY(403, "U003", "권한이 없습니다."),
    NEED_LOGIN(401, "U004", "로그인이 필요합니다."),
    AUTHENTICATION_NOT_FOUND(401, "U005", "Security Context에 인증 정보가 없습니다."),
    USER_ALREADY_LOGOUT(400, "U006", "이미 로그아웃한 상태입니다."),
    USER_NOT_FOUND(404, "U007", "존재하지 않는 회원입니다."),
    DO_NOT_CREATE_FILE(500, "U008", "파일을 생성할 수 없습니다."),
    DO_NOT_DELETE_FILE(404, "U009", "파일을 삭제할 수 없습니다."),
    PREVIOUSLY_USED_PASSWORD(409, "U010", "이전에 사용한 비밀번호 입니다."),
    INVALID_PASSWORD(400, "U011", "비밀번호 확인이 올바르지 않습니다."),

    //profile
    PROFILE_NOT_MATCH_USER(404, "P001", "유저 정보에 등록되지 않은 프로필입니다."),
    PROFILE_NOT_FOUND(404, "P002", "유저 프로필 정보를 찾을 수 없습니다."),
    PROFILE_PHOTO_NOT_FOUND(404, "P003", "이전에 이용한 프로필 사진을 찾을수 없습니다."),
    PROFILE_OVERCOUNT(404, "P004", "프로필 갯수는 4개를 초과할 수 없습니다."),

    //temperture
    DATE_ALREADY_EXIST(409, "T001", "이미 동일한 시간에 측정한 값이 있습니다."),

    //security
    INVALID_TOKEN(401, "S001", "잘못된 JWT 서명입니다."),
    EXPIRED_TOKEN(401, "S002", "만료된 JWT 토큰입니다."),
    UNSUPPORTED_TOKEN(401, "S003", "지원되지 않는 JWT 토큰입니다."),
    ILLEGAL_ARGUMENT_TOKEN(401, "S004", "JWT 토큰이 잘못되었습니다."),
    NOT_FOUND(404, "S005", "페이지를 찾을 수 없습니다."),
    INVALID_AUTHENTICATION(404, "S006", "잘못된 인증정보 입니다."),
    ACCESS_DENIED(406, "S007", "접근이 불가합니다."),

    //symptom
    EXCEEDED_NUMBER_OF_PHOTOS(400, "SYM001", "허용 가능한 사진 갯수를 초과하였습니다."),

    //setting
    DO_NOT_LOGIN_WITHDRAWAL_USER(403, "SE001", "해당 계정으로 가입된 계정이 운영 방침에 의해 차단되었거나 회원탈퇴 규정에 따라 " +
            "탈퇴한 지 1개월이 지나지 않은 계정으로 가입이 불가합니다."),

    //system
    MAX_UPLOAD_SIZE_EXCEED(400, "SY001", "허용가능한 파일 업로드 사이즈를 초과하였습니다."),
    CONVERTER_ERROR(404, "SY002", "개인정보 암호화 실패"),
    WEB_CLIENT_ERROR(404, "SY003", "웹 클라이언트 호출 에러"),

    //etc
    NO_APP_VERSION_DATA(404,"E001","앱 버전이 존재하지 않습니다."),
    SEND_NOTIFICATION_ERROR(404, "E002", ""), // 해당 메시지는 sendNotificationException에 존재
    USER_FCM_TOKEN_NOT_EXIST(404, "E003", ""); // 해당 메시지는 userFCMTokenNotExistException에 존재


    private int status;
    private String code;
    private String message;

    public void setMessage(String message) {
        this.message = message;
    }
}

