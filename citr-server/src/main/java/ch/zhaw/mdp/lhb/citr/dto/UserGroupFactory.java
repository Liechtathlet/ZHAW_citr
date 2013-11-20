package ch.zhaw.mdp.lhb.citr.dto;

import ch.zhaw.mdp.lhb.citr.jpa.entity.UserGroupDVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides methods to create DTOs from DVO and vice versa.
 *
 * @author Simon Lang
 */
public class UserGroupFactory {
	public static List<SubscriptionDTO> createSubscriptionDTOs(List<UserGroupDVO> userGroupDTOs) {
		List<SubscriptionDTO> subscriptionDTOs = new ArrayList<SubscriptionDTO>();
		for (UserGroupDVO userGroupDVO : userGroupDTOs) {
			SubscriptionDTO subscriptionDTO = new SubscriptionDTO();
			subscriptionDTO.setUser(UserFactory.createUserDTO(userGroupDVO.getUser()));
			subscriptionDTO.setGroup(GroupFactory.createGroupDTO(userGroupDVO.getGroup()));
			subscriptionDTO.setState(userGroupDVO.getState() == "approved" ?
										SubscriptionDTO.State.approved :
										SubscriptionDTO.State.open);
			subscriptionDTOs.add(subscriptionDTO);
		}

		return subscriptionDTOs;
	}
}
