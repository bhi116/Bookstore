package nl.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import nl.bookstore.domain.Category;
import nl.bookstore.domain.CategoryRepository;

@DataJpaTest
public class CategoryRepoTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void findByName(){
        Category category = categoryRepository.findByName("Adventure");
        assertThat(category).isNotNull();
    }

    @Test
    public void createNewCategory(){
        Category category = new Category("Horror");
        categoryRepository.save(category);
        assertThat(category.getName()).isNotEmpty();
    }

    @Test
    public void deleteCategory(){
        Category category = new Category("Scifi");
        categoryRepository.save(category);

        Category findC = categoryRepository.findByName("Scifi");
        assertThat(findC).isNotNull();
        categoryRepository.deleteById(findC.getCategoryid());

        Category deletedC = categoryRepository.findByName("Scifi");
        assertThat(deletedC).isNull();

    }

}
