package be.boyenvaesen.hbctwilio.services;

import com.twilio.rest.api.v2010.account.Message;

import org.springframework.stereotype.Service;


@Service
public interface ITwilioService {
     Message sendTestMessage();
}
