package ex.sample.domain.sample.infrastructure;

import ex.sample.domain.sample.domain.Sample;
import ex.sample.domain.sample.domain.SampleRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public class SampleInMemoryRepository implements SampleRepository {

    private final Map<UUID, Sample> sampleMap = new HashMap<>();

    @Override
    public Optional<Sample> findById(UUID id) {
        return Optional.ofNullable(sampleMap.get(id));
    }

    @Override
    public Page<Sample> findAll(Pageable pageable) {
        return new PageImpl<>(List.copyOf(sampleMap.values()), pageable, sampleMap.size());
    }

    @Override
    public Sample save(Sample sample) {
        sampleMap.put(sample.getId(), sample);
        return sample;
    }
}
