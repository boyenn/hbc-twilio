package be.boyenvaesen.hbctwilio.handlers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import be.boyenvaesen.hbctwilio.helpers.ComingEnum;
import be.boyenvaesen.hbctwilio.helpers.RegistrationMessage;

/**
 * Created by Boyen on 26/11/2016.
 */
public class MessageHandler {
    private String messageSid;
    private String messageBody;

    public MessageHandler(String messageSid, String messageBody) {
        this.messageSid = messageSid;
        this.messageBody = messageBody;
    }

    public boolean isRegistrationMessage() {
        if (!messageBody.toLowerCase().startsWith("bridge")) return false;
        String[] deconstructed = messageBody.split(" ");
        if (deconstructed.length != 5) return false;
        try {
            LocalDate.parse(deconstructed[3], DateTimeFormatter.ofPattern("dd/MM/yyyy").withLocale(Locale.US));
        } catch (DateTimeParseException ex) {
            ex.printStackTrace();
            return false;
        }
        return !(!deconstructed[4].toUpperCase().equals("JA") && !deconstructed[4].toUpperCase().equals("NEE"));

    }

    public RegistrationMessage getRegistrationMessage() {

        String[] deconstructed = messageBody.split(" ");
        LocalDate localDate = LocalDate.parse(deconstructed[3], DateTimeFormatter.ofPattern("dd/MM/yyyy").withLocale(Locale.US));
        ComingEnum comingEnum;
        if (deconstructed[4].toUpperCase().equals("JA")) {
            comingEnum = ComingEnum.YES;
        } else {
            comingEnum = ComingEnum.NO;
        }
        return new RegistrationMessage(deconstructed[1], deconstructed[2], localDate, comingEnum);
    }


    public String getMessageSid() {
        return messageSid;
    }

    public void setMessageSid(String messageSid) {
        this.messageSid = messageSid;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }
}
