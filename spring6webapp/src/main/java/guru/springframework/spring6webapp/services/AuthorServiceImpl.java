package guru.springframework.spring6webapp.services;

import guru.springframework.spring6webapp.domains.Author;
import guru.springframework.spring6webapp.repositories.IAuthorRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements IAuthorService {

    private final IAuthorRepository authorRepository;

    public AuthorServiceImpl(IAuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    /**
     * @return
     */
    @Override
    public Iterable<Author> findAll() {
        return authorRepository.findAll();
    }
}
