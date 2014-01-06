package ch.zhaw.mdp.lhb.citr.jpa.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ch.zhaw.mdp.lhb.citr.Logging.LoggingFactory;
import ch.zhaw.mdp.lhb.citr.Logging.LoggingStrategy;
import ch.zhaw.mdp.lhb.citr.jpa.entity.TagsDVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.zhaw.mdp.lhb.citr.jpa.entity.GroupDVO;
import ch.zhaw.mdp.lhb.citr.jpa.service.GroupRepository;

/**
 * @author Daniel Brun
 * @author Simon Lang
 * 
 *         Implementation of the DB-Service interface {@link GroupRepository}
 */
@Service("groupService")
public class GroupRepositoryJpaImpl implements GroupRepository {

    private static final LoggingStrategy LOG = LoggingFactory.get();

    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
	this.entityManager = entityManager;
    }

    @Override
    public GroupDVO getById(int id) {
	return entityManager.find(GroupDVO.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<GroupDVO> getAll() {
	Query query = entityManager.createNamedQuery("Group.findAll");
	List<GroupDVO> groups = query.getResultList();
	for (GroupDVO group : groups) {
	    group.getTags().size();
	}
	return groups;
    }

    @Override
    @Transactional(readOnly = true)
    public int create(GroupDVO group) {
	entityManager.persist(group);
	entityManager.flush();
	return group.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public void remove(int groupId) throws Exception {
	Query subscriptionQuery = entityManager
		.createQuery("delete from SubscriptionDVO s where s.groupId = :groupId");
	Query messageQuery = entityManager
		.createQuery("delete from MessageDVO m where m.groupId = :groupId");
	Query tagQuery = entityManager
		.createQuery("delete from TagsDVO t where t.groupId = :groupId");
	Query groupQuery = entityManager
		.createQuery("delete from GroupDVO g where g.id = :groupId");

	subscriptionQuery.setParameter("groupId", groupId);
	messageQuery.setParameter("groupId", groupId);
	tagQuery.setParameter("groupId", groupId);
	groupQuery.setParameter("groupId", groupId);

	subscriptionQuery.executeUpdate();
	messageQuery.executeUpdate();
	tagQuery.executeUpdate();
	groupQuery.executeUpdate();
    }

    @Override
    @Transactional(readOnly = true)
    public void addTagsToGroup(GroupDVO group) {
	for (TagsDVO tag : group.getTags()) {
	    LOG.info("Group id of tag: " + group.getId());
	    tag.setGroupId(group.getId());
	    entityManager.persist(tag);
	    entityManager.flush();
	}
    }
}
