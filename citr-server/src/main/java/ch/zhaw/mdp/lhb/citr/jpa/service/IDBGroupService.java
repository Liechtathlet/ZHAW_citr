package ch.zhaw.mdp.lhb.citr.jpa.service;

import ch.zhaw.mdp.lhb.citr.jpa.entity.GroupDVO;

import java.util.List;

/**
 * @author Daniel Brun
 * @author Simon Lang
 *
 * Interface for the abstraction of the database access for groups.
 */
public interface IDBGroupService {

	/**
	 * Gets the group by id.
	 *
	 * @param id The id of the group.
	 * @return The group or null if no user could be found.
	 */
	public GroupDVO getById(int id);

	/**
	 * Gets all groups
	 *
	 * @return All groups.
	 */
	public List<GroupDVO> getAll();

	/**
	 * Creates the provided group.
	 *
	 * @param group Group to create.
	 * @return  Id of the created group.
	 */
	public int create(GroupDVO group);
}
