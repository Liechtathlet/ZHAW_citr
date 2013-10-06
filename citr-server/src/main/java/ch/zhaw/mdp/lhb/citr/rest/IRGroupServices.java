/**
 * 
 */
package ch.zhaw.mdp.lhb.citr.rest;

import java.util.List;

import ch.zhaw.lhb.citr.dto.GroupDTO;

/**
 * @author Daniel Brun
 *
 * Interface for the 'Group' Rest-Services.
 */
public interface IRGroupServices {

	/**
	 * Gets a list with all available groups.
	 * 
	 * @return a list with all groups.
	 */
	public List<GroupDTO> getAllGroups();
	

	/**
	 * Creates a new group.'Group'.
	 * 
	 * @param aGroup the group to create.
	 * @return true if the group could be created successfully.
	 */
	public boolean createGroup(GroupDTO aGroup);
}
