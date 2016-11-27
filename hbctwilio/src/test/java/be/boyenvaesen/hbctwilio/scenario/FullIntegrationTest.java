package be.boyenvaesen.hbctwilio.scenario;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import be.boyenvaesen.hbctwilio.models.User;
import be.boyenvaesen.hbctwilio.repositories.BridgeDateRepository;
import be.boyenvaesen.hbctwilio.repositories.UserRepository;
import be.boyenvaesen.hbctwilio.schedules.MessageSchedule;
import be.boyenvaesen.hbctwilio.services.TwilioService;

/**
 * Created by Boyen on 27/11/2016.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class FullIntegrationTest {
    @Autowired
    MessageSchedule messageSchedule;

    @Autowired
    UserRepository userRepository;
    @Autowired
    BridgeDateRepository bridgeDateRepository;
    @Autowired
    TwilioService twilioService;

    @Before
    public void setUp() {
        messageSchedule.checkForNewDates();
        userRepository.save(new User("boyenvaesen@hotmail.com", "+32468252393", "Boyen", "Vaesen"));
    }

    @Test
    public void checkNewMessage() {
        //bridgeDateRepository.save(new BridgeDate(LocalDate.of(2016,12,01)));
        messageSchedule.handleNewMessages();
        Assertions.assertThat(twilioService.getUnhandledMessages()).isEmpty();
    }
}
