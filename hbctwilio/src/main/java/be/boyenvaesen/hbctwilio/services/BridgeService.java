package be.boyenvaesen.hbctwilio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import be.boyenvaesen.hbctwilio.models.BridgeDate;
import be.boyenvaesen.hbctwilio.repositories.BridgeDateRepository;

/**
 * Created by Boyen on 27/11/2016.
 */
@Service
public class BridgeService {
    @Autowired
    BridgeDateRepository bridgeDateRepository;

    public BridgeDate getBridgeDateByDate(LocalDate bridgeDate) {
        return bridgeDateRepository.findBridgeDateByBridgeDate(bridgeDate);
    }
}
