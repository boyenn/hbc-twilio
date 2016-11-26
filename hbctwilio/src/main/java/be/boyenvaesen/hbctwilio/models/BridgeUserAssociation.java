package be.boyenvaesen.hbctwilio.models;

import java.io.Serializable;

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


class BridgeUserAssociationId implements Serializable {

    private long userid;

    private long bridgedateid;

    public int hashCode() {
        return (int) (userid + bridgedateid);
    }

    public boolean equals(Object object) {
        if (object instanceof BridgeUserAssociationId) {
            BridgeUserAssociationId otherId = (BridgeUserAssociationId) object;
            return (otherId.userid == this.userid) && (otherId.bridgedateid == this.bridgedateid);
        }
        return false;
    }

    @Override
    public String toString() {
        return "BridgeUserAssociationId{" +
                "userid=" + userid +
                ", bridgedateid=" + bridgedateid +
                '}';
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
}