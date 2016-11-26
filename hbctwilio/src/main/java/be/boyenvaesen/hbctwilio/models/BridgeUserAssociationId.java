package be.boyenvaesen.hbctwilio.models;

import java.io.Serializable;

/**
 * Created by Boyen on 26/11/2016.
 */
public class BridgeUserAssociationId implements Serializable {

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
