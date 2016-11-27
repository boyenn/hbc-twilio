package be.boyenvaesen.hbctwilio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.boyenvaesen.hbctwilio.exceptions.AssociationException;
import be.boyenvaesen.hbctwilio.helpers.RegistrationMessage;
import be.boyenvaesen.hbctwilio.models.BridgeDate;
import be.boyenvaesen.hbctwilio.models.BridgeUserAssociation;
import be.boyenvaesen.hbctwilio.models.User;
import be.boyenvaesen.hbctwilio.repositories.BridgeUserAssociationRepository;

/**
 * Created by Boyen on 27/11/2016.
 */
@Service
public class AssociationService {
    @Autowired
    BridgeService bridgeService;
    @Autowired
    private TwilioService twilioService;
    @Autowired
    private BridgeUserAssociationRepository bridgeUserAssociationRepository;
    @Autowired
    private UserService userService;

    public void addAssociationFromMessage(RegistrationMessage registrationMessage)
            throws AssociationException {

        User userByName = userService.getUserByName(registrationMessage.getFirstName(), registrationMessage.getLastName());
        if (userByName == null) throw new AssociationException("User not found");
        BridgeDate bridgeDate = bridgeService.getBridgeDateByDate(registrationMessage.getDate());
        if (bridgeDate == null) throw new AssociationException("Bridgedate not found");
        if (userByName.getBridgeAssociations().stream().noneMatch(
                c -> c.getBridgeDate().getBridgeDate() == bridgeDate.getBridgeDate())) {
            BridgeUserAssociation bridgeUserAssociation = new BridgeUserAssociation(
                    bridgeDate,
                    userByName,
                    registrationMessage.isComing()
            );
            userByName.getBridgeAssociations().add(bridgeUserAssociation);
            bridgeDate.getUserAssociations().add(bridgeUserAssociation);
            bridgeUserAssociationRepository.save(bridgeUserAssociation);
        } else {
            BridgeUserAssociation bridgeUserAssociation = userByName.getBridgeAssociations().stream().filter(
                    c -> c.getBridgeDate().getBridgeDate() == bridgeDate.getBridgeDate()
            ).findFirst().get();
            bridgeUserAssociation.setComing(registrationMessage.isComing());

        }

    }


}
