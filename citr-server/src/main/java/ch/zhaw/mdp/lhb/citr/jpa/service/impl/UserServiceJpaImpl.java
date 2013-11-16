package ch.zhaw.mdp.lhb.citr.jpa.service.impl;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ch.zhaw.mdp.lhb.citr.jpa.entity.GroupDVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ch.zhaw.mdp.lhb.citr.jpa.entity.UserDVO;
import ch.zhaw.mdp.lhb.citr.jpa.service.IDBUserService;

/**
 * @author Daniel Brun
 * @author Simon Lang
 *
 * Implementation of the DB-Service interface {@link IDBUserService}
 */
@Service("userService")
public class UserServiceJpaImpl implements IDBUserService {

	private EntityManager entityManager;

	@Transactional(readOnly = true)
	public UserDVO getById(int id) {
		return entityManager.find(UserDVO.class, id);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<UserDVO> getAll() {
		Query query = entityManager.createNamedQuery("User.findAll");
		List<UserDVO> user = null;
		user = query.getResultList();
		return user;
	}

	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public boolean save(UserDVO user) {

		entityManager.persist(user);
		entityManager.flush();

		return true;
	}
	
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public boolean update(UserDVO user) {
		entityManager.merge(user);
		entityManager.flush();
		
		return true;
	}
	

	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public boolean delete(UserDVO user) {
		user = entityManager.getReference(UserDVO.class, user.getId());
		
		if (user == null)
		{
			return false;
		}
		
		entityManager.remove(user);
		entityManager.flush();
		
		return true;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public UserDVO findPerson(UserDVO user) {
		UserDVO result = null;
		
		Query queryFindPerson = entityManager.createNamedQuery("User.findUser");
		queryFindPerson.setParameter("username", user.getUsername());
		queryFindPerson.setParameter("openId", user.getOpenId());
		
		List<UserDVO> users = queryFindPerson.getResultList();
		
		if(users.size() > 0) {
			result = users.get(0);
		}
		
		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<GroupDVO> getGroups(UserDVO user) {
		return findPerson(user).getGroups();
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
