package be.boyenvaesen.hbctwilio.models;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Boyen on 26/11/2016.
 */
@Entity
public class DatabaseMessage {

    private String accountSid;
    private String apiVersion;
    private String body;
    private ZonedDateTime dateCreated;
    private ZonedDateTime dateUpdated;
    private ZonedDateTime dateSent;
    private Integer errorCode;
    private String errorMessage;
    @Column(name = "fromnumber")
    private String from;
    @Id
    private String sid;
    @Column(name = "tonumber")
    private String to;

    public DatabaseMessage(String accountSid, String apiVersion, String body, ZonedDateTime dateCreated, ZonedDateTime dateUpdated, ZonedDateTime dateSent, Integer errorCode, String errorMessage, String from, String sid, String to) {
        this.accountSid = accountSid;
        this.apiVersion = apiVersion;
        this.body = body;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.dateSent = dateSent;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.from = from;
        this.sid = sid;
        this.to = to;
    }

    public DatabaseMessage() {

    }

    public String getAccountSid() {
        return accountSid;
    }

    public void setAccountSid(String accountSid) {
        this.accountSid = accountSid;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public ZonedDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(ZonedDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public ZonedDateTime getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(ZonedDateTime dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public ZonedDateTime getDateSent() {
        return dateSent;
    }

    public void setDateSent(ZonedDateTime dateSent) {
        this.dateSent = dateSent;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
