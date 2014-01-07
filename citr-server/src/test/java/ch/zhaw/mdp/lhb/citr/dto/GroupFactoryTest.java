package ch.zhaw.mdp.lhb.citr.dto;

import ch.zhaw.mdp.lhb.citr.enumeration.GroupStateEnum;
import ch.zhaw.mdp.lhb.citr.jpa.entity.GroupDVO;
import junit.framework.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GroupFactoryTest {

	@Test
	public void GotNullListFromCreateGroups() {
		Assert.assertNull(GroupFactory.createGroups(null));
	}

	@Test
	public void GotEmptyListFromCreateGroups() {
		List<GroupDVO> groupDVOs = new ArrayList<GroupDVO>();
		List<GroupDTO> groupDTOs = GroupFactory.createGroups(groupDVOs);
		Assert.assertEquals(0, groupDTOs.size());
	}

	@Test
	public void GotTwoGroupDTOsFromCreateGroups() {
		List<GroupDVO> groupDVOs = new ArrayList<GroupDVO>();
		groupDVOs.add(getGroupDVO(1, "", null, GroupDVO.State.ACTIVE, null));
		groupDVOs.add(getGroupDVO(2, "", null, GroupDVO.State.ACTIVE, null));

		List<GroupDTO> groupDTOs = GroupFactory.createGroups(groupDVOs);
		Assert.assertEquals(2, groupDTOs.size());
	}

	@Test
	public void GotNullFromCreateGroupDTO() {
		Assert.assertNull(GroupFactory.createGroupDTO(null));
	}

	@Test
	public void GotGroupDTOFromCreateGroupDTO() {
		int id = 1;
		String name = "name";
		String tags = "tag1, tag2";
		GroupDTO groupDTO = GroupFactory.createGroupDTO(getGroupDVO(
				id,
				name,
				tags,
				GroupDVO.State.ACTIVE,
				GroupDVO.Mode.PUBLIC
		));

		Assert.assertEquals(id, groupDTO.getId());
		Assert.assertEquals(name, groupDTO.getName());
		Assert.assertEquals(tags, groupDTO.getTags());
		Assert.assertEquals(GroupStateEnum.ACTIVE, groupDTO.getState());
		Assert.assertTrue(groupDTO.isPublicGroup());
	}

	private GroupDVO getGroupDVO(int id, String name, String tags, GroupDVO.State state, GroupDVO.Mode mode) {
		GroupDVO groupDVO = new GroupDVO();
		groupDVO.setId(id);
		groupDVO.setName(name);
		groupDVO.setTags(tags);
		groupDVO.setState(state);
		groupDVO.setMode(mode);
		return groupDVO;
	}
}
