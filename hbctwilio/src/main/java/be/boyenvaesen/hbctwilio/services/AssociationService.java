package be.boyenvaesen.hbctwilio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import be.boyenvaesen.hbctwilio.exceptions.AssociationException;
import be.boyenvaesen.hbctwilio.helpers.ComingEnum;
import be.boyenvaesen.hbctwilio.helpers.RegistrationMessage;
import be.boyenvaesen.hbctwilio.models.BridgeDate;
import be.boyenvaesen.hbctwilio.models.BridgeUserAssociation;
import be.boyenvaesen.hbctwilio.models.User;
import be.boyenvaesen.hbctwilio.repositories.BridgeUserAssociationRepository;

import static be.boyenvaesen.hbctwilio.helpers.ComingEnum.YES;


@Service
public class AssociationService {
    @Value("${hbctwilio.membersneeded}")
    private int membersNeeded;
    @Autowired
    private BridgeService bridgeService;
    @Autowired
    private BridgeUserAssociationRepository bridgeUserAssociationRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private TwilioService twilioService;

    public void addAssociationFromMessage(RegistrationMessage registrationMessage)
            throws AssociationException {

        User userByName = userService.getUserByName(registrationMessage.getFirstName(), registrationMessage.getLastName());
        if (userByName == null)
            throw new AssociationException("User not found(" + registrationMessage.getFirstName()
                    + " " + registrationMessage.getLastName() + ")");

        BridgeDate bridgeDate = bridgeService.getBridgeDateByDate(registrationMessage.getDate());
        if (bridgeDate == null)
            throw new AssociationException("Bridgedate not found(" + registrationMessage.getDate() + ")");
        if (userByName.getBridgeAssociations().stream().noneMatch(
                c -> c.getBridgeDate().getBridgeDate() == bridgeDate.getBridgeDate())) {
            BridgeUserAssociation bridgeUserAssociation = new BridgeUserAssociation(
                    bridgeDate,
                    userByName,
                    registrationMessage.getComing()
            );
            userByName.getBridgeAssociations().add(bridgeUserAssociation);
            bridgeDate.getUserAssociations().add(bridgeUserAssociation);
            bridgeUserAssociationRepository.save(bridgeUserAssociation);
        } else {
            BridgeUserAssociation bridgeUserAssociation = userByName.getBridgeAssociations().stream().filter(
                    c -> c.getBridgeDate().getBridgeDate() == bridgeDate.getBridgeDate()
            ).findFirst().get();
            bridgeUserAssociation.setIsComing(registrationMessage.getComing());
            bridgeUserAssociationRepository.save(bridgeUserAssociation);
        }
        //if (registrationMessage.getComing() == ComingEnum.NO) {
        System.out.println("about to check for required members");
            checkForRequiredMembers(bridgeDate.getBridgeDate());

        //}

    }

    public void checkForRequiredMembers(LocalDate localDate) {
        BridgeDate bridgeDate = bridgeService.getBridgeDateByDate(localDate);
        if (bridgeDate.getUserAssociations().stream().filter(c -> c.getIsComing() == ComingEnum.UNCONFIRMED || c.getIsComing() == YES).count() < membersNeeded) {

            List<User> all = userService.getAll();
            all.forEach(c -> System.out.println(c.getFirstName()));
            all.forEach(c -> c.getBridgeAssociations().forEach(q -> System.out.println("q.getBridgeDate().getBridgeDate()  = " + q.getBridgeDate().getBridgeDate() + " vs " + localDate)));

            all.stream().filter(
                    c -> c.getBridgeAssociations().stream().noneMatch(q -> q.getBridgeDate().getBridgeDate().equals(localDate))).forEach(
                    c -> {

                        System.out.println("I have to send to :" + c.getFirstName() + " " + c.getLastName());
                        twilioService.sendRequestMessage(c,localDate);

                    }

            );

        }

    }


}
