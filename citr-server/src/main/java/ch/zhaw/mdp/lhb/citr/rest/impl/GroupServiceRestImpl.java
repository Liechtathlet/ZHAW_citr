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

import ch.zhaw.mdp.lhb.citr.dto.*;
import ch.zhaw.mdp.lhb.citr.jpa.entity.MessageDVO;
import ch.zhaw.mdp.lhb.citr.jpa.service.IDBMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import ch.zhaw.mdp.lhb.citr.Logging.LoggingFactory;
import ch.zhaw.mdp.lhb.citr.Logging.LoggingStrategy;
import ch.zhaw.mdp.lhb.citr.jpa.entity.GroupDVO;
import ch.zhaw.mdp.lhb.citr.jpa.entity.SubscriptionDVO;
import ch.zhaw.mdp.lhb.citr.jpa.entity.UserDVO;
import ch.zhaw.mdp.lhb.citr.jpa.service.IDBGroupService;
import ch.zhaw.mdp.lhb.citr.jpa.service.IDBSubscriptionService;
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

	private static final LoggingStrategy LOG = LoggingFactory.get();

	@Autowired
	private IDBGroupService groupService;

	@Autowired
	private IDBUserService userService;

	@Autowired
	private IDBSubscriptionService subscriptionService;

	@Autowired
	private IDBMessageService messageService;
	
	@Autowired
	private ReloadableResourceBundleMessageSource messageSource;
	

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
		UserDVO userDVO = getCurrentUser();
		
		GroupDVO group = new GroupDVO();
		group.setName(aGroup.getName());
		group.setMode(aGroup.isPublicGroup() ? GroupDVO.Mode.PUBLIC : GroupDVO.Mode.PRIVATE);
		group.setOwner(userDVO);
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

	@Override
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Secured("ROLE_USER")
	@Path("{groupId}/requestSubscription")
	public ResponseObject<Boolean> createRequestForGroupSubscription(@PathParam("groupId") int aGroupId) {

		String msg = null;
		Boolean succ = true;

		UserDVO currentUser = getCurrentUser();
		GroupDVO groupDVO = groupService.getById(aGroupId);

		SubscriptionDVO subscriptionDVO = new SubscriptionDVO();
		subscriptionDVO.setUserId(currentUser.getId());
		subscriptionDVO.setUser(currentUser);
		subscriptionDVO.setGroupId(groupDVO.getId());
		subscriptionDVO.setGroup(groupDVO);
		subscriptionDVO.setState(SubscriptionDVO.State.OPEN);

		try {
			subscriptionService.save(subscriptionDVO);
		} catch (Exception e) {
			msg = String.format("Unable to store group subscription. Error: %s", e.getMessage());
			LOG.error(msg);
			succ = false;
		}

		return new ResponseObject<Boolean>(succ, succ, msg);
	}

	@Override
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Secured("ROLE_USER")
	@Path("{groupId}/getSubscriptions")
	public ResponseObject<List<SubscriptionDTO>> getGroupSubscriptions(@PathParam("groupId") int aGroupId) {

		String msg = null;
		Boolean succ = true;

		List<SubscriptionDTO> subscriptionDTOs = null;
		try {
			GroupDVO groupDVO = groupService.getById(aGroupId);
			List<SubscriptionDVO> subscriptionDVOs = subscriptionService.getSubscriptionRequestByGroup(groupDVO);
			subscriptionDTOs = SubscriptionFactory.createSubscriptionDTOs(subscriptionDVOs);
		} catch (Exception e)    {
			msg = String.format("Unable to get group subscriptions. Error: %s", e.getMessage());
			LOG.error(msg);
		}

		return new ResponseObject<List<SubscriptionDTO>>(subscriptionDTOs, succ, msg);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Secured("ROLE_USER")
	@Path("/listSubscriptions")
	public ResponseObject<List<SubscriptionDTO>> getUserSubscriptions() {

		boolean successfull = true;
		String message = null;

		List<SubscriptionDVO> subscriptionDVOs = getCurrentUser().getSubscriptions();
		List<SubscriptionDTO> subscriptionDTOs = null;
		try {
			subscriptionDTOs = SubscriptionFactory.createSubscriptionDTOs(subscriptionDVOs);
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}

		if (subscriptionDTOs == null) {
			successfull = false;
			message = messageSource.getMessage("msg.user.getGroups.fail", null, null);
		}

		return new ResponseObject<List<SubscriptionDTO>>(subscriptionDTOs, successfull, message);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Secured("ROLE_USER")
	@Path("/listOwnedGroups")
	public ResponseObject<List<GroupDTO>> getOwnedGroup() {

		boolean successfull = true;
		String message = null;

		List<GroupDVO> groupDVOs = getCurrentUser().getCreatedGroups();
		List<GroupDTO> groupDTOs = null;
		try {
			groupDTOs = GroupFactory.createGroups(groupDVOs);
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}

		if (groupDTOs == null) {
			successfull = false;
			message = messageSource.getMessage("msg.user.getGroups.fail", null, null);
		}

		return new ResponseObject<List<GroupDTO>>(groupDTOs, successfull, message);
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

	@Override
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Secured("ROLE_USER")
	@Path("{groupId}/subscribe")
	public ResponseObject<Boolean> subscribe(@PathParam("groupId") int aGroupId) {

		boolean success = false;
		String message = null;
		GroupDVO groupDVO = groupService.getById(aGroupId);
		UserDVO userDVO = getCurrentUser();

		if (groupDVO == null) {
			return new ResponseObject<Boolean>(false, false, "Group does not exist");
		}

		if (subscriptionService.hasUserGroupSubscription(userDVO, groupDVO)) {
			return new ResponseObject<Boolean>(false, false, "User is already registered");
		}

		if (groupDVO.getMode() == GroupDVO.Mode.PUBLIC) {
			SubscriptionDVO subscriptionDVO = new SubscriptionDVO();
			subscriptionDVO.setUserId(userDVO.getId());
			subscriptionDVO.setGroupId(aGroupId);
			subscriptionDVO.setState(SubscriptionDVO.State.APPROVED);
			subscriptionService.save(subscriptionDVO);
			success = true;
		} else {
			message = "Group is not public. Send subscription request.";
		}
		return new ResponseObject<Boolean>(success, success, message);
	}

	private UserDVO getCurrentUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return userService.getByOpenId(auth.getName());
	}

	@GET
	@Override
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Secured("ROLE_USER")
	@Path("{groupId}/getNewestMessage")
	public ResponseObject<MessageDTO> getNewestMessage(@PathParam("groupId") int aGroupId) {

		MessageDVO messageDVO = messageService.GetNewestMessageFromGroup(aGroupId);
		MessageDTO messageDTO = null;

		boolean success = false;
		String message = null;

		if (messageDVO == null) {
		} else {
			messageDTO = MessageFactory.createMessageDTO(messageDVO);
			success = true;
		}

		return new ResponseObject<MessageDTO>(messageDTO, success, message);
	}
}
