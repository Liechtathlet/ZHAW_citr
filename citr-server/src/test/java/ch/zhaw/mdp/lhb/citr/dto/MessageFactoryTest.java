package ch.zhaw.mdp.lhb.citr.dto;

import ch.zhaw.mdp.lhb.citr.jpa.entity.MessageDVO;
import junit.framework.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Date;

public class MessageFactoryTest {

	@Test
	public void GotMessageDtoFromCreateMessageDTO() {
		String message = "Hello world";
		int userId = 1;
		int groupId = 2;

		final Date date = Mockito.mock(Date.class);
		Mockito.when(date.getTime()).thenReturn(30L);

		MessageDVO messageDVO = new MessageDVO();
		messageDVO.setMessage(message);
		messageDVO.setSendDate(date);
		messageDVO.setUserId(userId);
		messageDVO.setGroupId(groupId);
		messageDVO.setId(3);

		MessageDTO messageDTO = MessageFactory.createMessageDTO(messageDVO);

		Assert.assertEquals(message, messageDTO.getMessageText());
		Assert.assertEquals(groupId, messageDTO.getGroupId());
		Assert.assertEquals(userId, messageDTO.getUserId());
	}
}
