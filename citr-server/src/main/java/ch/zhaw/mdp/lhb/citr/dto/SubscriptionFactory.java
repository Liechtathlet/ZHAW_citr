package ch.zhaw.mdp.lhb.citr.dto;

import ch.zhaw.mdp.lhb.citr.jpa.entity.SubscriptionDVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides methods to create DTOs from DVO and vice versa.
 *
 * @author Simon Lang
 */
public class SubscriptionFactory {
	public static List<SubscriptionDTO> createSubscriptionDTOs(List<SubscriptionDVO> subscriptionDVOs) {
		List<SubscriptionDTO> subscriptionDTOs = new ArrayList<SubscriptionDTO>();
		for (SubscriptionDVO subscriptionDVO : subscriptionDVOs) {
			SubscriptionDTO subscriptionDTO = new SubscriptionDTO();
			subscriptionDTO.setUser(UserFactory.createUserDTO(subscriptionDVO.getUser()));
			subscriptionDTO.setGroup(GroupFactory.createGroupDTO(subscriptionDVO.getGroup()));
			subscriptionDTO.setState(subscriptionDVO.getState() == "approved" ?
										SubscriptionDTO.State.approved :
										SubscriptionDTO.State.open);
			subscriptionDTOs.add(subscriptionDTO);
		}

		return subscriptionDTOs;
	}
}
