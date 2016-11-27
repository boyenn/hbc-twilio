package be.boyenvaesen.hbctwilio.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Created by Boyen on 26/11/2016.
 */
@Entity
@Table(name = "BRIDGE_USER")
@IdClass(BridgeUserAssociationId.class)
public class BridgeUserAssociation {
    @Id
    private long userid;
    @Id
    private long bridgedateid;
    @Column(name = "isComing")
    private boolean isComing;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private User user;

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "BRIDGE_DATE_ID", referencedColumnName = "ID")
    private BridgeDate bridgeDate;


    public BridgeUserAssociation() {
    }

    public BridgeUserAssociation(BridgeDate bridgeDate, User user, boolean isComing) {
        this.user = user;
        this.userid = user.getId();
        this.bridgeDate = bridgeDate;
        this.bridgedateid = bridgeDate.getId();
        this.isComing = isComing;
    }



    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public long getBridgedateid() {
        return bridgedateid;
    }

    public void setBridgedateid(long bridgedateid) {
        this.bridgedateid = bridgedateid;
    }

    public boolean isComing() {
        return isComing;
    }

    public void setComing(boolean coming) {
        isComing = coming;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BridgeDate getBridgeDate() {
        return bridgeDate;
    }

    public void setBridgeDate(BridgeDate bridgeDate) {
        this.bridgeDate = bridgeDate;
    }
}


