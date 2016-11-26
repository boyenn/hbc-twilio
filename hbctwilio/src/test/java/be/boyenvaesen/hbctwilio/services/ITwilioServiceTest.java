package be.boyenvaesen.hbctwilio.services;

import com.twilio.rest.api.v2010.account.Message;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ITwilioServiceTest {
    @Autowired
    TwilioService twilioService;

    @Test
    @Ignore
    public void sendMessage() {
        Message m = twilioService.sendTestMessage();
        m.getSid();
        assertThat(m.getStatus()).isEqualTo(Message.Status.SENT);

    }

    @Test
    public void checkGetMessages() {
        twilioService.getReceivedMessages().forEach(message -> System.out.println("message.getBody() = " + message.getBody()));

    }

    @Test
    public void checkUnhandledImages() {
        twilioService.getUnhandledMessages().forEach(message -> System.out.println("message.getBody() = " + message.getBody()));
        twilioService.getUnhandledMessages().forEach(message -> twilioService.saveMessageToDatabase(message));
        assertThat(twilioService.getUnhandledMessages()).isEmpty();
    }

    @Test
    public void checkHandledImages() {
        twilioService.getHandledMessages().forEach(message -> System.out.println("message.getBody() = " + message.getBody()));
    }


}