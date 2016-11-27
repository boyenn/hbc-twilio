package be.boyenvaesen.hbctwilio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.boyenvaesen.hbctwilio.exceptions.AssociationException;
import be.boyenvaesen.hbctwilio.helpers.RegistrationMessage;
import be.boyenvaesen.hbctwilio.models.BridgeDate;
import be.boyenvaesen.hbctwilio.models.BridgeUserAssociation;
import be.boyenvaesen.hbctwilio.models.User;
import be.boyenvaesen.hbctwilio.repositories.BridgeUserAssociationRepository;


@Service
public class AssociationService {
    @Autowired
    private BridgeService bridgeService;
    @Autowired
    private BridgeUserAssociationRepository bridgeUserAssociationRepository;
    @Autowired
    private UserService userService;

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

        }

    }


}
