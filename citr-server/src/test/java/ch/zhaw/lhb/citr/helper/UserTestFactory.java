/**
 * 
 */
package ch.zhaw.lhb.citr.helper;

import ch.zhaw.mdp.lhb.citr.dto.UserDTO;
import ch.zhaw.mdp.lhb.citr.jpa.entity.UserDVO;

/**
 * @author Daniel Brun
 * 
 *         Test-Factory for {@link UserDVO} and {@link UserDTO} related objects and structures.
 */
public class UserTestFactory {

    /**
     * Creates a new user based on the given data.
     * 
     * @param anId The id.
     * @param aUsername The username.
     * @param anOpenId The openId.
     * @return The {@linnk UserDVO}.
     */
    public static UserDVO createUserDVO(int anId, String aUsername,
	    String anOpenId) {
	UserDVO user = new UserDVO();
	user.setOpenId(anOpenId);
	user.setUsername(aUsername);
	user.setId(anId);

	return user;
    }

    /**
     * Creates a new user based on the given data.
     * 
     * @param aUsername The username.
     * @param anOpenId The openId.
     * @return The {@linnk UserDTO}.
     */
    public static UserDTO createUserDTO(String aUsername, String anOpenId) {
	UserDTO user = new UserDTO();
	user.setOpenId(anOpenId);
	user.setUsername(aUsername);

	return user;
    }

}
