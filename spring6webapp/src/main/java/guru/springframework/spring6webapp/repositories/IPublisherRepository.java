package guru.springframework.spring6webapp.repositories;

import guru.springframework.spring6webapp.domains.Publisher;
import org.springframework.data.repository.CrudRepository;

public interface IPublisherRepository extends CrudRepository<Publisher, Long> {
}