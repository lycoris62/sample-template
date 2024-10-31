package ex.sample.domain.sample.infrastructure;

import ex.sample.domain.sample.domain.Sample;
import ex.sample.domain.sample.domain.SampleRepository;
import java.util.UUID;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;

@Primary
public interface SampleJpaRepository extends SampleRepository, JpaRepository<Sample, UUID> {

}
