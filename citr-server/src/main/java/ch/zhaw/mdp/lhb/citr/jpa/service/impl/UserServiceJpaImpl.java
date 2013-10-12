package ch.zhaw.mdp.lhb.citr.jpa.service.impl;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ch.zhaw.mdp.lhb.citr.jpa.entity.UserDVO;
import ch.zhaw.mdp.lhb.citr.jpa.service.IDBUserService;


@Service("userService")
public class UserServiceJpaImpl implements IDBUserService {

	private EntityManager entityManager;

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Transactional(readOnly = true)
	public UserDVO getById(int id) {
		// TODO Auto-generated method stub
		return entityManager.find(UserDVO.class, id);
	}
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<UserDVO> getAll() {
		Query query = entityManager.createNamedQuery("User.findAll");
		List<UserDVO> persons = null;
		persons = query.getResultList();
		return persons;
	}

	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public boolean save(UserDVO person) {

		entityManager.persist(person);
		entityManager.flush();

		return true;
	}
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public boolean update(UserDVO person) {
		entityManager.merge(person);
		entityManager.flush();
		return true;
	}
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public boolean delete(UserDVO person) {
		person = entityManager.getReference(UserDVO.class, person.getId());
		if (person == null)
			return false;
		entityManager.remove(person);
		entityManager.flush();
		return true;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public UserDVO findPerson(UserDVO person) {
		UserDVO result = null;
		Query queryFindPerson = entityManager.createNamedQuery("User.findUser");
		queryFindPerson.setParameter("name", person.getName());
		queryFindPerson.setParameter("age", person.getAge());
		List<UserDVO> persons = queryFindPerson.getResultList();
		if(persons.size() > 0) {
			result = persons.get(0);
		}
		return result;
	}
}
