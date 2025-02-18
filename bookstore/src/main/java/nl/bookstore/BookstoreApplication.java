package nl.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import nl.bookstore.domain.Book;
import nl.bookstore.domain.BookRepository;
import nl.bookstore.domain.Category;
import nl.bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BookRepository bookRepository, CategoryRepository categoryRepository) {
		return (args) -> {	

			// lisätään kategorioita
			Category adventure = new Category("Adventure");
			Category comic = new Category("Comic");
			Category fantasy = new Category("Fantasy");

			// tallennetaan kategoriat tietokantaan
			categoryRepository.save(adventure);
			categoryRepository.save(comic);
			categoryRepository.save(fantasy);

			// lisätään kirjoja
			Book b1 = new Book("Harry Potter ja liekehtivä pikari", "J.K. Rowling", 2001, 
			"9789513187057", 30.95, fantasy);
			Book b2 = new Book("Harry Potter ja salaisuuksien kammio", "J.K. Rowling", 1999, 
			"9789520401825", 25.95, fantasy);
			Book b3 = new Book("Harry Potter ja Feeniksin kilta", "J.K. Rowling", 2003, 
			"9789520401955", 28.95, fantasy);

			// tallennetaan kirjat tietokantaan
			bookRepository.save(b1);
			bookRepository.save(b2);
			bookRepository.save(b3);


		};
	}

}
