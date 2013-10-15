package ch.zhaw.mdp.lhb.citr.rest;

import ch.zhaw.mdp.lhb.citr.jpa.entity.GroupDVO;
import ch.zhaw.mdp.lhb.citr.jpa.service.IDBGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
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
}
