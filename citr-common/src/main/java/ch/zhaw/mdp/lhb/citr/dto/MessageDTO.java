/**
 * 
 */
package ch.zhaw.mdp.lhb.citr.dto;


/**
 * @author Daniel Brun
 * @author Simon Lang
 *
 * DTO-Class for 'Message'.
 */
public class MessageDTO {

	private int groupId;
	private int userId;
	private String messageText;

	public void setGroupId(int aGroupId) {
		groupId = aGroupId;
	}

	public void setMessageText(String aMessageText) {
		messageText = aMessageText;
	}

	public int getGroupId() {
		return groupId;
	}

	public String getMessageText() {
		return messageText;
	}

	@Override
	public int hashCode() {
		int hashCode = 17;
		
		hashCode += 37 * hashCode + groupId;
		hashCode += 37 * hashCode + messageText.hashCode();
		
		return hashCode;
	}
	
	@Override
	public boolean equals(Object anObj) {
		if(anObj == this){
			return true;
		}
		
		if(anObj == null || !(anObj instanceof MessageDTO)){
			return false;
		}

		MessageDTO message = (MessageDTO) anObj;
		
		return (groupId == message.getGroupId() &&
				(messageText == null ? message.getMessageText() == null : messageText.equals(message.getMessageText())));
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}
