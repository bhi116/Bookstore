package nl.bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import nl.bookstore.domain.Book;
import nl.bookstore.domain.BookRepository;

@Controller
public class BookRestController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/books")
    public @ResponseBody List<Book> bookListRest(){
        return (List<Book>) bookRepository.findAll();
        
    }

    @GetMapping("/books/{id}")
    public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId){
        return bookRepository.findById(bookId);
    }

}
