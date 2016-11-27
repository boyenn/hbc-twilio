package be.boyenvaesen.hbctwilio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

import be.boyenvaesen.hbctwilio.models.BridgeDate;

/**
 * Created by Boyen on 26/11/2016.
 */
@Repository
public interface BridgeDateRepository extends JpaRepository<BridgeDate, Long> {
    BridgeDate findBridgeDateByBridgeDate(LocalDate bridgeDate);
}
