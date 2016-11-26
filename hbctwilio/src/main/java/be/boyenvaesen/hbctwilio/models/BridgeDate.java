package be.boyenvaesen.hbctwilio.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Created by Boyen on 26/11/2016.
 */
@Entity
public class BridgeDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;
    private LocalDate bridgeDate;
    @OneToMany(mappedBy = "bridgeDate", fetch = FetchType.EAGER)
    private List<BridgeUserAssociation> userAssociations = new ArrayList<>();

    public BridgeDate() {

    }

    public BridgeDate(LocalDate bridgeDate) {

        this.bridgeDate = bridgeDate;
    }

    public void addUser(User user, boolean isComing) {


    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getBridgeDate() {
        return bridgeDate;
    }

    public void setBridgeDate(LocalDate bridgeDate) {
        this.bridgeDate = bridgeDate;
    }

    public List<BridgeUserAssociation> getUserAssociations() {
        return userAssociations;
    }

    public void setUserAssociations(List<BridgeUserAssociation> userAssociations) {
        this.userAssociations = userAssociations;
    }
}
