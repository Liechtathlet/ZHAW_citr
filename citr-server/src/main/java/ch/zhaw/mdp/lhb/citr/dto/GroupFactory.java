package ch.zhaw.mdp.lhb.citr.dto;

import ch.zhaw.mdp.lhb.citr.jpa.entity.GroupDVO;
import ch.zhaw.mdp.lhb.citr.jpa.entity.SubscriptionDVO;

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

        public static List<GroupDTO> createGroupsFromSubscriptions(List<SubscriptionDVO> subscriptionDVOs) {
                List<GroupDTO> groups = new ArrayList<GroupDTO>();

                for (SubscriptionDVO subscriptionDVO : subscriptionDVOs) {
                        GroupDVO groupDVO = subscriptionDVO.getGroup();
                        GroupDTO groupDTO = new GroupDTO();
                        groupDTO.setName(groupDVO.getName());
                        groupDTO.setPublicGroup(groupDVO.getMode() == GroupDVO.Mode.PUBLIC);
                        groupDTO.setState(groupDVO.getState() == GroupDVO.State.ACTIVE ? GroupDTO.State.ACTIVE : GroupDTO.State.PASSIVE);
                        groups.add(groupDTO);
                }

                return groups;
        }

        public static GroupDTO createGroupDTO(GroupDVO groupDVO) {
                GroupDTO dto = new GroupDTO();
                dto.setName(groupDVO.getName());
                dto.setPublicGroup(groupDVO.getMode() == GroupDVO.Mode.PUBLIC);
	            dto.setState(groupDVO.getState() == GroupDVO.State.ACTIVE ? GroupDTO.State.ACTIVE : GroupDTO.State.PASSIVE);
                return dto;
        }
}