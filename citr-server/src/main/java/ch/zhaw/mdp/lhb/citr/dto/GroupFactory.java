package ch.zhaw.mdp.lhb.citr.dto;

import ch.zhaw.mdp.lhb.citr.jpa.entity.GroupDVO;
import ch.zhaw.mdp.lhb.citr.jpa.entity.SubscriptionDVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class GroupFactory {

	public static List<GroupDTO> createGroups(List<GroupDVO> groupDVOs) {
		List<GroupDTO> groups = new ArrayList<GroupDTO>();

		//TODO: Mode evtl. als boolean?
		for (GroupDVO groupDVO : groupDVOs) {
			groups.add(createGroupDTO(groupDVO));
		}

		return groups;
	}

	public static List<GroupDTO> createSubscriptions(List<SubscriptionDVO> subscriptionDVOs) {
		List<GroupDTO> groups = new ArrayList<GroupDTO>();
		Logger LOG = LoggerFactory.getLogger(GroupFactory.class);

		for (SubscriptionDVO subscriptionDVO : subscriptionDVOs) {
			GroupDVO groupDVO = subscriptionDVO.getGroup();
			GroupDTO dto = new GroupDTO();
			dto.setName(groupDVO.getName());
			dto.setPublicGroup(groupDVO.getMode() == "public");
			dto.setState(subscriptionDVO.getState() == "approved" ? GroupDTO.State.approved : GroupDTO.State.open);
			groups.add(dto);
		}

		return groups;
	}

	public static GroupDTO createGroupDTO(GroupDVO groupDVO) {
		GroupDTO dto = new GroupDTO();
		dto.setName(groupDVO.getName());
		dto.setPublicGroup(groupDVO.getMode() == "public");
		return dto;
	}
}