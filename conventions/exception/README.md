# 공통 예외 처리 가이드

```java

/**
 * 샘플 단건 조회
 */
@Transactional(readOnly = true)
public GetSampleRes getSample(UUID id) {

    Sample sample = sampleRepository.findById(id)
        .orElseThrow(() -> new GlobalException(ErrorCase.NOT_FOUND));

    return mapper.toGetSampleRes(sample);
}
```

예외를 발생시키는 코드 예시입니다.   
`GlobalException`은 예외를 발생시키는 클래스로, `ErrorCase`를 사용하여 예외의 상태를 나타냅니다.

```java

@Getter
@RequiredArgsConstructor
public enum ErrorCase {

    /* 글로벌 1000번대 */

    // 권한 없음 403
    NOT_AUTHORIZED(HttpStatus.FORBIDDEN, 1000, "해당 요청에 대한 권한이 없습니다."),
    // 잘못된 형식의 입력 400
    INVALID_INPUT(HttpStatus.BAD_REQUEST, 1001, "유효하지 않은 입력값입니다."),
    // 존재하지 않는 값 404
    NOT_FOUND(HttpStatus.NOT_FOUND, 1002, "존재하지 않는 입력값입니다."),
    // 시스템 에러 500
    SYSTEM_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 1003, "알 수 없는 에러가 발생했습니다."),

    /* 유저 2000번대 */

    // 존재하지 않는 사용자 404,
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, 2000, "유저를 찾을 수 없습니다."),
    // ... 
    ;

    private final HttpStatus httpStatus; // 응답 상태 코드
    private final Integer code; // 응답 코드. 도메인에 따라 1000번대로 나뉨
    private final String message; // 응답에 대한 설명
}
```

`ErrorCase`는 예외의 상태를 나타내는 Enum 입니다.  
`httpStatus`는 응답 상태 코드, `code`는 특정 에러를 알려주는 응답 코드, `message`는 응답에 대한 설명입니다.

```json 
{
  "code": 1001,
  "message": "유효하지 않은 입력값입니다.",
  "data": {}
}
```

예외 발생 시 응답입니다.  
`HttpStatus`는 응답의 상태코드로 확인할 수 있으므로 제외했습니다.

```java
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CreateSampleReq(

    @Pattern(regexp = "^[a-zA-Z가-힣0-9]{1,20}$", message = "이름은 한글, 영문, 숫자만 20자 이내로 입력 가능합니다.")
    String name,

    @NotNull(message = "금액은 필수값입니다.")
    Long money
) {

}
```

위와 같이 `Validation` 처리를 한 경우, 응답의 `data`에는 `field`와 `message`를 포함하여 에러를 전달합니다.

```json
{
  "code": 1001,
  "message": "유효하지 않은 입력값입니다.",
  "data": [
    {
      "field": "name",
      "message": "이름은 한글, 영문, 숫자만 20자 이내로 입력 가능합니다."
    },
    {
      "field": "money",
      "message": "금액은 필수값입니다."
    }
  ]
}
```

