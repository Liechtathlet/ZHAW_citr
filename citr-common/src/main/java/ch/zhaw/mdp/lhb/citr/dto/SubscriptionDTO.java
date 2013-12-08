package ch.zhaw.mdp.lhb.citr.dto;

/**
 * @author Daniel Brun
 *
 * DTO-Class for 'User'.
 */
public class SubscriptionDTO {
	
	private UserDTO user;
	private GroupDTO group;
	private SubscriptionDTO.State state;

	public SubscriptionDTO.State getState() {
		return state;
	}

	public void setState(SubscriptionDTO.State state) {
		this.state = state;
	}

	public GroupDTO getGroup() {
		return group;
	}

	public void setGroup(GroupDTO group) {
		this.group = group;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public enum State {
		approved,
		open,
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int hashCode = 17;
		
		hashCode += 37 * hashCode + user.getOpenId().hashCode();
		hashCode += 37 * hashCode + group.getHashId().hashCode();
		hashCode += 37 * hashCode + state.ordinal();

		return hashCode;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object anObj) {
		if(anObj == this){
			return true;
		}
		
		if(anObj == null || !(anObj instanceof SubscriptionDTO)){
			return false;
		}

		SubscriptionDTO subscription = (SubscriptionDTO) anObj;
		
		return((user == null ? subscription.user == null : user.getOpenId().equals(subscription.user.getOpenId())) &&
				(group == null ? subscription.group == null : group.getHashId().equals(subscription.getGroup().getHashId())));
	}
}
