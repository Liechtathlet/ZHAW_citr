package ch.zhaw.mdp.lhb.citr.jpa.service.impl;

import ch.zhaw.mdp.lhb.citr.Logging.LoggingFactory;
import ch.zhaw.mdp.lhb.citr.Logging.LoggingStrategy;
import ch.zhaw.mdp.lhb.citr.jpa.entity.GroupDVO;
import ch.zhaw.mdp.lhb.citr.jpa.entity.SubscriptionDVO;
import ch.zhaw.mdp.lhb.citr.jpa.entity.UserDVO;
import ch.zhaw.mdp.lhb.citr.jpa.service.IDBSubscriptionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @author Simon Lang
 *
 * Implementation of the DB-Service interface {@link ch.zhaw.mdp.lhb.citr.jpa.service.IDBSubscriptionService}
 */
@Service("subscriptionService")
public class SubscriptionServiceJpaImpl implements IDBSubscriptionService {

	private EntityManager entityManager;

	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public boolean save(SubscriptionDVO subscriptionDVO) {

		entityManager.persist(subscriptionDVO);
		entityManager.flush();

		return true;
	}

	@Override
	public List<SubscriptionDVO> getSubscriptionRequestByGroup(GroupDVO group) {
		Query q = entityManager.createQuery("select ug from SubscriptionDVO ug where ug.groupId = :groupId and ug.state = :state");
		q.setParameter("groupId", group.getId());
		q.setParameter("state", "open");
		List<SubscriptionDVO> subscriptionDVOs = q.getResultList();
		return subscriptionDVOs;
	}

	private static final LoggingStrategy LOG = LoggingFactory.get();

	@Override
	public boolean hasUserGroupSubscription(UserDVO user, GroupDVO group) {
		Query q = entityManager.createQuery("select s from SubscriptionDVO s where s.groupId = :groupId and s.userId = :userId");
		q.setParameter("groupId", group.getId());
		q.setParameter("userId", user.getId());
		return q.getResultList().size() > 0;
	}

	/**
	 * Sets the Entity Manager
	 *
	 * @param entityManager The Entity Manager
	 */
	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/**
	 * Gets the Entity Manager
	 *
	 * @return The Entity Manager
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}
}
