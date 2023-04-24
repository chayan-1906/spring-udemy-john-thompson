package guru.springframework.spring6webapp.repositories;

import guru.springframework.spring6webapp.domains.Author;
import org.springframework.data.repository.CrudRepository;

public interface IAuthorRepository extends CrudRepository<Author, Long> {
}
