package ch.zhaw.mdp.lhb.citr.jpa.service.impl;

import ch.zhaw.mdp.lhb.citr.jpa.entity.GroupDVO;
import ch.zhaw.mdp.lhb.citr.jpa.service.IDBGroupService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @author Daniel Brun
 * @author Simon Lang
 *
 */
@Service("groupService")
public class GroupServiceJpaImpl implements IDBGroupService {

	private EntityManager entityManager;

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	@Transactional(readOnly = true)
	public List<GroupDVO> getAll() {
		Query query = entityManager.createNamedQuery("Group.findAll");
		return query.getResultList();
	}
}
