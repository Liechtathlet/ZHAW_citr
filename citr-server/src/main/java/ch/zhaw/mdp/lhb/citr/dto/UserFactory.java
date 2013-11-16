/**
 * 
 */
package ch.zhaw.mdp.lhb.citr.dto;

import ch.zhaw.mdp.lhb.citr.jpa.entity.UserDVO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author Daniel Brun
 * 
 *         Provides methods to create DTOs from DVOs and backwards.
 */
public class UserFactory {

	/**
	 * Converts a UserDVO to a UserDTO.
	 * 
	 * @param aUser
	 *            The user to convert.
	 * @return The converted user.
	 */
	public static UserDTO createUserDTO(UserDVO aUser) {
		UserDTO convUser = null;

		if (aUser != null) {
			convUser = new UserDTO();
			convUser.setOpenId(aUser.getOpenId());
			convUser.setUsername(aUser.getUsername());
		}

		return convUser;
	}

	/**
	 * Converts a UserDTO to a UserDVO.
	 * 
	 * @param aUser
	 *            The user to convert.
	 * @return The converted user.
	 */
	public static UserDVO createUserDVO(UserDTO aUser) {
		UserDVO convUser = null;

		if (aUser != null) {
			convUser = new UserDVO();
			convUser.setOpenId(aUser.getOpenId());
			convUser.setUsername(aUser.getUsername());
		}

		return convUser;
	}

	/**
	 * Gets the currently logged in user.
	 *
	 * @return The logged in user.
	 */
	public static UserDVO getLoggedInUser() {
		UserDVO userDVO = new UserDVO();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		userDVO.setUsername(auth.getName());
		return userDVO;
	}
}
