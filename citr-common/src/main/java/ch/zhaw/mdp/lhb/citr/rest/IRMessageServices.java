package ch.zhaw.mdp.lhb.citr.rest;

import ch.zhaw.mdp.lhb.citr.dto.MessageDTO;

/**
 * @author Daniel Brun
 *
 * Interface for the Message-Rest-Services
 */
public interface IRMessageServices {

	/**
	 * Commits a message in the given group.
	 * 
	 * @param aGroupId The id of the group.
	 * @param aMessage The message to send.
	 * @return True if the message was sent successfully, false otherwise.
	 */
	public boolean sendMessage(String aGroupId, MessageDTO aMessage);
}
