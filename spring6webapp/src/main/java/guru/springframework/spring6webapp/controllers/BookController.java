package guru.springframework.spring6webapp.controllers;

import guru.springframework.spring6webapp.services.IBookService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookController {

    private final IBookService bookService;


    public BookController(IBookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public String findAllBooks(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "books";
    }
}