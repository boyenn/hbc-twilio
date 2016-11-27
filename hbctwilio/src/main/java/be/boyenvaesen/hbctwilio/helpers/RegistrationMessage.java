package be.boyenvaesen.hbctwilio.helpers;

import java.time.LocalDate;

/**
 * Created by Boyen on 26/11/2016.
 */
public class RegistrationMessage {
    private String firstName;
    private String lastName;
    private LocalDate date;
    private ComingEnum coming;

    public RegistrationMessage(String firstName, String lastName, LocalDate date, ComingEnum coming) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.date = date;
        this.coming = coming;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public ComingEnum getComing() {
        return coming;
    }

    public void setComing(ComingEnum coming) {
        this.coming = coming;
    }
}
