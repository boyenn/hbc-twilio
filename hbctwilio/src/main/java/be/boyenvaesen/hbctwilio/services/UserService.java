package be.boyenvaesen.hbctwilio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import be.boyenvaesen.hbctwilio.models.User;
import be.boyenvaesen.hbctwilio.repositories.UserRepository;

/**
 * Created by Boyen on 26/11/2016.
 */
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public User getUserByName(String firstName, String lastName) {
        return userRepository.findUserByFirstNameIgnoreCaseAndLastNameIgnoreCase(firstName, lastName);

    }

    public List<User> getAll(){
        List<User> target = new ArrayList<>();
         userRepository.findAll().forEach(target::add);
        return target;
    }
}
