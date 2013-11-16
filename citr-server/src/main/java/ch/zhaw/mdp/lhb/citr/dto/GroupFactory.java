package ch.zhaw.mdp.lhb.citr.dto;

import ch.zhaw.mdp.lhb.citr.jpa.entity.GroupDVO;
import ch.zhaw.mdp.lhb.citr.response.ResponseObject;
import ch.zhaw.mdp.lhb.citr.rest.impl.GroupServiceRestImpl;
import org.springframework.security.access.annotation.Secured;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.List;

public class GroupFactory {

	public static List<GroupDTO> createGroups(List<GroupDVO> groupDVOs) {
		List<GroupDTO> groups = new ArrayList<GroupDTO>();

		//TODO: Mode evtl. als boolean?
		for (GroupDVO dvo : groupDVOs) {
			GroupDTO dto = new GroupDTO();
			dto.setName(dvo.getName());
			dto.setPublicGroup(dvo.getMode() == "public");
			groups.add(dto);
		}

		return groups;
	}
}