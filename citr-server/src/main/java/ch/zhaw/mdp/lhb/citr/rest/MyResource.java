package ch.zhaw.mdp.lhb.citr.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import ch.zhaw.mdp.lhb.citr.jpa.entity.UserDVO;
import ch.zhaw.mdp.lhb.citr.jpa.service.IDBUserService;

// The Java class will be hosted at the URI path "/resources"
@Path("/myresources")

// Define as configurable spring component
@Component

// Define scope to spring IOC container. Return a single bean instance per HTTP request.
@Scope("request")
public class MyResource {
	private static final Logger LOGGER = LoggerFactory.getLogger(MyResource.class);
	
	@Autowired
	IDBUserService personService;

	// The Java method will process HTTP GET requests
	@GET
	// The Java method will produce content identified by the MIME Media
	// type "text/plain"
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Secured("ROLE_USER")
	@Path("/test")
	public List<UserDVO> getIt() {
		LOGGER.error("Got request");
		UserDVO person = new UserDVO();
		person.setUsername("David Sells");
		person.setOpenId("DS");

		addIfDoesNotExist(person);

		StringBuffer buffer = new StringBuffer();

		List<UserDVO> persons = personService.getAll();
		for (UserDVO person2 : persons) {
			buffer.append(person2.getUsername()).append(":").append(person2.getOpenId())
					.append("\n");
		}

		return persons;
	}
	private void addIfDoesNotExist(UserDVO person) {
		if(personService.findPerson(person) == null) {
			personService.save(person);
		}
	}
}
