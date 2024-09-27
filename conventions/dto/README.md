# DTO 가이드

- DTO는 Record 타입으로 생성합니다.
- DTO는 요청과 응답의 디렉토리를 구분합니다.
    - 요청은 request, 응답은 response 디렉토리에 저장합니다.
- DTO의 네이밍은 다음과 같습니다.
    - `<대상><행위><Req|res>`
        - 대상: User, Order, Comment, ...
        - 행위: Get, Create, Add, Update, Edit, Delete
        - ex) UserCreateReq, UserGetRes

```java
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record SampleCreateReq(

    @Pattern(regexp = "^[a-zA-Z가-힣0-9]{1,20}$", message = "이름은 한글, 영문, 숫자만 20자 이내로 입력 가능합니다.")
    String name,

    @NotNull(message = "금액은 필수값입니다.")
    Long money
) {

}
```