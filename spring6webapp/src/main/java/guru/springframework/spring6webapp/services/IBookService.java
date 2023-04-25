package guru.springframework.spring6webapp.services;

import guru.springframework.spring6webapp.domains.Book;

public interface IBookService {

    Iterable<Book> findAll();
}
