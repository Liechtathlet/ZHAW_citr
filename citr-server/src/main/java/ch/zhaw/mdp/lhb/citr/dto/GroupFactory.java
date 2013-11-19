package ch.zhaw.mdp.lhb.citr.dto;

import java.util.ArrayList;
import java.util.List;

import ch.zhaw.mdp.lhb.citr.jpa.entity.GroupDVO;

public class GroupFactory {

    public static List<GroupDTO> createGroups(List<GroupDVO> groupDVOs) {
	List<GroupDTO> groups = new ArrayList<GroupDTO>();

	// TODO: Mode evtl. als boolean?
	if (groupDVOs != null) {
	    for (GroupDVO dvo : groupDVOs) {
		GroupDTO dto = new GroupDTO();
		dto.setName(dvo.getName());
		dto.setPublicGroup(dvo.getMode() == "public");
		groups.add(dto);
	    }
	}

	return groups;
    }
}