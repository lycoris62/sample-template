package ex.sample.domain.sample2.infrastructure;

import ex.sample.domain.sample2.domain.Sample;
import ex.sample.domain.sample2.domain.Sample2Repository;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Sample2RepositoryImpl extends Sample2Repository, JpaRepository<Sample, UUID> {

}
