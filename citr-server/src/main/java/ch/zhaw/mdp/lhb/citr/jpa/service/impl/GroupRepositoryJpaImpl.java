package ch.zhaw.mdp.lhb.citr.jpa.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.zhaw.mdp.lhb.citr.Logging.LoggingFactory;
import ch.zhaw.mdp.lhb.citr.Logging.LoggingStrategy;
import ch.zhaw.mdp.lhb.citr.jpa.entity.GroupDVO;
import ch.zhaw.mdp.lhb.citr.jpa.entity.TagsDVO;
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
    public GroupDVO getById(int anId) {
	return entityManager.find(GroupDVO.class, anId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<GroupDVO> getAll() {
	Query query = entityManager.createNamedQuery("Group.findAll");
	List<GroupDVO> groups = query.getResultList();

	if (groups == null) {
	    groups = new ArrayList<GroupDVO>();
	}

	for (GroupDVO group : groups) {
	    group.getTags().size();
	}

	return groups;
    }

    @Override
    @Transactional(readOnly = true)
    public int create(GroupDVO aGroup) {
	// Reset id
	aGroup.setId(-1);

	entityManager.persist(aGroup);
	entityManager.flush();

	return aGroup.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean remove(int aGroupId) {
	Query subscriptionQuery = entityManager
		.createQuery("delete from SubscriptionDVO s where s.groupId = :groupId");
	Query messageQuery = entityManager
		.createQuery("delete from MessageDVO m where m.groupId = :groupId");
	Query tagQuery = entityManager
		.createQuery("delete from TagsDVO t where t.groupId = :groupId");
	Query groupQuery = entityManager
		.createQuery("delete from GroupDVO g where g.id = :groupId");

	subscriptionQuery.setParameter("groupId", aGroupId);
	messageQuery.setParameter("groupId", aGroupId);
	tagQuery.setParameter("groupId", aGroupId);
	groupQuery.setParameter("groupId", aGroupId);

	try {
	    subscriptionQuery.executeUpdate();
	    messageQuery.executeUpdate();
	    tagQuery.executeUpdate();
	    groupQuery.executeUpdate();
	} catch (Exception e) {
	    LOG.error("Error removing group: " + e.toString());
	    return false;
	}
	
	return true;
    }

    @Override
    @Transactional(readOnly = true)
    public void addTagsToGroup(GroupDVO aGroup) {
	
	for (TagsDVO tag : aGroup.getTags()) {
	    tag.setGroupId(aGroup.getId());
	    
	    entityManager.persist(tag);
	    entityManager.flush();
	}
	
    }
}
