package ch.zhaw.mdp.lhb.citr.dto;

import ch.zhaw.mdp.lhb.citr.enumeration.GroupStateEnum;

/**
 * @author Daniel Brun
 * @author Simon Lang
 * 
 * DTO-Class for 'Group'.
 */
public class GroupDTO {

    private int id;

    private String name;
    private GroupStateEnum state;

    private boolean publicGroup;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param aId the id to set
     */
    public void setId(int aId) {
        id = aId;
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

    /**
     * @return the state
     */
    public GroupStateEnum getState() {
        return state;
    }

    /**
     * @param aState the state to set
     */
    public void setState(GroupStateEnum aState) {
        state = aState;
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
	hashCode += 37 * hashCode + id;
	hashCode += 37 * hashCode + state.hashCode();
	hashCode += 37 * hashCode + (publicGroup ? 1 : 0);

	return hashCode;
    }

    @Override
    public boolean equals(Object anObj) {
	if (anObj == this) {
	    return true;
	}

	if (anObj == null || !(anObj instanceof GroupDTO)) {
	    return false;
	}

	GroupDTO group = (GroupDTO) anObj;

	return ((name == null ? group.name == null : name.equals(group
		.name)
		&& (state == null ? group.state == null : state
			.equals(group.state)) 
		&& (publicGroup == group
		    .publicGroup)));
    }
}
