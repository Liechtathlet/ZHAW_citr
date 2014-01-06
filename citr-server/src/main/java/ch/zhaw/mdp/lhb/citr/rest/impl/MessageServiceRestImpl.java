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
import ch.zhaw.mdp.lhb.citr.dto.MessageFactory;
import ch.zhaw.mdp.lhb.citr.gcm.GcmRequestHelper;
import ch.zhaw.mdp.lhb.citr.jpa.entity.GroupDVO;
import ch.zhaw.mdp.lhb.citr.jpa.entity.MessageDVO;
import ch.zhaw.mdp.lhb.citr.jpa.entity.SubscriptionDVO;
import ch.zhaw.mdp.lhb.citr.jpa.entity.UserDVO;
import ch.zhaw.mdp.lhb.citr.jpa.service.GroupRepository;
import ch.zhaw.mdp.lhb.citr.jpa.service.MessageRepository;
import ch.zhaw.mdp.lhb.citr.jpa.service.SubscriptionRepository;
import ch.zhaw.mdp.lhb.citr.jpa.service.UserRepository;
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
    private UserRepository userRepo;

    @Autowired
    private GcmRequestHelper gcmRequestHelper;
    
    @Autowired
    private ReloadableResourceBundleMessageSource messageSource;

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
	String msgKey = "msg.argument.invalid";

	if (aMessageDTO != null) {
	    UserDVO msgUser = userRepo.getById(aMessageDTO.getUserId());
	    if (aMessageDTO.getMessageText() == null || msgUser == null
		    || groupRepo.getById(aMessageDTO.getGroupId()) == null) {
		msgKey = "msg.msg.data.invalid";
	    } else {
		UserDVO currentUser = getCurrentUser();
		GroupDVO groupOfMessage = groupRepo.getById(aMessageDTO
			.getGroupId());

		// Checking permission of citr creation
		if (msgUser.getId() != getCurrentUser().getId()
			|| groupOfMessage.getOwner().getId() != currentUser
				.getId()) {
		    msgKey = "msg.no.permission";
		}

		MessageDVO messageDVO = MessageFactory
			.createMessageDVO(aMessageDTO);

		// Set citr send time (Do no't reset time fields, otherwise the sql sort will not work and not the newest message will be returned)
		Calendar today = Calendar.getInstance();
		messageDVO.setSendDate(today.getTime());

		//Save message and check response
		if (messageRepo.save(messageDVO) > 0) {
		    result = Boolean.TRUE;
		    msgKey = "msg.message.succ";
		    
		    // Fire Push-Notification (And forget)
		    List<String> recipients = new ArrayList<String>();

		    // Get approved subscriptions
		    List<SubscriptionDVO> subscriptionDVOs = subscriptionRepo
			    .getSubscriptionByGroup(groupOfMessage,
				    SubscriptionDVO.State.APPROVED);

		    if (subscriptionDVOs != null) {
			LOG.debug("Send push notification to: "
				+ subscriptionDVOs);

			for (SubscriptionDVO subscription : subscriptionDVOs) {
			    recipients.add(subscription.getUser()
				    .getRegistrationId());
			}

			try {
			    gcmRequestHelper.sendHTTPRequest(recipients);
			} catch (IOException e) {
			    LOG.error(e.toString());
			}
		    }
		}else{
		    msgKey = "msg.data.save.failed";
		}
	    }

	}
	
	return new ResponseObject<Boolean>(result, result.booleanValue(),
		messageSource.getMessage(msgKey, null, null));
    }

    /**
     * @return the current user.
     */
    private UserDVO getCurrentUser() {
	Authentication auth = SecurityContextHolder.getContext()
		.getAuthentication();
	return userRepo.getByOpenId(auth.getName());
    }
}