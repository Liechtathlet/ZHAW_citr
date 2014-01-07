package ch.zhaw.mdp.lhb.citr.dto;

import ch.zhaw.mdp.lhb.citr.jpa.entity.MessageDVO;
import junit.framework.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageFactoryTest {

	@Test
	public void GotNullFromCreateMessageDTO() {
		Assert.assertNull(MessageFactory.createMessageDTO(null));
	}

	@Test
	public void GotMessageDtoFromCreateMessageDTO() {
		String message = "Hello world";
		int userId = 1;
		int groupId = 2;

		final Date date = Mockito.mock(Date.class);
		Mockito.when(date.getTime()).thenReturn(30L);

		MessageDVO messageDVO = getMessageDVO(message, userId, groupId, date);

		MessageDTO messageDTO = MessageFactory.createMessageDTO(messageDVO);

		Assert.assertEquals(message, messageDTO.getMessageText());
		Assert.assertEquals(groupId, messageDTO.getGroupId());
		Assert.assertEquals(userId, messageDTO.getUserId());
	}

	private MessageDVO getMessageDVO(String message, int userId, int groupId, Date date) {
		MessageDVO messageDVO = new MessageDVO();
		messageDVO.setMessage(message);
		messageDVO.setSendDate(date);
		messageDVO.setUserId(userId);
		messageDVO.setGroupId(groupId);
		messageDVO.setId(3);
		return messageDVO;
	}

	@Test
	public void GotNullFromCreateMessageDVO() {
		Assert.assertNull(MessageFactory.createMessageDVO(null));
	}

	@Test
	public void GotMessageDvoFromCreateMessageDVO() {
		String message = "Hello world";
		int userId = 1;
		int groupId = 2;

		final Date date = Mockito.mock(Date.class);
		Mockito.when(date.getTime()).thenReturn(30L);

		MessageDTO messageDTO = new MessageDTO();
		messageDTO.setUserId(userId);
		messageDTO.setGroupId(groupId);
		messageDTO.setMessageText(message);

		MessageDVO messageDVO = MessageFactory.createMessageDVO(messageDTO);

		Assert.assertEquals(message, messageDVO.getMessage());
		Assert.assertEquals(groupId, messageDVO.getGroupId());
		Assert.assertEquals(userId, messageDVO.getUserId());
	}

	@Test
	public void GotEmptyListFromCreateMessageDTOsForNull() {
		Assert.assertEquals(0, MessageFactory.createMessageDTOs(null).size());
	}

	@Test
	public void GotEmptyListFromCreateMessageDTOsForEmptyList() {
		ArrayList<MessageDVO> messageDVOs = new ArrayList<MessageDVO>();
		Assert.assertEquals(0, MessageFactory.createMessageDTOs(messageDVOs).size());
	}

	@Test
	public void GotMessageDtosFromCreateMessageDTOs() {
		String message1 = "Hello world";
		int userId1 = 1;
		int groupId1 = 2;
		String message2 = "Hello world2";
		int userId2 = 2;
		int groupId2 = 5;

		final Date date = Mockito.mock(Date.class);
		Mockito.when(date.getTime()).thenReturn(30L);

		List<MessageDVO> messageDVOs = new ArrayList<MessageDVO>();
		messageDVOs.add(getMessageDVO(message1, userId1, groupId1, date));
		messageDVOs.add(getMessageDVO(message2, userId2, groupId2, date));

		List<MessageDTO> messageDTOs = MessageFactory.createMessageDTOs(messageDVOs);

		Assert.assertEquals(2, messageDTOs.size());
		Assert.assertEquals(message1, messageDTOs.get(0).getMessageText());
		Assert.assertEquals(groupId1, messageDTOs.get(0).getGroupId());
		Assert.assertEquals(userId1, messageDTOs.get(0).getUserId());
		Assert.assertEquals(message2, messageDTOs.get(1).getMessageText());
		Assert.assertEquals(groupId2, messageDTOs.get(1).getGroupId());
		Assert.assertEquals(userId2 , messageDTOs.get(1).getUserId());
	}
}
