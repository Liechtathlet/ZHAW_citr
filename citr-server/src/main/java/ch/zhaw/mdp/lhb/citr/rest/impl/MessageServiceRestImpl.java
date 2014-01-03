/**
 *
 */
package ch.zhaw.mdp.lhb.citr.rest.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
import ch.zhaw.mdp.lhb.citr.dto.MessageDTO;
import ch.zhaw.mdp.lhb.citr.gcm.GcmRequestHelper;
import ch.zhaw.mdp.lhb.citr.jpa.entity.GroupDVO;
import ch.zhaw.mdp.lhb.citr.jpa.entity.MessageDVO;
import ch.zhaw.mdp.lhb.citr.jpa.entity.SubscriptionDVO;
import ch.zhaw.mdp.lhb.citr.jpa.service.GroupRepository;
import ch.zhaw.mdp.lhb.citr.jpa.service.MessageRepository;
import ch.zhaw.mdp.lhb.citr.jpa.service.SubscriptionRepository;
import ch.zhaw.mdp.lhb.citr.response.ResponseObject;
import ch.zhaw.mdp.lhb.citr.rest.MessageServices;

/**
 * @author Daniel Brun
 * @author Simon Lang
 *         <p/>
 *         Implementation of the Service-Interface {@link IRMessageServices}.
 */
@Component
@Path("/message")
@Scope("singleton")
public class MessageServiceRestImpl implements MessageServices {

	private static final LoggingStrategy LOG = LoggingFactory.get();

	@Autowired
	private MessageRepository messageRepo;

	@Autowired
	private GroupRepository groupRepo;

	@Autowired
	private SubscriptionRepository subscriptionRepo;

	@Autowired
	private ReloadableResourceBundleMessageSource messageSource;

	/**
	 * Creates a message.
	 *
	 * @param aMessageDTO Message to create.
	 * @return
	 */
	@POST
	@Override
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Secured("ROLE_USER")
	@Path("/create")
	public ResponseObject<Boolean> createMessage(MessageDTO aMessageDTO) {
		if (aMessageDTO == null) {
			throw new IllegalArgumentException(
					"The argument aMessageDTO must not be null");
		}

		Boolean result = Boolean.FALSE;
		String msg = "";

		// TODO: Get user credentials
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();

		// TODO: Check permission for group

		// TODO: Validate message
		MessageDVO messageDVO = new MessageDVO();
		messageDVO.setMessage(aMessageDTO.getMessageText());
		messageDVO.setGroupId(aMessageDTO.getGroupId());

		//FIXME: Sort doesn't work with this date
		Calendar today = Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 0);
		messageDVO.setSendDate(today.getTime());

		//TODO: evtl. Check if failed
		messageRepo.save(messageDVO);
		result = Boolean.TRUE;
		msg = messageSource.getMessage("msg.message.succ", null, null);
		// TODO: Implement different return texts.

		// Fire Push-Notification:
		//FIXME: GroupId
		List<String> recipients = new ArrayList<String>();
		GroupDVO group = groupRepo.getById(1);
		List<SubscriptionDVO> subscriptionDVOs = subscriptionRepo
				.getSubscriptionByGroup(group, SubscriptionDVO.State.APPROVED);
		if (subscriptionDVOs != null) {
			LOG.debug("Push notification: " + subscriptionDVOs);
			for (SubscriptionDVO subscription : subscriptionDVOs) {
				LOG.debug("Send push to: " + subscription.getUser().getUsername());
				recipients.add(subscription.getUser().getRegistrationId());
			}
			GcmRequestHelper helper = new GcmRequestHelper();
			try {
				helper.sendHTTPRequest(recipients);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return new ResponseObject<Boolean>(result, result.booleanValue(), msg);
	}
}