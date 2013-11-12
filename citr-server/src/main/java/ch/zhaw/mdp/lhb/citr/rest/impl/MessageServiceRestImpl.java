/**
 * 
 */
package ch.zhaw.mdp.lhb.citr.rest.impl;

import java.util.Calendar;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import ch.zhaw.mdp.lhb.citr.dto.MessageDTO;
import ch.zhaw.mdp.lhb.citr.jpa.entity.MessageDVO;
import ch.zhaw.mdp.lhb.citr.jpa.service.IDBMessageService;
import ch.zhaw.mdp.lhb.citr.response.ResponseObject;
import ch.zhaw.mdp.lhb.citr.rest.IRMessageServices;

/**
 * @author Daniel Brun
 * @author Simon Lang
 *
 * Implementation of the Service-Interface {@link IRMessageServices}.
 */
@Component
@Path("/message")
@Scope("singleton")
public class MessageServiceRestImpl implements IRMessageServices {

	private static final Logger LOG = LoggerFactory
			.getLogger(MessageServiceRestImpl.class);
	
	@Autowired
	private IDBMessageService messageService;

	@Autowired
	private ReloadableResourceBundleMessageSource messageSource;
	
	/* (non-Javadoc)
	 * @see ch.zhaw.mdp.lhb.citr.rest.IRMessageServices#sendMessage(ch.zhaw.mdp.lhb.citr.dto.MessageDTO)
	 */
	@POST
	@Override
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Secured("ROLE_USER")
	@Path("/create")
	public ResponseObject<Boolean> createMessage(MessageDTO aMessageDTO) {
		if(aMessageDTO == null){
			throw new IllegalArgumentException("The argument aMessageDTO must not be null");
		}
		
		Boolean result = Boolean.FALSE;
		String msg = "";
		
		//TODO: Get user credentials
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		//TODO: Check permission for group
		
		//TODO: Validate message
		MessageDVO messageDVO = new MessageDVO();
		messageDVO.setMessage(aMessageDTO.getMessageText());
		messageDVO.setGroupId(aMessageDTO.getGroupId());
		Calendar today = Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 0);
		messageDVO.setSendDate(today.getTime());
		
		//TODO: REVIEW@SLANG: Weiss ned öb im ID-Feld immer en Wert > 0 drin staht, wenn de insert gfailt isch..., uf jede fall müsst mer denn s id feld mit -1 initialisiere
				//Han etzte mal vorläufig boolean dri gno, chasch sust gern wider d id dri ne ;-)
		messageService.save(messageDVO);
		result = Boolean.TRUE;
		msg = messageSource.getMessage("msg.message.succ", null, null);
		//TODO: Implement different return texts.
		
		return new ResponseObject<Boolean>(result, result.booleanValue(), msg);
	}
}