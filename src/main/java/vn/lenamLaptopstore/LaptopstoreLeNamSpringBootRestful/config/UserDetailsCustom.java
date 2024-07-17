package vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.config;

import java.util.Collections;
import java.util.Optional;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.service.UserService;

@Component("userDetailsService")
public class UserDetailsCustom implements UserDetailsService {

    private final UserService userService;

    public UserDetailsCustom(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.domain.User> user = this.userService
                .getUserByEmail(username);

        if (!user.isPresent()) {
            throw new UsernameNotFoundException("Username not valid");
        }

        return new User(user.get().getEmail(), user.get().getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }

}
