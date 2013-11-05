/**
 * 
 */
package ch.zhaw.mdp.lhb.citr.jpa.service;

import ch.zhaw.mdp.lhb.citr.jpa.entity.MessageDVO;

/**
 * @author Daniel Brun
 * @author Simon Lang
 *
 * Interface for the abstraction of the database access for 'Messages'.
 */
public interface IDBMessageService {
	
	/**
	 * Saves the given message.
	 * 
	 *
	 * @param aMessage the message to save.
	 * @return true if the message was saved successfully, false otherwise.
	 */
	public long save(MessageDVO aMessage);
	
	/**
	 * Looks for the message with the given id.
	 * 
	 * @param anId the id to look for.
	 * @return the corresponding message or null if no message for the given id could be found.
	 */
	public MessageDVO getMessageById(long anId);
}
