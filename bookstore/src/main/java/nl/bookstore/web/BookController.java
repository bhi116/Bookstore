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
import nl.bookstore.domain.CategoryRepository;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/booklist")
    public String bookList(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "booklist";      //booklist.html
    }

    @GetMapping("/add")     //lisää kirja
    public String addBook(Model model){
        model.addAttribute("book", new Book());
        model.addAttribute("categories", categoryRepository.findAll());
        return "addbook";       //addbook.html
    }

    @PostMapping("/save")       //tallenna kirja
    public String save(@ModelAttribute Book book){
        bookRepository.save(book);
        System.out.println("Tallennettu kirja: " + book);
        return "redirect:booklist";
    }

    @GetMapping("/delete/{id}")     //poista kirja
    public String deleteBook(@PathVariable("id") Long bookId, Model model){
        bookRepository.deleteById(bookId);
        return "redirect:/booklist";
    }

    @GetMapping("/edit/{id}")       //muokkaa kirjaa
    public String editBook(@PathVariable("id") Long bookId, Model model) {
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isPresent()) {
            model.addAttribute("book", book.get());
            return "editbook";      //editbook.html
        }
        return "redirect:/booklist";        //jos kirjaa ei löydy -> booklist.html
    }

    @PostMapping("/update/{id}")        //päivittää muokatun kirjan tiedot
    public String updateBook(@PathVariable("id") Long bookId, @ModelAttribute Book updatedBook) {
        Optional<Book> bookData = bookRepository.findById(bookId);
        if (bookData.isPresent()) {
            Book book = bookData.get();
            book.setTitle(updatedBook.getTitle());
            book.setAuthor(updatedBook.getAuthor());
            book.setPublicationYear(updatedBook.getPublicationYear());
            book.setIsbn(updatedBook.getIsbn());
            book.setPrice(updatedBook.getPrice());

            bookRepository.save(book);
        }
        return "redirect:/booklist";
    }

}
