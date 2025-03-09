package nl.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import nl.bookstore.domain.AppUser;
import nl.bookstore.domain.AppUserRepository;
import nl.bookstore.domain.Book;
import nl.bookstore.domain.BookRepository;
import nl.bookstore.domain.Category;
import nl.bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CommandLineRunner demo(BookRepository bookRepository, CategoryRepository categoryRepository, 
                                  AppUserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        return (args) -> {    
            // Lisätään kategorioita
            Category adventure = new Category("Adventure");
            Category comic = new Category("Comic");
            Category fantasy = new Category("Fantasy");

            // Tallennetaan kategoriat tietokantaan
            categoryRepository.save(adventure);
            categoryRepository.save(comic);
            categoryRepository.save(fantasy);

            // Lisätään kirjoja
            Book b1 = new Book("Harry Potter ja liekehtivä pikari", "J.K. Rowling", 2001, 
                "9789513187057", 30.95, fantasy);
            Book b2 = new Book("Harry Potter ja salaisuuksien kammio", "J.K. Rowling", 1999, 
                "9789520401825", 25.95, fantasy);
            Book b3 = new Book("Harry Potter ja Feeniksin kilta", "J.K. Rowling", 2003, 
                "9789520401955", 28.95, fantasy);

            // Tallennetaan kirjat tietokantaan
            bookRepository.save(b1);
            bookRepository.save(b2);
            bookRepository.save(b3);

            log.info("fetch all books");
            for (Book book : bookRepository.findAll()) {
                log.info(book.toString());
            }

            // Lisätään käyttäjiä, jos niitä ei ole
            if (userRepository.count() == 0) { 
                AppUser admin = new AppUser("admin", passwordEncoder.encode("admin123"), "admin@email.com", "ADMIN");
                AppUser user = new AppUser("user", passwordEncoder.encode("user123"), "user@email.com", "USER");

                userRepository.save(admin);
                userRepository.save(user);

                log.info("Demo users added!");
            }
        };
    }
}

