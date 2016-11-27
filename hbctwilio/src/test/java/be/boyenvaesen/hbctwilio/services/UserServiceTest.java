package be.boyenvaesen.hbctwilio.services;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import be.boyenvaesen.hbctwilio.helpers.ComingEnum;
import be.boyenvaesen.hbctwilio.models.BridgeDate;
import be.boyenvaesen.hbctwilio.models.BridgeUserAssociation;
import be.boyenvaesen.hbctwilio.models.User;
import be.boyenvaesen.hbctwilio.repositories.BridgeDateRepository;
import be.boyenvaesen.hbctwilio.repositories.BridgeUserAssociationRepository;
import be.boyenvaesen.hbctwilio.repositories.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Boyen on 26/11/2016.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {
    @Autowired
    BridgeDateRepository bridgeDateRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BridgeUserAssociationRepository bridgeUserAssociationRepository;

    @Autowired
    UserService userService;

    private User userSetup;

    @Before
    public void setUp() throws Exception {

        bridgeUserAssociationRepository.deleteAll();
        userRepository.deleteAll();
        bridgeDateRepository.deleteAll();
        userSetup = new User("boyenvaesen@hotmail.com", "+32468252393", "boyen", "vaesen");
        BridgeDate bridgeDate = new BridgeDate(LocalDate.now());
        bridgeDateRepository.save(bridgeDate);
        userRepository.save(userSetup);

        BridgeUserAssociation bridgeUserAssociation = new BridgeUserAssociation();
        bridgeUserAssociation.setUser(userSetup);
        bridgeUserAssociation.setUserid(userSetup.getId());
        bridgeUserAssociation.setBridgeDate(bridgeDate);
        bridgeUserAssociation.setBridgedateid(bridgeDate.getId());
        bridgeUserAssociation.setIsComing(ComingEnum.YES);
        bridgeUserAssociationRepository.save(bridgeUserAssociation);
    }

    @After
    public void tearDown() throws Exception {

        bridgeUserAssociationRepository.deleteAll();
        userRepository.deleteAll();
        bridgeDateRepository.deleteAll();
    }

    @Test
    public void getUserByName() throws Exception {
        User user = userService.getUserByName("boyen", "vaesen");
        assertThat(user.getBridgeAssociations()).hasSize(1);
        assertThat(user.getBridgeAssociations().get(0).getBridgeDate().getBridgeDate()).isToday();
    }


}