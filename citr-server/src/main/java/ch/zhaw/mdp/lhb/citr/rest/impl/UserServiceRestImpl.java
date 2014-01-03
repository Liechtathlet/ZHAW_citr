/**
 *
 */
package ch.zhaw.mdp.lhb.citr.rest.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import ch.zhaw.mdp.lhb.citr.Logging.LoggingFactory;
import ch.zhaw.mdp.lhb.citr.Logging.LoggingStrategy;
import ch.zhaw.mdp.lhb.citr.dto.UserDTO;
import ch.zhaw.mdp.lhb.citr.dto.UserFactory;
import ch.zhaw.mdp.lhb.citr.jpa.entity.UserDVO;
import ch.zhaw.mdp.lhb.citr.jpa.service.UserRepository;
import ch.zhaw.mdp.lhb.citr.response.ResponseObject;
import ch.zhaw.mdp.lhb.citr.rest.UserServices;

/**
 * @author Daniel Brun
 *         <p/>
 *         Implementation of the Service-Interface {@link IRUserServices}.
 */
@Component
@Path("/user")
@Scope("singleton")
public class UserServiceRestImpl implements UserServices {

	private static final LoggingStrategy LOG = LoggingFactory.get();

	@Autowired
	private UserRepository userService;

	@Autowired
	private ReloadableResourceBundleMessageSource messageSource;

	/**
	 * Logs in the provided user.
	 *
	 * @param anOpenId
	 * @param aRegistrationId
	 * @return
	 */
	@GET
	@Override
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Secured("ROLE_USER")
	@Path("/{id}/login/{gcmId}")
	public ResponseObject<UserDTO> loginUser(@PathParam("id") String anOpenId,
	                                         @PathParam("gcmId") String aRegistrationId) {
		UserDTO resUser = null;
		boolean successfull = false;
		String msg;

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth != null && auth.getName().equals(anOpenId)) {
			UserDVO user = new UserDVO();
			user.setOpenId(anOpenId);

			user = userService.findPerson(user);

			resUser = UserFactory.createUserDTO(user);

			if (resUser == null) {
				LOG.info("Authentication for user: + " + anOpenId + " failed");
				msg = messageSource.getMessage("msg.user.auth.failed", null, null);
			} else {
				//Store registration id
				user.setRegistrationId(aRegistrationId);
				userService.update(user);

				LOG.info("Authentication for user: + " + anOpenId + " was successfull");
				msg = messageSource.getMessage("msg.user.auth.succ", new String[]{resUser.getUsername()}, null);
				successfull = true;
			}
		} else {
			msg = messageSource.getMessage("msg.user.noPermission", null, null);
		}

		return new ResponseObject<UserDTO>(resUser, successfull, msg);
	}

	/**
	 * Registers a provided user.
	 * @param aUser User data to register.
	 * @return
	 */
	@Override
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Secured("ROLE_USER")
	@Path("/register")
	public ResponseObject<Boolean> registerUser(UserDTO aUser) {
		UserDVO user = UserFactory.createUserDVO(aUser);
		Boolean result = Boolean.FALSE;
		String msg = "";

		if (user == null) {
			throw new IllegalArgumentException(
					"The argument aUser must not be null!");
		}

		if (userService.findPerson(user) == null) {
			if (aUser.getUsername() != null && aUser.getOpenId() != null) {
				if (userService.save(user)) {
					result = Boolean.TRUE;
				} else {
					msg = messageSource.getMessage("msg.user.create.fail", null, null);
				}
			} else {
				msg = messageSource.getMessage("msg.user.data.invalid", null, null);
			}
		} else {
			msg = messageSource.getMessage("msg.user.alreadyExists", null, null);
		}

		return new ResponseObject<Boolean>(result, result.booleanValue(), msg);
	}
}
