package ex.sample.domain.sample.implementation;

import ex.sample.domain.model.Money;
import ex.sample.domain.sample.domain.Sample;
import ex.sample.domain.sample.infrastructure.SampleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class SampleSaver {

    private final SampleRepository sampleRepository;

    public Sample create(Sample sample) {
        return sampleRepository.save(sample);
    }

    public Sample create(String name, long money) {
        Sample sample = Sample.create(name, new Money(money));
        return sampleRepository.save(sample);
    }
}
