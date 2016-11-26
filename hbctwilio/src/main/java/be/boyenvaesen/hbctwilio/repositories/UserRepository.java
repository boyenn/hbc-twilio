package be.boyenvaesen.hbctwilio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import be.boyenvaesen.hbctwilio.models.User;

/**
 * Created by Boyen on 26/11/2016.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByFirstNameAndLastName(String firstName, String lastName);
}
