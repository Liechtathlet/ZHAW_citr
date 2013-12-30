package ch.zhaw.mdp.lhb.citr.dto;

import java.util.ArrayList;
import java.util.List;

import ch.zhaw.mdp.lhb.citr.enumeration.GroupStateEnum;
import ch.zhaw.mdp.lhb.citr.jpa.entity.GroupDVO;
import ch.zhaw.mdp.lhb.citr.jpa.entity.SubscriptionDVO;

public class GroupFactory {

        public static List<GroupDTO> createGroups(List<GroupDVO> groupDVOs) {
                List<GroupDTO> groups = new ArrayList<GroupDTO>();

                for (GroupDVO groupDVO : groupDVOs) {
                        groups.add(createGroupDTO(groupDVO));
                }

                return groups;
        }

        public static List<GroupDTO> createGroupsFromSubscriptions(List<SubscriptionDVO> subscriptionDVOs) {
                List<GroupDTO> groups = new ArrayList<GroupDTO>();

                for (SubscriptionDVO subscriptionDVO : subscriptionDVOs) {
                        GroupDVO groupDVO = subscriptionDVO.getGroup();
                        GroupDTO groupDTO = new GroupDTO();
                        groupDTO.setName(groupDVO.getName());
                        groupDTO.setPublicGroup(groupDVO.getMode() == GroupDVO.Mode.PUBLIC);
                        groupDTO.setState(groupDVO.getState() == GroupDVO.State.ACTIVE ? GroupStateEnum.ACTIVE : GroupStateEnum.PASSIVE);
                        groupDTO.setId(groupDVO.getId());
                        groups.add(groupDTO);

                }

                return groups;
        }

        public static GroupDTO createGroupDTO(GroupDVO groupDVO) {
                GroupDTO dto = new GroupDTO();
                dto.setName(groupDVO.getName());
                dto.setPublicGroup(groupDVO.getMode() == GroupDVO.Mode.PUBLIC);
	            dto.setState(groupDVO.getState() == GroupDVO.State.ACTIVE ? GroupStateEnum.ACTIVE : GroupStateEnum.PASSIVE);
                dto.setId(groupDVO.getId());
                return dto;
        }
}