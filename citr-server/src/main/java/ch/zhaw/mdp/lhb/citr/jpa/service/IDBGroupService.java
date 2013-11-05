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
	public List<GroupDVO> getAll();
	public int create(GroupDVO group);
}
