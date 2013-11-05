package ch.zhaw.mdp.lhb.citr.rest;

import ch.zhaw.mdp.lhb.citr.dto.MessageDTO;

/**
 * @author Daniel Brun
 * @author Simon Lang
 *
 * Interface for the Message-Rest-Services
 */
public interface IRMessageServices {

	/**
	 * Commits a message in the given group.
	 * 
	 * @param aMessage The message to send.
	 * @return long>0 if the message was sent successfully, long=0 otherwise.
	 */
	public long createMessage(MessageDTO aMessage);
}
