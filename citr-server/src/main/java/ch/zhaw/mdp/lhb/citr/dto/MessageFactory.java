package ch.zhaw.mdp.lhb.citr.dto;

import java.util.ArrayList;
import java.util.List;

import ch.zhaw.mdp.lhb.citr.jpa.entity.MessageDVO;

public class MessageFactory {

	public static MessageDTO createMessageDTO(MessageDVO messageDVO) {
		MessageDTO messageDTO = null;

		if (messageDVO != null) {
			messageDTO = new MessageDTO();
			messageDTO.setGroupId(messageDVO.getGroupId());
			messageDTO.setMessageText(messageDVO.getMessage());
			messageDTO.setUserId(messageDVO.getUserId());
		}

		return messageDTO;
	}

	public static MessageDVO createMessageDVO(MessageDTO aMessageDTO) {
		MessageDVO messageDVO = null;

		if (aMessageDTO != null) {
			messageDVO = new MessageDVO();
			messageDVO.setGroupId(aMessageDTO.getGroupId());
			messageDVO.setMessage(aMessageDTO.getMessageText());
			messageDVO.setUserId(aMessageDTO.getUserId());
		}
		return messageDVO;
	}

	public static List<MessageDTO> createMessageDTOs(
			List<MessageDVO> messageDVOs) {
		List<MessageDTO> messageDTOs = new ArrayList<MessageDTO>();

		if (messageDVOs != null) {
			for (MessageDVO messageDVO : messageDVOs) {
				messageDTOs.add(createMessageDTO(messageDVO));
			}
		}

		return messageDTOs;
	}
}