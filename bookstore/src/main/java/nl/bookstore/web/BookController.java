package nl.bookstore.web;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import nl.bookstore.domain.Book;
import nl.bookstore.domain.BookRepository;

@Controller
public class BookController {

    @Autowired
    private BookRepository repository;

    @GetMapping("/booklist")
    public String bookList(Model model) {
        model.addAttribute("books", repository.findAll());
        return "booklist";      //booklist.html
    }

    @GetMapping("/add")     //lisää kirja
    public String addBook(Model model){
        model.addAttribute("book", new Book());
        return "addbook";       //addbook.html
    }

    @PostMapping("/save")       //tallenna kirja
    public String save(Book book){
        repository.save(book);
        return "redirect:booklist";
    }

    @GetMapping("/delete/{id}")     //poista kirja
    public String deleteBook(@PathVariable("id") Long bookId, Model model){
        repository.deleteById(bookId);
        return "redirect:/booklist";
    }

    @GetMapping("/edit/{id}")       //muokkaa kirjaa
    public String editBook(@PathVariable("id") Long bookId, Model model) {
        Optional<Book> book = repository.findById(bookId);
        if (book.isPresent()) {
            model.addAttribute("book", book.get());
            return "editbook";      //editbook.html
        }
        return "redirect:/booklist";        //jos kirjaa ei löydy -> booklist.html
    }

    @PostMapping("/update/{id}")        //päivittää muokatun kirjan tiedot
    public String updateBook(@PathVariable("id") Long bookId, @ModelAttribute Book updatedBook) {
        Optional<Book> bookData = repository.findById(bookId);
        if (bookData.isPresent()) {
            Book book = bookData.get();
            book.setTitle(updatedBook.getTitle());
            book.setAuthor(updatedBook.getAuthor());
            book.setPublicationYear(updatedBook.getPublicationYear());
            book.setIsbn(updatedBook.getIsbn());
            book.setPrice(updatedBook.getPrice());

            repository.save(book);
        }
        return "redirect:/booklist";
    }

}
