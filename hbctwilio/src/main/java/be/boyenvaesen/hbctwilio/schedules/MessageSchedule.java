package be.boyenvaesen.hbctwilio.schedules;


import com.twilio.rest.api.v2010.account.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import be.boyenvaesen.hbctwilio.handlers.MessageHandler;
import be.boyenvaesen.hbctwilio.helpers.RegistrationMessage;
import be.boyenvaesen.hbctwilio.services.TwilioService;

@Component
public class MessageSchedule {
    @Autowired
    TwilioService twilioService;

    /*TODO : After deployment, switch from manually checking for new messages to defining a rest EndPoint (webhook)
      TODO: so that twilio can notify us of new messages instead of checking ourselves.
    *
    */
    @Scheduled(fixedRate = 1000L)
    public void handleNewMessages() {
        twilioService.getUnhandledMessages().forEach(this::handleMessage);

    }

    private void handleMessage(Message message) {
        MessageHandler messageHandler = new MessageHandler(message.getSid(), message.getBody());
        if (messageHandler.isRegistrationMessage()) {
            RegistrationMessage registrationMessage = messageHandler.getRegistrationMessage();
            System.out.println();
        }
    }
}
