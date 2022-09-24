package com.example.demo.registration;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRole;
import com.example.demo.appuser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final AppUserService appUserService;
    private final EmailValidator emailValidator;
    public String register(RegistrationRequest request) {
        String email = request.getEmail();
        boolean isValidEmail = emailValidator.test(email);
        if(!isValidEmail) {
            throw new IllegalStateException(String.format("%s not valid", email));
        }
        return appUserService.signUpUser(new AppUser(
                request.getFirstName(), request.getLastName(), request.getUsername(), request.getPassword(), AppUserRole.USER
        ));
    }
}
