/**
 * 
 */
package ch.zhaw.mdp.lhb.citr.jpa.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import ch.zhaw.mdp.lhb.citr.jpa.entity.MessageDVO;
import ch.zhaw.mdp.lhb.citr.jpa.service.IDBMessageService;

/**
 * @author Daniel Brun
 *
 */
@Service("messageService")
public class MessageServiceJpaImpl implements IDBMessageService{

	private EntityManager entityManager;

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public boolean save(MessageDVO aMessage) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public MessageDVO getMessageById(long anId) {
		// TODO Auto-generated method stub
		return null;
	}

}
