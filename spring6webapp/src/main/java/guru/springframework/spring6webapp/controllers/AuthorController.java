package guru.springframework.spring6webapp.controllers;

import guru.springframework.spring6webapp.services.IAuthorService;
import guru.springframework.spring6webapp.services.IBookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthorController {

    private final IAuthorService authorService;

    public AuthorController(IAuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/authors")
    public String findAllAuthors(Model model) {
        model.addAttribute("authors", authorService.findAll());
        return "authors";
    }
}
