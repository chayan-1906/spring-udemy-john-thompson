package guru.springframework.spring6webapp.services;

import guru.springframework.spring6webapp.domains.Book;
import guru.springframework.spring6webapp.repositories.IBookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements IBookService {

    private final IBookRepository bookRepository;

    public BookServiceImpl(IBookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * @return
     */
    @Override
    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }
}
