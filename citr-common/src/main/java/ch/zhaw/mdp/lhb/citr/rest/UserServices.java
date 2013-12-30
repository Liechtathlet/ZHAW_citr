/**
 * 
 */
package ch.zhaw.mdp.lhb.citr.rest;

import ch.zhaw.mdp.lhb.citr.dto.UserDTO;
import ch.zhaw.mdp.lhb.citr.response.ResponseObject;

/**
 * @author Daniel Brun
 *
 * Interface for the User-Rest-Services
 */
public interface UserServices {

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
	 * @param aRegistrationId The registration id.
	 * @return The user or null if no corresponding user could be found.
	 */
	public ResponseObject<UserDTO> loginUser(String aOpenId, String aRegistrationId);
}
