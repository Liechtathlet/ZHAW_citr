/**
 * 
 */
package ch.zhaw.mdp.lhb.citr.rest.impl;

import java.util.List;

import javax.ws.rs.Path;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ch.zhaw.mdp.lhb.citr.dto.GroupDTO;
import ch.zhaw.mdp.lhb.citr.jpa.service.IDBGroupService;
import ch.zhaw.mdp.lhb.citr.rest.IRGroupServices;

/**
 * @author Daniel Brun
 *
 * Implementation of the Service-Interface {@link IRGroupServices}.
 */

public class GroupServiceRestImpl implements IRGroupServices {

	private IDBGroupService groupService;

	/* (non-Javadoc)
	 * @see ch.zhaw.mdp.lhb.citr.rest.IRGroupServices#createGroup(ch.zhaw.mdp.lhb.citr.dto.GroupDTO)
	 */
	@Override
	public boolean createGroup(GroupDTO aArg0) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.mdp.lhb.citr.rest.IRGroupServices#getAllGroups()
	 */
	@Override
	public List<GroupDTO> getAllGroups() {
		// TODO Auto-generated method stub
		return null;
	}


}
