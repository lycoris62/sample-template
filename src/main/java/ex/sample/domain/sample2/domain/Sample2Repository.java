package ex.sample.domain.sample2.domain;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface Sample2Repository {

    Sample save(Sample sample);

    void delete();

    void update();

    Optional<Sample> findById(UUID id);

    Page<Sample> findAll(Pageable pageable);
}
