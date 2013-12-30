package ch.zhaw.mdp.lhb.citr.rest;

import ch.zhaw.mdp.lhb.citr.dto.MessageDTO;
import ch.zhaw.mdp.lhb.citr.response.ResponseObject;

/**
 * @author Daniel Brun
 * @author Simon Lang
 *
 * Interface for the Message-Rest-Services
 */
public interface MessageServices {

	/**
	 * Commits a message in the given group.
	 * 
	 * @param aMessage The message to send.
	 * @return long>0 if the message was sent successfully, long=0 otherwise.
	 */
	public ResponseObject<Boolean> createMessage(MessageDTO aMessage);
}
