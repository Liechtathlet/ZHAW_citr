/**
 * 
 */
package ch.zhaw.mdp.lhb.citr.rest.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import ch.zhaw.mdp.lhb.citr.dto.MessageDTO;
import ch.zhaw.mdp.lhb.citr.jpa.service.IDBMessageService;
import ch.zhaw.mdp.lhb.citr.rest.IRMessageServices;

/**
 * @author Daniel Brun
 *
 */
@Component
@Path("/message")
@Scope("request")
public class MessageServiceRestImpl implements IRMessageServices {

	@Autowired
	private IDBMessageService messageService;

	/* (non-Javadoc)
	 * @see ch.zhaw.mdp.lhb.citr.rest.IRMessageServices#sendMessage(ch.zhaw.mdp.lhb.citr.dto.MessageDTO)
	 */
	@POST
	@Override
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Secured("ROLE_USER")
	@Path("/send")
	public boolean sendMessage(MessageDTO aMessage) {
		// TODO Auto-generated method stub
		System.out.println("Received Request for sendMessage: " + aMessage.getMessageText());
		return true;
	}
}
