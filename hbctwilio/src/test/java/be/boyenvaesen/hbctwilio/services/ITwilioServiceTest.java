package be.boyenvaesen.hbctwilio.services;

import com.twilio.rest.api.v2010.account.Message;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.jodatime.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ITwilioServiceTest {
    @Autowired
    TwilioService twilioService;

    @Test
    public void sendMessage() {
        Message m = twilioService.sendMessage();
        m.getSid();
        assertThat(m.getDateSent()).isEqualToIgnoringSeconds(new DateTime());

    }
}