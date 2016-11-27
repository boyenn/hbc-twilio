package be.boyenvaesen.hbctwilio.services;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import be.boyenvaesen.hbctwilio.models.BridgeDate;
import be.boyenvaesen.hbctwilio.repositories.BridgeDateRepository;

/**
 * Created by Boyen on 27/11/2016.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class BridgeServiceTest {
    @Autowired
    BridgeDateRepository bridgeDateRepository;

    @Before
    public void setUp() throws Exception {
        bridgeDateRepository.deleteAll();
        bridgeDateRepository.save(new BridgeDate(LocalDate.now()));
    }

    @After
    public void tearDown() throws Exception {
        bridgeDateRepository.deleteAll();
    }

    @Test
    public void getBridgeDateByDate() throws Exception {
        Assertions.assertThat(bridgeDateRepository.findBridgeDateByBridgeDate(LocalDate.now())).isNotNull();
        Assertions.assertThat((
                bridgeDateRepository.findBridgeDateByBridgeDate(LocalDate.now()).getBridgeDate()))
                .isToday();

    }

}