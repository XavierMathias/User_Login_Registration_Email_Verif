/**This class will act as the main place where the user will interact with the program and register
 * */


package com.example.demo.appuser;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.persistence.Id;
import java.util.Collection;
import java.util.Collections;

@Getter // creating get methods
@Setter // creating set methods
@EqualsAndHashCode
@NoArgsConstructor // creates a default constructor without having to make one
@Entity // this will make this class an entity for the database, meaning a table with columns and
public class AppUser implements UserDetails {

    @Id
    @SequenceGenerator(
            name = "student_sequence",
            allocationSize = 1, // how much should each id number increment, in this case it would be just adding 1 each time
            sequenceName = "student_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    @Enumerated(
            EnumType.STRING // when returning an appUserRole, it will return as a string
    )
    private AppUserRole appUserRole; // ADMIN or USER
    private Boolean locked = false; // is the account locked
    private Boolean enabled = false; // is the account enabled, and will be TRUE once the user has conformed their email

    public AppUser(String firstName, String lastName, String email, String password, AppUserRole appUserRole) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.appUserRole = appUserRole;
//        this.locked = locked;
//        this.enabled = enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(appUserRole.name()); // this will determine is the user that we are passing has the authority
        return Collections.singletonList(authority); // returning a collection
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getPassword() {
        return password;
    }


    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
