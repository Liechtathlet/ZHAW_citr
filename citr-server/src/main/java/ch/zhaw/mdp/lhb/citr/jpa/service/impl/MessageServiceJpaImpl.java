/**
 * 
 */
package ch.zhaw.mdp.lhb.citr.jpa.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import ch.zhaw.mdp.lhb.citr.jpa.entity.MessageDVO;
import ch.zhaw.mdp.lhb.citr.jpa.service.IDBMessageService;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Daniel Brun
 * @author Simon Lang
 *
 * Implementation of the DB-Service interface {@link IDBMessageService}
 */
@Service("messageService")
public class MessageServiceJpaImpl implements IDBMessageService{

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

}
