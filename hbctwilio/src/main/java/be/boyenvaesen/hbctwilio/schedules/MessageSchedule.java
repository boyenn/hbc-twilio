package be.boyenvaesen.hbctwilio.schedules;


import org.springframework.scheduling.annotation.Scheduled;

public class MessageSchedule {

    //TODO : After deployment, switch from manually checking for new messages to defining a rest EndPoint (webhook) so that twilio can notify us of new messages.
    @Scheduled(fixedRate = 1000L)
    public static void handleNewMessages() {


    }
}
