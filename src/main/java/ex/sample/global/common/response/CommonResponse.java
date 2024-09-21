package ex.sample.global.common.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class CommonResponse<T> {

    @Schema(description = "응답 코드", example = "0")
    private Integer code; // 커스텀 응답 코드
    @Schema(description = "응답 메시지", example = "정상 처리 되었습니다.")
    private String message; // 응답에 대한 설명
    @Schema(description = "응답 데이터", example = "{}")
    private T data; // 응답에 필요한 데이터

    /**
     * data 필드에 값을 넣을 때 사용하는 메서드 - data 필드가 필요 없는 경우
     */
    public static CommonResponse<CommonEmptyRes> success() {
        return getSuccessRes(new CommonEmptyRes());
    }

    /**
     * data 필드에 값을 넣을 때 사용하는 메서드 - data 필드가 필요한 경우
     */
    public static <T> CommonResponse<T> success(T data) {
        return getSuccessRes(data);
    }

    private static <T> CommonResponse<T> getSuccessRes(T data) {
        return CommonResponse.<T>builder()
            .code(0)
            .message("정상 처리 되었습니다.")
            .data(data)
            .build();
    }

    /**
     * 에러 발생 시 특정 에러에 맞는 응답하는 메서드 - data 필드가 필요 없는 경우
     */
    public static CommonResponse<CommonEmptyRes> error(ErrorCase errorCase) {

        return CommonResponse.<CommonEmptyRes>builder()
            .code(errorCase.getCode())
            .message(errorCase.getMessage())
            .data(new CommonEmptyRes())
            .build();
    }

    /**
     * 에러 발생 시 특정 에러에 맞는 응답하는 메서드 - data 필드가 필요한 경우
     */
    public static <T> CommonResponse<T> error(ErrorCase errorCase, T data) {
        return CommonResponse.<T>builder()
            .code(errorCase.getCode())
            .message(errorCase.getMessage())
            .data(data)
            .build();
    }
}
