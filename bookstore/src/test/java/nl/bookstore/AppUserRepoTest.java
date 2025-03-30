package nl.bookstore;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import nl.bookstore.domain.AppUser;
import nl.bookstore.domain.AppUserRepository;

@DataJpaTest
public class AppUserRepoTest {
    @Autowired
    private AppUserRepository userRepository;

    @Test
    public void findByUsername(){
        AppUser user = userRepository.findByUsername("user");
        assertThat(user).isNotNull();
        assertThat(user.getRole()).isEqualTo("USER");
    }

    @Test
    public void createNewUser(){
        AppUser user = new AppUser("user2", "user222", "user2@test.com", "USER");
        userRepository.save(user);
        assertThat(user.getPasswordHash()).isNotEmpty();
    }

    @Test
    public void deleteUser(){
        AppUser user = new AppUser("user3", "password", "user3@test.com", "USER");
        userRepository.save(user);
        
        AppUser foundUser = userRepository.findByUsername("user3");
        assertThat(foundUser).isNotNull();
        userRepository.deleteById(foundUser.getId());

        AppUser deletedUser = userRepository.findByUsername("user3");
        assertThat(deletedUser).isNull();
        }

}
