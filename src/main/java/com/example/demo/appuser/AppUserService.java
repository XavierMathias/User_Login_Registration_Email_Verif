/** This class will look for the user once we are trying to log them into their accounts
 * */

package com.example.demo.appuser;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final static String EMAIL_NOT_FOUND = "user with email %s not found";
    private final AppUserRepository appUserRepository;


    // find the user by their email
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        // this will return the user's details using an email, and if it doesn't exist it the function will return a statement
        return appUserRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format(EMAIL_NOT_FOUND, email))); // String.formar will but the email string in parameter in the %s

    }
}
