/**
 * 
 */
package ch.zhaw.mdp.lhb.citr.rest.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import ch.zhaw.mdp.lhb.citr.dto.GroupDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import ch.zhaw.mdp.lhb.citr.dto.UserDTO;
import ch.zhaw.mdp.lhb.citr.dto.UserFactory;
import ch.zhaw.mdp.lhb.citr.jpa.entity.UserDVO;
import ch.zhaw.mdp.lhb.citr.jpa.service.IDBUserService;
import ch.zhaw.mdp.lhb.citr.rest.IRUserServices;

import java.util.ArrayList;

/**
 * @author Daniel Brun
 * 
 * Implementation of the Service-Interface {@link IRUserServices}.
 */
@Component
@Path("/user")
@Scope("request")
public class UserServiceRestImpl implements IRUserServices {

	@Autowired
	private IDBUserService userService;

	@GET
	@Override
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Secured("ROLE_USER")
	@Path("/details")
	public UserDTO getUser(@QueryParam("openId") String anOpenId) {
		UserDVO user = new UserDVO();
		user.setOpenId(anOpenId);
		
		return UserFactory.createUserDTO(userService.findPerson(user));
	}

	@Override
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Secured("ROLE_USER")
	@Path("/register")
	public boolean registerUser(UserDTO aUser) {
		UserDVO user = UserFactory.createUserDVO(aUser);
		
		if(user == null){
			throw new IllegalArgumentException("The argument aUser must not be null!");
		}
		
		if(userService.findPerson(user) == null){
			if(aUser.getUsername() != null && aUser.getOpenId() != null){
				return userService.save(user);
			}
		}
		
		return false;
	}


	@GET
	@Override
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Secured("ROLE_USER")
	@Path("/details")
	public ArrayList<GroupDTO> getGroups() {
		ArrayList list = new ArrayList();
		GroupDTO groupDTO = new GroupDTO();
		groupDTO.setName("Hardcoded Test");
		groupDTO.setPublicGroup(true);
		list.add(groupDTO);
		return list;
	}
}
