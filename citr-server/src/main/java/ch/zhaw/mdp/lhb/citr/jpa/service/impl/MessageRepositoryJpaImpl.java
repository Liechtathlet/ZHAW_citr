/**
 * 
 */
package ch.zhaw.mdp.lhb.citr.jpa.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.zhaw.mdp.lhb.citr.Logging.LoggingFactory;
import ch.zhaw.mdp.lhb.citr.Logging.LoggingStrategy;
import ch.zhaw.mdp.lhb.citr.jpa.entity.MessageDVO;
import ch.zhaw.mdp.lhb.citr.jpa.service.MessageRepository;

/**
 * @author Daniel Brun
 * @author Simon Lang
 * 
 *         Implementation of the DB-Service interface {@link MessageRepository}
 */
@Service("messageService")
public class MessageRepositoryJpaImpl implements MessageRepository {

    private static final LoggingStrategy LOG = LoggingFactory.get();

    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
	this.entityManager = entityManager;
    }

    @Override
    @Transactional(readOnly = true)
    public long save(MessageDVO aMessage) {
	// Preset Id
	aMessage.setId(-1);

	entityManager.persist(aMessage);
	entityManager.flush();

	return aMessage.getId();
    }

    @Override
    public MessageDVO getMessageById(long anId) {
	return entityManager.find(MessageDVO.class, anId);
    }

    @Override
    public List<MessageDVO> getMessagesByGroup(int aGroupId, int aCount) {

	Query q = entityManager
		.createQuery("select m from MessageDVO m where m.groupId = :groupId "
			+ "order by m.sendDate desc");

	q.setParameter("groupId", aGroupId);
	q.setMaxResults(aCount);

	List<MessageDVO> list = q.getResultList();

	if (list == null) {
	    list = new ArrayList<MessageDVO>();
	}

	return list;
    }

    @Override
    public List<MessageDVO> getMessagesByGroup(int aGroupId, int aCount,
	    Date aOlderThan) {

	Query q = entityManager.createQuery("select m from MessageDVO m "
		+ "where m.groupId = :groupId and m.sendDate < :olderThan "
		+ "order by m.sendDate desc");

	q.setParameter("groupId", aGroupId);
	q.setParameter("olderThan", aOlderThan);
	q.setMaxResults(aCount);

	List<MessageDVO> list = q.getResultList();

	if (list == null) {
	    list = new ArrayList<MessageDVO>();
	}

	return list;
    }
}
