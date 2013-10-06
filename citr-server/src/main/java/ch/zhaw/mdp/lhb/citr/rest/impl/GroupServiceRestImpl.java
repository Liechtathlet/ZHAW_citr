/**
 * 
 */
package ch.zhaw.mdp.lhb.citr.rest.impl;

import java.util.List;

import ch.zhaw.lhb.citr.dto.GroupDTO;
import ch.zhaw.mdp.lhb.citr.jpa.service.IDBGroupService;
import ch.zhaw.mdp.lhb.citr.rest.IRGroupServices;

/**
 * @author Daniel Brun
 *
 * Implementation of the Service-Interface {@link IRGroupServices}.
 */
public class GroupServiceRestImpl implements IRGroupServices {

	private IDBGroupService groupService;
	
	@Override
	public List<GroupDTO> getAllGroups() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean createGroup(GroupDTO aGroup) {
		// TODO Auto-generated method stub
		return false;
	}


}
