package be.boyenvaesen.hbctwilio.services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class TwilioService implements ITwilioService {
    // Find your Account Sid and Token at twilio.com/user/account

    @Value("${twilio.sid}")
    private String ACCOUNT_SID;

    @Value("${twilio.auth}")
    private String AUTH_TOKEN;


    @Value("${twilio.sender}")
    private String SENDER_PHONE_NUMBER;


    public Message sendMessage() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        return Message.creator(new PhoneNumber(SENDER_PHONE_NUMBER),
                new PhoneNumber("+32460200728"),
                "Test twilio SMS").create();
    }
}
