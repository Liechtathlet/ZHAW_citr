/**
 * 
 */
package ch.zhaw.mdp.lhb.citr.rest.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ch.zhaw.mdp.lhb.citr.jpa.entity.GroupDVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import ch.zhaw.mdp.lhb.citr.dto.GroupDTO;
import ch.zhaw.mdp.lhb.citr.jpa.service.IDBGroupService;
import ch.zhaw.mdp.lhb.citr.rest.IRGroupServices;

/**
 * @author Daniel Brun
 *
 * Implementation of the Service-Interface {@link IRGroupServices}.
 */
@Component
@Path("/group")
@Scope("request")
public class GroupServiceRestImpl implements IRGroupServices {

	@Autowired
	private IDBGroupService groupService;

	/* (non-Javadoc)
	 * @see ch.zhaw.mdp.lhb.citr.rest.IRGroupServices#createGroup(ch.zhaw.mdp.lhb.citr.dto.GroupDTO)
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Secured("ROLE_USER")
	@Path("/create")
	public boolean createGroup(GroupDTO aGroup) {
		GroupDVO group = new GroupDVO();
		group.setName(aGroup.getName());
		groupService.create(group);
		return true;
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.mdp.lhb.citr.rest.IRGroupServices#getAllGroups()
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Secured("ROLE_USER")
	@Path("/list")
	public List<GroupDTO> getAllGroups() {
		List<GroupDTO> groups = new ArrayList<GroupDTO>();
		for (GroupDVO dvo : groupService.getAll()) {
			GroupDTO dto = new GroupDTO();
			dto.setName(dvo.getName());
			dto.setPublicGroup(dvo.getMode() == "public");
			groups.add(dto);
		}
/*
		GroupDTO grp = new GroupDTO();
		grp.setHashId("Test");
		grp.setName("Test");
		groups.add(grp);
*/
		return groups;
	}


}
