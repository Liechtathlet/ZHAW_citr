package ch.zhaw.mdp.lhb.citr.dto;

import ch.zhaw.mdp.lhb.citr.jpa.entity.MessageDVO;

public class MessageFactory {

        public static MessageDTO createMessageDTO(MessageDVO messageDVO) {
            MessageDTO messageDTO = new MessageDTO();
	        messageDTO.setGroupId(messageDVO.getGroupId());
	        messageDTO.setMessageText(messageDVO.getMessage());
	        messageDTO.setUserId(messageDVO.getUserId());
            return messageDTO;
        }
}