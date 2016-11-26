package be.boyenvaesen.hbctwilio.repositories;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import be.boyenvaesen.hbctwilio.models.User;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Boyen on 26/11/2016.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Before
    public void setUp() throws Exception {

        User user = new User("boyenvaesen@hotmail.com", "+32468252393", "boyen", "vaesen");

        userRepository.save(user);

    }

    @After
    public void tearDown() throws Exception {
        userRepository.deleteAll();
    }

    @Test
    public void findUserByFirstNameAndLastName() throws Exception {
        User userByFirstNameAndLastName = userRepository.findUserByFirstNameAndLastName("boyen", "vaesen");
        assertThat(userByFirstNameAndLastName)
                .extracting("firstName", "lastName").containsOnly("boyen", "vaesen");
        assertThat(userByFirstNameAndLastName.getFirstName()).isEqualToIgnoringCase("boyen");

    }

}