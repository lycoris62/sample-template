package ex.sample.domain.sample1.repository;

import ex.sample.domain.sample1.domain.Sample;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SampleRepository extends JpaRepository<Sample, UUID> {

}
