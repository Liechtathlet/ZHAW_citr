package ch.zhaw.mdp.lhb.citr.jpa.service;

import ch.zhaw.mdp.lhb.citr.jpa.entity.GroupDVO;
import ch.zhaw.mdp.lhb.citr.jpa.entity.UserGroupDVO;

import java.util.List;

/**
 * @author Simon Lang
 *
 * Interface for the UserGroup-Persistence-Services
 */
public interface IDBUserGroupService {
	/**
	 * Creates a new userGroup.
	 *
	 * @param userGroup The userGroup to save.
	 * @return True if the userGroup was saved successfully.
	 */
	boolean save(UserGroupDVO userGroup);

	/**
	 * Gets all userGroup requests for a particular group.
	 * @param group The group to get the subscriptions to.
	 * @return List of subscriptions.
	 */
	List<UserGroupDVO> getSubscriptionRequestByGroup(GroupDVO group);
}
