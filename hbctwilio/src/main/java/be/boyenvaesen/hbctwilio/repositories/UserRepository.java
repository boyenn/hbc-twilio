package be.boyenvaesen.hbctwilio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import be.boyenvaesen.hbctwilio.models.User;

/**
 * Created by Boyen on 26/11/2016.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByFirstNameAndLastName(String firstName, String lastName);
}
