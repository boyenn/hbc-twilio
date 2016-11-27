package be.boyenvaesen.hbctwilio.schedules;


import com.twilio.rest.api.v2010.account.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import be.boyenvaesen.hbctwilio.exceptions.AssociationException;
import be.boyenvaesen.hbctwilio.handlers.MessageHandler;
import be.boyenvaesen.hbctwilio.helpers.RegistrationMessage;
import be.boyenvaesen.hbctwilio.models.BridgeDate;
import be.boyenvaesen.hbctwilio.repositories.BridgeDateRepository;
import be.boyenvaesen.hbctwilio.services.AssociationService;
import be.boyenvaesen.hbctwilio.services.TwilioService;

@Component
public class MessageSchedule {
    @Autowired
    TwilioService twilioService;
    @Autowired
    BridgeDateRepository bridgeDateRepository;
    @Autowired
    AssociationService associationService;

    /*TODO : After deployment, switch from manually checking for new messages to defining a rest EndPoint (webhook)
      TODO: so that twilio can notify us of new messages instead of checking ourselves.
    *
    */

    public void handleNewMessages() {

        twilioService.getUnhandledMessages().forEach(c -> System.out.println(c.getSid()));
        twilioService.getUnhandledMessages().forEach(this::handleMessage);
    }

    @Scheduled(cron = "0 22 * * * ?")
    public void checkForNewDates() {
        LocalDate localDate = LocalDate.now();
        LocalDate endDate = localDate.plusYears(1);
        List<LocalDate> thursdays = new ArrayList<>();
        boolean reachedAThursday = false;
        while (localDate.isBefore(endDate)) {
            if (localDate.getDayOfWeek() == DayOfWeek.THURSDAY) {
                thursdays.add(localDate);
                reachedAThursday = true;
            }
            if (reachedAThursday) {
                localDate = localDate.plusWeeks(1);
            } else {
                localDate = localDate.plusDays(1);
            }
        }

        for (LocalDate th : thursdays) {
            if (bridgeDateRepository.findBridgeDateByBridgeDate(th) == null) {
                System.out.println("saving new Date : " + th);
                bridgeDateRepository.save(new BridgeDate(th));
            }
        }

    }

    private void handleMessage(Message message) {
        MessageHandler messageHandler = new MessageHandler(message.getSid(), message.getBody());
        if (messageHandler.isRegistrationMessage()) {
            RegistrationMessage registrationMessage = messageHandler.getRegistrationMessage();
            try {
                associationService.addAssociationFromMessage(registrationMessage);
                System.out.println("Successfully added an association message :O :O :O ");
            } catch (AssociationException ex) {

                System.out.println(ex.getMessage() + " : " + message.getBody());
            }


        }
        twilioService.saveMessageToDatabase(message);
    }
}
