package ex.sample.domain.sample.implementation;

import ex.sample.domain.sample.domain.Sample;
import ex.sample.domain.sample.infrastructure.SampleRepository;
import ex.sample.global.common.response.ErrorCase;
import ex.sample.global.exception.GlobalException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SampleReader {

    private final SampleRepository sampleRepository;

    public Sample read(UUID id) {
        return sampleRepository.findById(id)
            .orElseThrow(() -> new GlobalException(ErrorCase.NOT_FOUND));
    }

    public Slice<Sample> readAll(Pageable pageable) {
        return sampleRepository.findAll(pageable);
    }
}
