package ex.sample.domain.sample.dto.response;

import java.util.UUID;

public record CreateSampleRes(
    UUID id,
    String name,
    Long money
) {

}
