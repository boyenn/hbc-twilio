package be.boyenvaesen.hbctwilio.models;

import org.hibernate.validator.constraints.Email;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="bridgeuser")
public class User {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Email
    private String emailAdress;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private boolean isDefault;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<BridgeUserAssociation> bridgeAssociations = new ArrayList<>();

    public User(String emailAdress, String phoneNumber, String firstName, String lastName,boolean isDefault) {
        this.emailAdress = emailAdress;
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isDefault = isDefault;
    }

    public User() {

    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<BridgeUserAssociation> getBridgeAssociations() {
        return bridgeAssociations;
    }

    public void setBridgeAssociations(List<BridgeUserAssociation> bridgeAssociations) {
        this.bridgeAssociations = bridgeAssociations;
    }

    public String getEmailAdress() {
        return emailAdress;
    }

    public void setEmailAdress(String emailAdress) {
        this.emailAdress = emailAdress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }
}
