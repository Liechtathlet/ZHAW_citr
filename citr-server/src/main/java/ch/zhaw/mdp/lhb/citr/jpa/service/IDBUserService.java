package ch.zhaw.mdp.lhb.citr.jpa.service;

import ch.zhaw.mdp.lhb.citr.jpa.entity.UserDVO;
import ch.zhaw.mdp.lhb.citr.jpa.entity.UserGroupDVO;

import java.util.List;


/**
 * @author Daniel Brun
 *
 * Interface for the User-Persistence-Services
 */
public interface IDBUserService {
	
	/**
	 * Saves the given user to the database.
	 * No check / validation will be performed.
	 * 
	 * @param user The user to save.
	 * @return True if the user was saved successfully.
	 */
	public boolean save(UserDVO person);
	
	/**
	 * Gets a complete User-List from the database.
	 * 
	 * @return a list with all users.
	 */
	public List<UserDVO> getAll();
	
	
	/**
	 * Gets the user by id.
	 * 
	 * @param id The id of the user.
	 * @return The user or null if no user could be found.
	 */
	public UserDVO getById(int id);
	
	/**
	 * Deletes the given user.
	 * 
	 * @param user The user to delete.
	 * @return True if the user was deleted successfully.
	 */
	public boolean delete(UserDVO person);
	
	/**
	 * Updates the given user.
	 * No check / validation will be performed.
	 * 
	 * @param user The user to update.
	 * @return True if the user was updated successfully.
	 */
	public boolean update(UserDVO person);
	
	/**
	 * Finds the user which matchs the given criteria.
	 * 
	 * @param user The user to find.
	 * @return The user or null if no match could be found.
	 */
	public UserDVO findPerson(UserDVO person);

	/**
	 * Gets the groups of the provided user id.
	 * @param id    The id of the user.
	 * @return      Groups of the user.
	 */
	public List<UserGroupDVO> getUserGroups(UserDVO person);
}
