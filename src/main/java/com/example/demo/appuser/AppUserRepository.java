/** This interface will act as a place to get users' information to user against the Service
 * */

package com.example.demo.appuser;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface AppUserRepository {

    Optional<AppUser> findByEmail(String email); // find user by their email in the DB

}