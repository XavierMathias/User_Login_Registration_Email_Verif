/** This class will look for the user once we are trying to log them into their accounts
 * */

package com.example.demo.appuser;

import com.example.demo.registration.RegistrationService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final static String EMAIL_NOT_FOUND = "user with email %s not found";
    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    // find the user by their email
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        // this will return the user's details using an email, and if it doesn't exist it the function will return a statement
        return appUserRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format(EMAIL_NOT_FOUND, email))); // String.formar will but the email string in parameter in the %s

    }

    public String signUpUser(AppUser appUser){
        boolean userExists = appUserRepository.findByEmail(appUser.getEmail()).isPresent(); // checks if the email exists in the repository

        if(userExists){
            throw new IllegalStateException(String.format("email %s taken", appUser.getEmail())); // states that the email is already taken
        }

        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword()); // encodes the user's password

        appUser.setPassword(encodedPassword); // sets the user's password as encoded

        // TODO: SEND confirmation token
        return "it works";

    }
}
