package nl.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import nl.bookstore.domain.AppUserRepository;
import nl.bookstore.domain.AppUser;

@Service
public class UserDetailServiceImp implements UserDetailsService {
    private final AppUserRepository userRepository;

    @Autowired
	public UserDetailServiceImp(AppUserRepository userRepository) {
		this.userRepository = userRepository;
	}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {   
    	AppUser appUser = userRepository.findByUsername(username);
        if (appUser == null) {
            throw new UsernameNotFoundException("User not found");
        }
        System.out.println("Found user: " + appUser.getUsername());
        return new User(appUser.getUsername(), appUser.getPasswordHash(),
                AuthorityUtils.createAuthorityList(appUser.getRole()));
    }  

}
