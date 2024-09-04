package ex.sample.global.common.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse<T> implements Serializable {

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
    public static CommonResponse<CommonEmptyRes> error(ResultCase resultCase) {

        return CommonResponse.<CommonEmptyRes>builder()
            .code(resultCase.getCode())
            .message(resultCase.getMessage())
            .data(new CommonEmptyRes())
            .build();
    }

    /**
     * 에러 발생 시 특정 에러에 맞는 응답하는 메서드 - data 필드가 필요한 경우
     */
    public static <T> CommonResponse<T> error(ResultCase resultCase, T data) {
        return CommonResponse.<T>builder()
            .code(resultCase.getCode())
            .message(resultCase.getMessage())
            .data(data)
            .build();
    }

    /**
     * data 필드가 필요 없는 경우 null 대신 비어있는 객체를 응답하기 위한 DTO
     */
    @JsonIgnoreProperties // 아무 필드가 없을 시 직렬화를 위한 어노테이션
    public record CommonEmptyRes() {

    }
}
