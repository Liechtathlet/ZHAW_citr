package ch.zhaw.mdp.lhb.citr.jpa.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ch.zhaw.mdp.lhb.citr.jpa.entity.GroupDVO;
import ch.zhaw.mdp.lhb.citr.jpa.entity.SubscriptionDVO;
import ch.zhaw.mdp.lhb.citr.jpa.entity.UserDVO;
import ch.zhaw.mdp.lhb.citr.jpa.service.SubscriptionRepository;

/**
 * @author Simon Lang
 *
 * Implementation of the DB-Service interface {@link ch.zhaw.mdp.lhb.citr.jpa.service.SubscriptionRepository}
 * 
 * FIXME: Review & save -> Exception catch & entsprechends Handling
 */
@Service("subscriptionService")
public class SubscriptionRepositoryJpaImpl implements SubscriptionRepository {

	private EntityManager entityManager;

	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public boolean save(SubscriptionDVO subscriptionDVO) {

		entityManager.persist(subscriptionDVO);
		entityManager.flush();

		return true;
	}

	@Override
	public List<SubscriptionDVO> getSubscriptionByGroup(GroupDVO group, SubscriptionDVO.State aState) {
		Query q = entityManager.createQuery("select ug from SubscriptionDVO ug where ug.groupId = :groupId and ug.state = :state");
		q.setParameter("groupId", group.getId());
		q.setParameter("state", aState);
		List<SubscriptionDVO> subscriptionDVOs = q.getResultList();
		
		if(subscriptionDVOs == null){
		    subscriptionDVOs = new ArrayList<SubscriptionDVO>();
		}
		
		return subscriptionDVOs;
	}

	@Override
	public boolean hasUserGroupSubscription(UserDVO user, GroupDVO group) {
		Query q = entityManager.createQuery("select s from SubscriptionDVO s where s.groupId = :groupId and s.userId = :userId");
		q.setParameter("groupId", group.getId());
		q.setParameter("userId", user.getId());
		return q.getResultList().size() > 0;
	}

	@Override
	public SubscriptionDVO getSubscription(UserDVO userDVO, GroupDVO groupDVO) {
		Query q = entityManager.createQuery("select s from SubscriptionDVO s where s.groupId = :groupId and s.userId = :userId");
		q.setParameter("groupId", groupDVO.getId());
		q.setParameter("userId", userDVO.getId());
		List<SubscriptionDVO> subscriptionDVOs = q.getResultList();
		return subscriptionDVOs.get(0);
	}

	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void updateState(SubscriptionDVO subscriptionDVO, SubscriptionDVO.State state) {
		Query q = entityManager.createQuery("update SubscriptionDVO s set s.state = :state where s.groupId = :groupId and s.userId = :userId");
		q.setParameter("state", state);
		q.setParameter("groupId", subscriptionDVO.getGroupId());
		q.setParameter("userId", subscriptionDVO.getUserId());
		q.executeUpdate();
	}

	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public boolean remove(SubscriptionDVO subscriptionDVO) {
		Query q = entityManager.createQuery("delete from SubscriptionDVO s where s.groupId = :groupId and s.userId = :userId");
		q.setParameter("groupId", subscriptionDVO.getGroupId());
		q.setParameter("userId", subscriptionDVO.getUserId());
		q.executeUpdate();
		
		return true;
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
