package ch.zhaw.mdp.lhb.citr.dto;

/**
 * @author Daniel Brun
 * @author Simon Lang
 *
 * DTO-Class for 'Group'.
 */
public class GroupDTO {
	
	private String name;
	private State state;
	private String hashId;
	private boolean publicGroup;

	public enum State {
		approved,
		open,
		none,
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param aName the name to set
	 */
	public void setName(String aName) {
		name = aName;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	/**
	 * @return the hashId
	 */
	public String getHashId() {
		return hashId;
	}
	/**
	 * @param aHashId the hashId to set
	 */
	public void setHashId(String aHashId) {
		hashId = aHashId;
	}
	/**
	 * @return the publicGroup
	 */
	public boolean isPublicGroup() {
		return publicGroup;
	}
	/**
	 * @param aPublicGroup the publicGroup to set
	 */
	public void setPublicGroup(boolean aPublicGroup) {
		publicGroup = aPublicGroup;
	}
	
	@Override
	public int hashCode() {
		int hashCode = 17;
		
		hashCode += 37 * hashCode + name.hashCode();
		hashCode += 37 * hashCode + hashId.hashCode();
		hashCode += 37 * hashCode + (publicGroup ? 1 : 0);
		
		return hashCode;
	}
	
	@Override
	public boolean equals(Object anObj) {
		if(anObj == this){
			return true;
		}
		
		if(anObj == null || !(anObj instanceof GroupDTO)){
			return false;
		}

		GroupDTO group = (GroupDTO) anObj;
		
		return((name == null ? group.getName() == null : name.equals(group.getName())) &&
				(hashId == null ? group.getHashId() == null : hashId.equals(group.getHashId())) &&
				(publicGroup == group.isPublicGroup()));
	}
}
