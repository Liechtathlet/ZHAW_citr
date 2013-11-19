package ch.zhaw.mdp.lhb.citr.dto;

import ch.zhaw.mdp.lhb.citr.jpa.entity.GroupDVO;
import ch.zhaw.mdp.lhb.citr.response.ResponseObject;
import ch.zhaw.mdp.lhb.citr.rest.impl.GroupServiceRestImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.List;

public class GroupFactory {

	public static List<GroupDTO> createGroups(List<GroupDVO> groupDVOs) {
		Logger LOG = LoggerFactory.getLogger(GroupFactory.class);
		LOG.info("Test4...");
		List<GroupDTO> groups = new ArrayList<GroupDTO>();
		LOG.info("Test5...");

		//TODO: Mode evtl. als boolean?
		for (GroupDVO dvo : groupDVOs) {
			LOG.info("Test6...");
			GroupDTO dto = new GroupDTO();
			dto.setName(dvo.getName());
			dto.setPublicGroup(dvo.getMode() == "public");
			groups.add(dto);
		}

		return groups;
	}
}