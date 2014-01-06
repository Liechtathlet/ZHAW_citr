/**
 * 
 */
package ch.zhaw.mdp.lhb.citr.jpa.service;

import ch.zhaw.mdp.lhb.citr.jpa.entity.MessageDVO;

import java.util.Date;
import java.util.List;

/**
 * @author Daniel Brun
 * @author Simon Lang
 * 
 *         Interface for the abstraction of the database access for 'Messages'.
 */
public interface MessageRepository {

    /**
     * Saves the given message.
     * 
     * @param aMessage the message to save.
     * @return The id of the group or -1 otherwise.
     */
    public long save(MessageDVO aMessage);

    /**
     * Looks for the message with the given id.
     * 
     * @param anId the id to look for.
     * @return the corresponding message or null if no message for the given id could be found.
     */
    public MessageDVO getMessageById(long anId);

    /**
     * Gets the messages of a given group.
     * 
     * @param aGroupId Group id to get the messages from.
     * @param aCount Message count to get.
     * @return Found messages.
     */
    public List<MessageDVO> getMessagesByGroup(int aGroupId, int aCount);

    /**
     * Gets the messages of a given group older then the providerd Date.
     * 
     * @param aGroupId Group id to get the messages from.
     * @param aCount Message count to get.
     * @param anOlderThan Get messages older than this Date.
     * @return Found messages.
     */
    public List<MessageDVO> getMessagesByGroup(int aGroupId, int aCount, Date anOlderThan);
}
