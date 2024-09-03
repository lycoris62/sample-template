package ex.sample.global.exception;

public record InvalidInputRes(
    String field,
    String message
) {

}