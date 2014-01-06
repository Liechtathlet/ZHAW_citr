package ch.zhaw.mdp.lhb.citr.jpa.service;

import ch.zhaw.mdp.lhb.citr.jpa.entity.GroupDVO;

import java.util.List;

/**
 * @author Daniel Brun
 * @author Simon Lang
 * 
 *         Interface for the abstraction of the database access for groups.
 */
public interface GroupRepository {

    /**
     * Gets the group by id.
     * 
     * @param anId The id of the group.
     * @return The group or null if no user could be found.
     */
    public GroupDVO getById(int anId);

    /**
     * Gets all groups
     * 
     * @return All groups.
     */
    public List<GroupDVO> getAll();

    /**
     * Creates the provided group.
     * 
     * @param aGroup Group to create.
     * @return Id of the created group.
     */
    public int create(GroupDVO aGroup);

    /**
     * Removes a group.
     * 
     * @param aGroupId Group id to remove.
     */
    public void remove(int aGroupId) throws Exception;

    /**
     * Adds tags to the group.
     * 
     * @param aGroup Group with the tags.
     */
    void addTagsToGroup(GroupDVO aGroup);
}
