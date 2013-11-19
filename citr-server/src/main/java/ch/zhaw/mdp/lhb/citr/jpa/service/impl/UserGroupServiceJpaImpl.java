package ch.zhaw.mdp.lhb.citr.jpa.service.impl;

import ch.zhaw.mdp.lhb.citr.jpa.entity.UserGroupDVO;
import ch.zhaw.mdp.lhb.citr.jpa.service.IDBUserGroupService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Simon Lang
 *
 * Implementation of the DB-Service interface {@link IDBUserGroupService}
 */
@Service("userGroupService")
public class UserGroupServiceJpaImpl implements IDBUserGroupService {

	private EntityManager entityManager;

	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public boolean save(UserGroupDVO userGroup) {

		entityManager.persist(userGroup);
		entityManager.flush();

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
