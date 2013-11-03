package ch.zhaw.mdp.lhb.citr.dto;

/**
 * @author Daniel Brun
 *
 * DTO-Class for 'User'.
 */
public class UserDTO {
	
	private String username;
	private String openId;
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param aUsername the username to set
	 */
	public void setUsername(String aUsername) {
		username = aUsername;
	}
	/**
	 * @return the openId
	 */
	public String getOpenId() {
		return openId;
	}
	/**
	 * @param aOpenId the openId to set
	 */
	public void setOpenId(String aOpenId) {
		openId = aOpenId;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int hashCode = 17;
		
		hashCode += 37 * hashCode + username.hashCode();
		hashCode += 37 * hashCode + openId.hashCode();
		
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
		
		if(anObj == null || !(anObj instanceof UserDTO)){
			return false;
		}

		UserDTO user = (UserDTO) anObj;
		
		return((username == null ? user.getUsername() == null : username.equals(user.getUsername())) &&
				(openId == null ? user.getOpenId() == null : openId.equals(user.getOpenId())));
	}
}
