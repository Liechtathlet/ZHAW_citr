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
import ch.zhaw.mdp.lhb.citr.jpa.service.IDBMessageService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Daniel Brun
 * @author Simon Lang
 *
 * Implementation of the DB-Service interface {@link IDBMessageService}
 */
@Service("messageService")
public class MessageServiceJpaImpl implements IDBMessageService{

	private static final LoggingStrategy LOG = LoggingFactory.get();

	private EntityManager entityManager;

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	@Transactional(readOnly = true)
	public long save(MessageDVO message) {
		entityManager.persist(message);
		entityManager.flush();
		return message.getId();
	}

	@Override
	public MessageDVO getMessageById(long anId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MessageDVO GetNewestMessageFromGroup(int aGroupId) {

		Query q = entityManager.createQuery("select m from MessageDVO m where m.groupId = :groupId order by m.sendDate desc");
		q.setParameter("groupId", aGroupId);

		List list = q.getResultList();
		if (list.size() == 0) {
			return null;
		}

		return (MessageDVO)list.get(0);
	}

}
