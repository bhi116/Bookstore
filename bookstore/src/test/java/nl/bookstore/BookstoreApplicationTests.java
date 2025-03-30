package nl.bookstore;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import nl.bookstore.web.*;

@SpringBootTest
public class BookstoreApplicationTests {

	@Autowired
	private BookController bookController;
	@Autowired
	private BookRestController bookRestController;
	@Autowired
	private CategoryController categoryController;

	@Test
	public void contextLoads() throws Exception{
		assertThat(bookController).isNotNull();
		assertThat(bookRestController).isNotNull();
		assertThat(categoryController).isNotNull();
	}

}
