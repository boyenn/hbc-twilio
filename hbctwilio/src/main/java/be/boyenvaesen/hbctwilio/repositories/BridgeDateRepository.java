package be.boyenvaesen.hbctwilio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import be.boyenvaesen.hbctwilio.models.BridgeDate;

/**
 * Created by Boyen on 26/11/2016.
 */
public interface BridgeDateRepository extends JpaRepository<BridgeDate, Long> {

}
