package ch.zhaw.mdp.lhb.citr.jpa.service;

import ch.zhaw.mdp.lhb.citr.jpa.entity.UserGroupDVO;

/**
 * @author Simon Lang
 *
 * Interface for the UserGroup-Persistence-Services
 */
public interface IDBUserGroupService {
	/**
	 * Creates a new UserGroup.
	 *
	 * @param userGroup The userGroup to save.
	 * @return True if the userGroup was saved successfully.
	 */
	boolean save(UserGroupDVO userGroup);
}
