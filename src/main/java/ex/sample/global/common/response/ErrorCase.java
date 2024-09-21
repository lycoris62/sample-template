package ex.sample.global.common.response;

import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
import static java.net.HttpURLConnection.HTTP_CONFLICT;
import static java.net.HttpURLConnection.HTTP_FORBIDDEN;
import static java.net.HttpURLConnection.HTTP_INTERNAL_ERROR;
import static java.net.HttpURLConnection.HTTP_NOT_FOUND;
import static java.net.HttpURLConnection.HTTP_UNAUTHORIZED;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCase {

    /* 글로벌 1000번대 */

    // 권한 없음 403
    NOT_AUTHORIZED(HTTP_FORBIDDEN, 1000, "해당 요청에 대한 권한이 없습니다."),
    // 잘못된 형식의 입력 400
    INVALID_INPUT(HTTP_BAD_REQUEST, 1001, "유효하지 않은 입력값입니다."),
    // 존재하지 않는 값 404
    NOT_FOUND(HTTP_NOT_FOUND, 1002, "존재하지 않는 입력값입니다."),
    // 시스템 에러 500
    SYSTEM_ERROR(HTTP_INTERNAL_ERROR, 1003, "알 수 없는 에러가 발생했습니다."),

    /* 유저 2000번대 */

    // 존재하지 않는 사용자 404,
    USER_NOT_FOUND(HTTP_NOT_FOUND, 2000, "유저를 찾을 수 없습니다."),
    // 로그인 필요 401
    LOGIN_REQUIRED(HTTP_UNAUTHORIZED, 2001, "로그인이 필요합니다."),
    // 중복된 유저네임 입력 409
    DUPLICATED_USERNAME(HTTP_CONFLICT, 2002, "중복된 유저네임을 입력하셨습니다."),
    // 유효하지 않은 토큰 401
    INVALID_TOKEN(HTTP_UNAUTHORIZED, 2003, "유효하지 않은 토큰입니다."),
    // 만료된 액세스 토큰 401
    EXPIRED_ACCESS_TOKEN(HTTP_UNAUTHORIZED, 2004, "만료된 Access Token"),
    // 만료된 리프레쉬 토큰 401
    EXPIRED_REFRESH_TOKEN(HTTP_UNAUTHORIZED, 2005, "만료된 Refresh Token"),
    ;

    private final int httpStatus; // 응답 상태 코드
    private final Integer code; // 응답 코드. 도메인에 따라 1000번대로 나뉨
    private final String message; // 응답에 대한 설명
}
