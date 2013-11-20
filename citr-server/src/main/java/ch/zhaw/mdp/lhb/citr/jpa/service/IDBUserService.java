package ch.zhaw.mdp.lhb.citr.jpa.service;

import ch.zhaw.mdp.lhb.citr.jpa.entity.UserDVO;
import ch.zhaw.mdp.lhb.citr.jpa.entity.SubscriptionDVO;

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
	 * @param person The user to save.
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
	 * Gets the user by openId.
	 *
	 * @param openId The openId of the user.
	 * @return The user or null if no user could be found.
	 */
	public UserDVO getByOpenId(String openId);
	
	/**
	 * Deletes the given user.
	 * 
	 * @param person The user to delete.
	 * @return True if the user was deleted successfully.
	 */
	public boolean delete(UserDVO person);
	
	/**
	 * Updates the given user.
	 * No check / validation will be performed.
	 * 
	 * @param person The user to update.
	 * @return True if the user was updated successfully.
	 */
	public boolean update(UserDVO person);
	
	/**
	 * Finds the user which matchs the given criteria.
	 * 
	 * @param person The user to find.
	 * @return The user or null if no match could be found.
	 */
	public UserDVO findPerson(UserDVO person);

	/**
	 * Gets the groups of the provided user id.
	 * @param person The user to get the groups from.
	 * @return      Groups of the user.
	 */
	public List<SubscriptionDVO> getsubscriptions(UserDVO person);
}
