/**
 * 
 */
package ch.zhaw.mdp.lhb.citr.jpa.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ch.zhaw.mdp.lhb.citr.Logging.LoggingFactory;
import ch.zhaw.mdp.lhb.citr.Logging.LoggingStrategy;
import org.springframework.stereotype.Service;

import ch.zhaw.mdp.lhb.citr.jpa.entity.MessageDVO;
import ch.zhaw.mdp.lhb.citr.jpa.service.MessageRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author Daniel Brun
 * @author Simon Lang
 *
 * Implementation of the DB-Service interface {@link MessageRepository}
 */
@Service("messageService")
public class MessageRepositoryJpaImpl implements MessageRepository{

	private static final LoggingStrategy LOG = LoggingFactory.get();

	private EntityManager entityManager;

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/**
	 * Saves the given message.
	 *
	 *
	 * @param aMessage the message to save.
	 * @return true if the message was saved successfully, false otherwise.
	 */
	@Override
	@Transactional(readOnly = true)
	public long save(MessageDVO aMessage) {
		entityManager.persist(aMessage);
		entityManager.flush();
		return aMessage.getId();
	}

	/**
	 * Looks for the aMessage with the given id.
	 *
	 * @param anId the id to look for.
	 * @return the corresponding aMessage or null if no aMessage for the given id could be found.
	 */
	@Override
	public MessageDVO getMessageById(long anId) {
		return entityManager.find(MessageDVO.class, anId);
	}

	/**
	 * Gets the messages of a given group.
	 * @param aGroupId Group id to get the messages from.
	 * @param count Message count to get.
	 * @return Found messages.
	 */
	@Override
	public List<MessageDVO> getMessagesByGroup(int aGroupId, int count) {

		Query q = entityManager.createQuery("select m from MessageDVO m where m.groupId = :groupId " +
				"order by m.sendDate desc");
		q.setParameter("groupId", aGroupId);
		q.setMaxResults(count);

		List list = q.getResultList();
		if (list.size() == 0) {
			return null;
		}

		return list;
	}

	/**
	 * Gets the messages of a given group older then the providerd Date.
	 * @param aGroupId Group id to get the messages from.
	 * @param count Message count to get.
	 * @param olderThan Get messages older than this Date.
	 * @return Found messages.
	 */
	@Override
	public List<MessageDVO> getMessagesByGroup(int aGroupId, int count, Date olderThan) {

		Query q = entityManager.createQuery("select m from MessageDVO m " +
				"where m.groupId = :groupId and m.sendDate < :olderThan " +
				"order by m.sendDate desc");
		q.setParameter("groupId", aGroupId);
		q.setParameter("olderThan", olderThan);
		q.setMaxResults(count);

		List list = q.getResultList();
		if (list.size() == 0) {
			return null;
		}

		return list;
	}
}
