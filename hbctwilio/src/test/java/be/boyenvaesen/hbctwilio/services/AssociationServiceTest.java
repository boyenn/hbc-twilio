package be.boyenvaesen.hbctwilio.services;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import be.boyenvaesen.hbctwilio.exceptions.AssociationException;
import be.boyenvaesen.hbctwilio.helpers.RegistrationMessage;
import be.boyenvaesen.hbctwilio.models.BridgeDate;
import be.boyenvaesen.hbctwilio.models.User;
import be.boyenvaesen.hbctwilio.repositories.BridgeDateRepository;
import be.boyenvaesen.hbctwilio.repositories.BridgeUserAssociationRepository;
import be.boyenvaesen.hbctwilio.repositories.UserRepository;

/**
 * Created by Boyen on 27/11/2016.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class AssociationServiceTest {

    @Autowired
    AssociationService associationService;

    @Autowired
    BridgeUserAssociationRepository bridgeUserAssociationRepository;
    @Autowired
    BridgeDateRepository bridgeDateRepository;
    @Autowired
    UserRepository userRepository;

    @Before
    public void setUp() {

        bridgeUserAssociationRepository.deleteAll();
        bridgeDateRepository.deleteAll();
        userRepository.deleteAll();
    }

    @After
    public void tearDown() {

        bridgeUserAssociationRepository.deleteAll();
        bridgeDateRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test(expected = AssociationException.class)
    public void addAssociationOnNonExistingUser() throws Exception {
        RegistrationMessage registrationMessage = new RegistrationMessage(
                "boyen",
                "vaesen",
                LocalDate.now(), true
        );
        associationService.addAssociationFromMessage(registrationMessage);
        BridgeDate bridgeDate = bridgeDateRepository.findBridgeDateByBridgeDate(LocalDate.now());
        User user = userRepository.findUserByFirstNameIgnoreCaseAndLastNameIgnoreCase("boyen", "vaesen");
        Assertions.assertThat(user.getBridgeAssociations().get(0).getBridgeDate().getBridgeDate()).isEqualTo(bridgeDate.getBridgeDate());


    }

    @Test(expected = AssociationException.class)
    public void addAssociationOnNonExistingBridgeDate() throws Exception {
        userRepository.save(new User("boyenvaesen@hotmail.com", "+32468252393", "Boyen", "Vaesen"));
        RegistrationMessage registrationMessage = new RegistrationMessage(
                "boyen",
                "vaesen",
                LocalDate.now(), true
        );
        associationService.addAssociationFromMessage(registrationMessage);
        BridgeDate bridgeDate = bridgeDateRepository.findBridgeDateByBridgeDate(LocalDate.now());
        User user = userRepository.findUserByFirstNameIgnoreCaseAndLastNameIgnoreCase("boyen", "vaesen");
        Assertions.assertThat(user.getBridgeAssociations().get(0).getBridgeDate().getBridgeDate()).isEqualTo(bridgeDate.getBridgeDate());


    }

    @Test
    public void addAssociation() throws Exception {
        bridgeDateRepository.save(new BridgeDate(LocalDate.now()));
        userRepository.save(new User("boyenvaesen@hotmail.com", "+32468252393", "Boyen", "Vaesen"));
        RegistrationMessage registrationMessage = new RegistrationMessage(
                "boyen",
                "vaesen",
                LocalDate.now(), true
        );
        associationService.addAssociationFromMessage(registrationMessage);
        BridgeDate bridgeDate = bridgeDateRepository.findBridgeDateByBridgeDate(LocalDate.now());
        User user = userRepository.findUserByFirstNameIgnoreCaseAndLastNameIgnoreCase("boyen", "vaesen");
        Assertions.assertThat(user.getBridgeAssociations().get(0).getBridgeDate().getBridgeDate()).isEqualTo(bridgeDate.getBridgeDate());


    }

    @Test
    public void changeAssociation() throws Exception {
        bridgeDateRepository.save(new BridgeDate(LocalDate.now()));
        userRepository.save(new User("boyenvaesen@hotmail.com", "+32468252393", "Boyen", "Vaesen"));
        RegistrationMessage registrationMessage = new RegistrationMessage(
                "boyen",
                "vaesen",
                LocalDate.now(), true
        );
        associationService.addAssociationFromMessage(registrationMessage);
        BridgeDate bridgeDate = bridgeDateRepository.findBridgeDateByBridgeDate(LocalDate.now());
        User user = userRepository.findUserByFirstNameIgnoreCaseAndLastNameIgnoreCase("boyen", "vaesen");
        Assertions.assertThat(user.getBridgeAssociations().get(0).isComing()).isTrue();
        Assertions.assertThat(bridgeDate.getUserAssociations().get(0).getUser().getFirstName()).isEqualToIgnoringCase("boyen");
        registrationMessage = new RegistrationMessage(
                "boyen",
                "vaesen",
                LocalDate.now(), false
        );
        associationService.addAssociationFromMessage(registrationMessage);
        bridgeDate = bridgeDateRepository.findBridgeDateByBridgeDate(LocalDate.now());
        user = userRepository.findUserByFirstNameIgnoreCaseAndLastNameIgnoreCase("boyen", "vaesen");
        Assertions.assertThat(
                user.getBridgeAssociations().get(0).getBridgeDate().getBridgeDate())
                .isEqualTo(bridgeDate.getBridgeDate());

        Assertions.assertThat(user.getBridgeAssociations().get(0).isComing()).isFalse();
        Assertions.assertThat(user.getBridgeAssociations()).hasSize(1);
    }

}