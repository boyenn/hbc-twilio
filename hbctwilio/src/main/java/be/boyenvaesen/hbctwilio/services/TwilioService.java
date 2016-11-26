package be.boyenvaesen.hbctwilio.services;

import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import be.boyenvaesen.hbctwilio.models.DatabaseMessage;
import be.boyenvaesen.hbctwilio.repositories.MessageDatabaseRepository;


@Service
public class TwilioService implements ITwilioService {
    // Find your Account Sid and Token at twilio.com/user/account

    @Autowired
    MessageDatabaseRepository messageDatabaseRepository;
    @Value("${twilio.sid}")
    private String ACCOUNT_SID;
    @Value("${twilio.auth}")
    private String AUTH_TOKEN;
    @Value("${twilio.sender}")
    private String SENDER_PHONE_NUMBER;
    @Value("${twilio.testreciever}")
    private String RECIEVER_PHONE_NUMBER;


    public TwilioService() {


    }

    public Message sendTestMessage() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        return Message.creator(new PhoneNumber(RECIEVER_PHONE_NUMBER),
                new PhoneNumber(SENDER_PHONE_NUMBER),
                "To correct user").create();
    }

    public ResourceSet<Message> getReceivedMessages() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        return Message.reader().setTo(new PhoneNumber(SENDER_PHONE_NUMBER)).read();
    }

    public List<Message> getUnhandledMessages() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        ResourceSet<Message> twilioMessages = Message.reader().setTo(new PhoneNumber(SENDER_PHONE_NUMBER))
                .read();
        List<String> handledSids = messageDatabaseRepository.findAll().stream().map(c -> c.getSid()).collect(Collectors.toList());

        List<Message> unhandledMessages = StreamSupport.stream(twilioMessages.spliterator(), false).filter(c -> !handledSids.contains(c.getSid())
        ).collect(Collectors.toList());

        return unhandledMessages;
    }

    public List<DatabaseMessage> getHandledMessages() {

        return messageDatabaseRepository.findAll();
    }


    public DatabaseMessage messageToDatabaseMessage(Message message) {
        DatabaseMessage databaseMessage = new DatabaseMessage(
                message.getAccountSid(),
                message.getApiVersion(),
                message.getBody(),
                message.getDateCreated().toGregorianCalendar().toZonedDateTime(),
                message.getDateUpdated().toGregorianCalendar().toZonedDateTime(),
                message.getDateSent().toGregorianCalendar().toZonedDateTime(),
                message.getErrorCode(),
                message.getErrorMessage(),
                message.getFrom().toString(),
                message.getSid(),
                message.getTo()
        );
        return databaseMessage;
    }

    public DatabaseMessage saveMessageToDatabase(Message message) {
        return messageDatabaseRepository.save(messageToDatabaseMessage(message));
    }


}
