package guru.springframework.spring6reactive.repositories;

import guru.springframework.spring6reactive.domain.Beer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author padmanabhadas
 */

@Repository
public interface IBeerRepository extends ReactiveCrudRepository<Beer, Integer> {
}
