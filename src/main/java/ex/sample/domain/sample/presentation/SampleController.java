package ex.sample.domain.sample.presentation;

import ex.sample.domain.sample.application.SampleService;
import ex.sample.domain.sample.dto.request.CreateSampleReq;
import ex.sample.domain.sample.dto.response.CreateSampleRes;
import ex.sample.domain.sample.dto.response.GetSampleRes;
import ex.sample.global.common.response.CommonResponse;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/samples") // URL 자원은 복수형으로 사용
public class SampleController {

    private final SampleService sampleService;

    /**
     * 샘플 단건 조회
     */
    @GetMapping("/{id}")
    public CommonResponse<GetSampleRes> getSample(@PathVariable("id") UUID id) {
        GetSampleRes response = sampleService.getSample(id);
        return CommonResponse.success(response);
    }

    /**
     * 샘플 리스트 조회
     */
    @GetMapping
    public CommonResponse<Slice<GetSampleRes>> getSample(
        @PageableDefault(sort = "createdAt", direction = Direction.DESC) Pageable pageable
    ) {
        Slice<GetSampleRes> response = sampleService.getSampleList(pageable);
        return CommonResponse.success(response);
    }

    /**
     * 샘플 생성
     */
    @PostMapping
    public CommonResponse<CreateSampleRes> createSample(@Validated @RequestBody CreateSampleReq request) {
        CreateSampleRes response = sampleService.createSample(request);
        return CommonResponse.success(response);
    }
}
