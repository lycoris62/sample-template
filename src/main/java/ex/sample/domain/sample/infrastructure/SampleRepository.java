package ex.sample.domain.sample.infrastructure;

import ex.sample.domain.sample.domain.Sample;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SampleRepository extends JpaRepository<Sample, UUID> {

}
