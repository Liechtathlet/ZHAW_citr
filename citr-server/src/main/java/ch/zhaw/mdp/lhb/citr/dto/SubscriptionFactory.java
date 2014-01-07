package ch.zhaw.mdp.lhb.citr.dto;

import java.util.ArrayList;
import java.util.List;

import ch.zhaw.mdp.lhb.citr.enumeration.SubscriptionStateEnum;
import ch.zhaw.mdp.lhb.citr.jpa.entity.SubscriptionDVO;

/**
 * Provides methods to create DTOs from DVO and vice versa.
 * 
 * @author Simon Lang
 */
public class SubscriptionFactory {

    public static List<SubscriptionDTO> createSubscriptionDTOs(
	    List<SubscriptionDVO> subscriptionDVOs) {
	List<SubscriptionDTO> subscriptionDTOs = null;
	
	if (subscriptionDVOs != null) {
	    subscriptionDTOs = new ArrayList<SubscriptionDTO>();
	    
	    for (SubscriptionDVO subscriptionDVO : subscriptionDVOs) {
		SubscriptionDTO subscriptionDTO = new SubscriptionDTO();
		subscriptionDTO.setUser(UserFactory
			.createUserDTO(subscriptionDVO.getUser()));
		subscriptionDTO.setGroup(GroupFactory
			.createGroupDTO(subscriptionDVO.getGroup()));
		subscriptionDTO
			.setState(subscriptionDVO.getState() == SubscriptionDVO.State.APPROVED ? SubscriptionStateEnum.APPROVED
				: SubscriptionStateEnum.OPEN);
		subscriptionDTOs.add(subscriptionDTO);
	    }

	}
	return subscriptionDTOs;
    }
}
