package be.boyenvaesen.hbctwilio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import be.boyenvaesen.hbctwilio.models.BridgeUserAssociation;
import be.boyenvaesen.hbctwilio.models.BridgeUserAssociationId;

/**
 * Created by Boyen on 26/11/2016.
 */
@Repository
public interface BridgeUserAssociationRepository extends JpaRepository<BridgeUserAssociation, BridgeUserAssociationId> {

}
