/**
 * 
 */
package ch.zhaw.mdp.lhb.citr.dto;

import ch.zhaw.mdp.lhb.citr.jpa.entity.UserDVO;

/**
 * Provides methods to create DTOs from DVOs and backwards.
 *
 * @author Daniel Brun
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

}
