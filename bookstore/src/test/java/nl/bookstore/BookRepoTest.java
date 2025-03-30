package nl.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import nl.bookstore.domain.*;

@DataJpaTest
public class BookRepoTest {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void createNewBook(){
        Category adventure = categoryRepository.findByName("Adventure");
        Book book = new Book("Seikkailun salat", "Kirjailija", 2025, "999999", 99.99, adventure);
        bookRepository.save(book);
        assertThat(book.getIsbn()).isNotEmpty();
    }

    @Test
    public void findByTitle(){
        List<Book> books = bookRepository.findByTitle("Harry Potter ja liekehtivä pikari");
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getAuthor()).isEqualTo("J.K. Rowling");
    }

    @Test
    public void deleteBook(){
        Category adventure = categoryRepository.findByName("Adventure");
        Book book = new Book("Seikkailun salat 2", "Kirjailija", 2026, "999998", 99.98, adventure);
        bookRepository.save(book);
        assertThat(bookRepository.findByTitle("Seikkailun salat 2")).hasSize(1);

        bookRepository.deleteById(book.getId());
        assertThat(bookRepository.findByTitle("Seikkailun salat 2")).isEmpty();

    }

}
