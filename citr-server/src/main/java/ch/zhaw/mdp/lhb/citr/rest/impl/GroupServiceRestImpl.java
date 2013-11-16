/**
 * 
 */
package ch.zhaw.mdp.lhb.citr.rest.impl;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import ch.zhaw.mdp.lhb.citr.dto.GroupDTO;
import ch.zhaw.mdp.lhb.citr.dto.GroupFactory;
import ch.zhaw.mdp.lhb.citr.jpa.entity.GroupDVO;
import ch.zhaw.mdp.lhb.citr.jpa.service.IDBGroupService;
import ch.zhaw.mdp.lhb.citr.jpa.service.IDBUserService;
import ch.zhaw.mdp.lhb.citr.response.ResponseObject;
import ch.zhaw.mdp.lhb.citr.rest.IRGroupServices;

/**
 * @author Daniel Brun
 *
 * Implementation of the Service-Interface {@link IRGroupServices}.
 */
@Component
@Path("/group")
@Scope("singleton")
public class GroupServiceRestImpl implements IRGroupServices {

	private static final Logger LOG = LoggerFactory
			.getLogger(GroupServiceRestImpl.class);

	@Autowired
	private IDBGroupService groupService;
	
	@Autowired
	private IDBUserService userService;
	
	@Autowired
	private ReloadableResourceBundleMessageSource messageSource;
	

	/* (non-Javadoc)
	 * @see ch.zhaw.mdp.lhb.citr.rest.IRGroupServices#createGroup(ch.zhaw.mdp.lhb.citr.dto.GroupDTO)
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Secured("ROLE_USER")
	@Path("/create")
	public ResponseObject<Boolean> createGroup(GroupDTO aGroup) {
		if(aGroup == null){
			throw new IllegalArgumentException("The argument aGroup must not be null!");
		}
		
		Boolean result = Boolean.FALSE;
		String msg = "";
		
		GroupDVO group = new GroupDVO();
		group.setName(aGroup.getName());
		groupService.create(group);
		
		//TODO: Validate group & set group owner
		
		//TODO: Implement different texts
		
		msg = messageSource.getMessage("msg.group.create.succ", new String[]{group.getName()}, null);
		result = Boolean.TRUE;
		
		return new ResponseObject<Boolean>(result, result.booleanValue(), msg);
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.mdp.lhb.citr.rest.IRGroupServices#getAllGroups()
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Secured("ROLE_USER")
	@Path("/list")
	public ResponseObject<List<GroupDTO>> getAllGroups() {
		List<GroupDTO> groups = GroupFactory.createGroups(groupService.getAll());
		return new ResponseObject<List<GroupDTO>>(groups, true, null);
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.mdp.lhb.citr.rest.IRGroupServices#createRequestForGroupSubscription(int)
	 */
	@Override
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Secured("ROLE_USER")
	@Path("{groupId}/requestSubscription")
	public ResponseObject<Boolean> createRequestForGroupSubscription(@PathParam("groupId") int aGroupId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.mdp.lhb.citr.rest.IRGroupServices#getGroupSubscriptions()
	 */
	@Override
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Secured("ROLE_USER")
	@Path("/listSubscriptions")
	public ResponseObject<List<GroupDTO>> getGroupSubscriptions() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.mdp.lhb.citr.rest.IRGroupServices#searchGroup(ch.zhaw.mdp.lhb.citr.dto.GroupDTO)
	 */
	@Override
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Secured("ROLE_USER")
	@Path("/search")
	public ResponseObject<List<GroupDTO>> searchGroup(GroupDTO aGroup) {
		// TODO Auto-generated method stub
		return null;
	}


}
