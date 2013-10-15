package ch.zhaw.mdp.lhb.citr.rest;

import ch.zhaw.mdp.lhb.citr.jpa.entity.GroupDVO;
import ch.zhaw.mdp.lhb.citr.jpa.service.IDBGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


/**
 * @author Simon Lang
 *
 * Groups service.
 */
@Component
@Path("/groups")
@Scope("request")
public class Groups {

	@Autowired
	IDBGroupService groupService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Secured("ROLE_USER")
	@Path("/all")
	public List<GroupDVO> getAllGroups() {
		return groupService.getAll();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Secured("ROLE_USER")
	@Path("/create/{name}/{state}/{mode}")
	public int createGroup(@PathParam("name") String name, @PathParam("state") int state, @PathParam("mode") int mode) {
		GroupDVO group = new GroupDVO();
		group.setName(name);
		group.setState(state);
		group.setMode(mode);
		groupService.create(group);
		return group.getId();
	}
}
