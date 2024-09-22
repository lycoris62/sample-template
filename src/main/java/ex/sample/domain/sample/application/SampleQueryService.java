package ex.sample.domain.sample.application;

import ex.sample.domain.sample.domain.Sample;
import ex.sample.domain.sample.dto.response.GetSampleRes;
import ex.sample.domain.sample.implementation.SampleReader;
import ex.sample.domain.sample.mapper.SampleMapper;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SampleQueryService {

    private final SampleReader sampleReader;
    private final SampleMapper sampleMapper;
    
    /**
     * 샘플 단건 조회
     */
    @Transactional(readOnly = true)
    public GetSampleRes getSample(UUID id) {
        Sample sample = sampleReader.read(id);
        return sampleMapper.toGetSampleRes(sample);
    }

    /**
     * 샘플 리스트 조회
     * TODO : 페이징 처리를 List, Slice, Page 중 어떤 것을 사용할지 고민
     */
    @Transactional(readOnly = true)
    public Slice<GetSampleRes> getSampleList(Pageable pageable) {
        return sampleReader.readAll(pageable)
            .map(sampleMapper::toGetSampleRes);
    }
}
