package guru.springframework.spring6reactiveexamples.implementations;

import guru.springframework.spring6reactiveexamples.domain.Person;
import guru.springframework.spring6reactiveexamples.repositories.IPersonRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author padmanabhadas
 */

public class PersonRepositoryImpl implements IPersonRepository {

    Person michael = Person.builder()
            .id(1)
            .firstName("Michael")
            .lastName("Weston")
            .build();
    Person fiona = Person.builder()
            .id(2)
            .firstName("Fiona")
            .lastName("Glenanne")
            .build();
    Person sam = Person.builder()
            .id(3)
            .firstName("Sam")
            .lastName("Axe")
            .build();
    Person jesse = Person.builder()
            .id(4)
            .firstName("Jesse")
            .lastName("Porter")
            .build();

    /**
     * @param id
     * @return
     */
    @Override
    public Mono<Person> getById(final Integer id) {
        return findAll().filter(person -> person.getId().equals(id)).next();
    }

    /**
     * @return
     */
    @Override
    public Flux<Person> findAll() {
        return Flux.just(michael, fiona, sam, jesse);
    }
}
