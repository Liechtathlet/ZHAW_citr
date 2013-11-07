/**
 * 
 */
package ch.zhaw.mdp.lhb.citr.rest;

import ch.zhaw.mdp.lhb.citr.dto.GroupDTO;
import ch.zhaw.mdp.lhb.citr.dto.UserDTO;

import java.util.ArrayList;

/**
 * @author Daniel Brun
 *
 * Interface for the User-Rest-Services
 */
public interface IRUserServices {

	/**
	 * Registers the given user.
	 * 
	 * @param aUser The user to register
	 * @return True if the registration was successfull, false otherwise.
	 */
	public boolean registerUser(UserDTO aUser);

	/**
	 * Gets the details of the user.
	 * 
	 * @param aOpenId The openId
	 * @return The user or null if no corresponding user could be found.
	 */
	public UserDTO getUser(String aOpenId);


	/**
	 * Gets all groups of the current user.
	 *
	 * @return List of groups of the user.
	 */
	public ArrayList<GroupDTO> getGroups();
}
