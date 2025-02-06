package nl.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import nl.bookstore.domain.Book;
import nl.bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BookRepository repository) {
		return (args) -> {	
			Book b1 = new Book("Harry Potter ja liekehtivä pikari", "J.K. Rowling", 2001, 
			"9789513187057", 30.95);
			Book b2 = new Book("Harry Potter ja salaisuuksien kammio", "J.K. Rowling", 1999, 
			"9789520401825", 25.95);
			Book b3 = new Book("Harry Potter ja Feeniksin kilta", "J.K. Rowling", 2003, 
			"9789520401955", 28.95);

			repository.save(b1);
			repository.save(b2);
			repository.save(b3);
		};
	}

}
