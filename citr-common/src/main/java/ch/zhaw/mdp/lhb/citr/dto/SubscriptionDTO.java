package ch.zhaw.mdp.lhb.citr.dto;

import ch.zhaw.mdp.lhb.citr.enumeration.SubscriptionStateEnum;

/**
 * @author Daniel Brun
 * 
 *         DTO-Class for 'Subscription'.
 */
public class SubscriptionDTO {

    private UserDTO user;
    private GroupDTO group;
    private SubscriptionStateEnum state;

    /**
     * @return the user
     */
    public UserDTO getUser() {
	return user;
    }

    /**
     * @param aUser the user to set
     */
    public void setUser(UserDTO aUser) {
	user = aUser;
    }

    /**
     * @return the group
     */
    public GroupDTO getGroup() {
	return group;
    }

    /**
     * @param aGroup the group to set
     */
    public void setGroup(GroupDTO aGroup) {
	group = aGroup;
    }

    /**
     * @return the state
     */
    public SubscriptionStateEnum getState() {
	return state;
    }

    /**
     * @param aState the state to set
     */
    public void setState(SubscriptionStateEnum aState) {
	state = aState;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	int hashCode = 17;

	hashCode += 37 * hashCode + user.hashCode();
	hashCode += 37 * hashCode + group.hashCode();
	hashCode += 37 * hashCode + state.hashCode();

	return hashCode;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object anObj) {
	if (anObj == this) {
	    return true;
	}

	if (anObj == null || !(anObj instanceof SubscriptionDTO)) {
	    return false;
	}

	SubscriptionDTO subscription = (SubscriptionDTO) anObj;

	return ((user == null ? subscription.user == null : user
		.equals(subscription.user)
		&& (group == null ? subscription.group == null : group
			.equals(subscription.group))
		&& (state == null ? subscription.state == null : state
			.equals(subscription.state))));
    }
}
