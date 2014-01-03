package ch.zhaw.mdp.lhb.citr.dto;

import ch.zhaw.mdp.lhb.citr.jpa.entity.MessageDVO;

import java.util.ArrayList;
import java.util.List;

public class MessageFactory {

        public static MessageDTO createMessageDTO(MessageDVO messageDVO) {
            MessageDTO messageDTO = new MessageDTO();
	        messageDTO.setGroupId(messageDVO.getGroupId());
	        messageDTO.setMessageText(messageDVO.getMessage());
	        messageDTO.setUserId(messageDVO.getUserId());
            return messageDTO;
        }

        public static List<MessageDTO> createMessageDTOs(List<MessageDVO> messageDVOs) {
	        List<MessageDTO> messageDTOs = new ArrayList<MessageDTO>();
	        for (MessageDVO messageDVO : messageDVOs) {
		        messageDTOs.add(createMessageDTO(messageDVO));
	        }

	        return messageDTOs;
        }
}