/**
 * 
 */
package ch.zhaw.mdp.lhb.citr.rest;

import ch.zhaw.mdp.lhb.citr.dto.GroupDTO;
import ch.zhaw.mdp.lhb.citr.dto.UserDTO;
import ch.zhaw.mdp.lhb.citr.response.ResponseObject;

import java.util.List;

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
	public ResponseObject<Boolean> registerUser(UserDTO aUser);

	/**
	 * Performs the login of the given user and returns the user details.
	 * 
	 * @param aOpenId The openId
	 * @return The user or null if no corresponding user could be found.
	 */
	public ResponseObject<UserDTO> loginUser(String aOpenId);

	/**
	 * Gets all groups of the current user.
	 *
	 * @return List of groups of the user.
	 */
	public ResponseObject<List<GroupDTO>> getGroups();
}
