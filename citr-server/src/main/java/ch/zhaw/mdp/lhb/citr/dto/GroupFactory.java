package ch.zhaw.mdp.lhb.citr.dto;

import ch.zhaw.mdp.lhb.citr.jpa.entity.GroupDVO;
import ch.zhaw.mdp.lhb.citr.jpa.entity.UserGroupDVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class GroupFactory {

	public static List<GroupDTO> createGroups(List<GroupDVO> groupDVOs) {
		List<GroupDTO> groups = new ArrayList<GroupDTO>();

		//TODO: Mode evtl. als boolean?
		for (GroupDVO groupDVO : groupDVOs) {
			GroupDTO dto = new GroupDTO();
			dto.setName(groupDVO.getName());
			dto.setPublicGroup(groupDVO.getMode() == "public");
			groups.add(dto);
		}

		return groups;
	}

	public static List<GroupDTO> createUserGroups(List<UserGroupDVO> userGroupDVOs) {
		List<GroupDTO> groups = new ArrayList<GroupDTO>();
		Logger LOG = LoggerFactory.getLogger(GroupFactory.class);

		for (UserGroupDVO userGroupDVO : userGroupDVOs) {
			GroupDVO groupDVO = userGroupDVO.getGroup();
			GroupDTO dto = new GroupDTO();
			dto.setName(groupDVO.getName());
			dto.setPublicGroup(groupDVO.getMode() == "public");
			dto.setState(userGroupDVO.getState() == "approved" ? GroupDTO.State.approved : GroupDTO.State.open);
			groups.add(dto);
		}

		return groups;
	}
}