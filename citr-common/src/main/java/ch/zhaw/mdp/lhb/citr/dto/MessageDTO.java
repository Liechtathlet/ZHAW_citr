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

	/**
	 * @return the groupId
	 */
	public int getGroupId() {
	    return groupId;
	}

	/**
	 * @param aGroupId the groupId to set
	 */
	public void setGroupId(int aGroupId) {
	    groupId = aGroupId;
	}

	/**
	 * @return the messageText
	 */
	public String getMessageText() {
	    return messageText;
	}

	/**
	 * @param aMessageText the messageText to set
	 */
	public void setMessageText(String aMessageText) {
	    messageText = aMessageText;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
	    return userId;
	}

	/**
	 * @param aUserId the userId to set
	 */
	public void setUserId(int aUserId) {
	    userId = aUserId;
	}

	@Override
	public int hashCode() {
		int hashCode = 17;
		
		hashCode += 37 * hashCode + groupId;
		hashCode += 37 * hashCode + userId;
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
		
		return (groupId == message.groupId && userId == message.userId &&
				(messageText == null ? message.getMessageText() == null : messageText.equals(message.getMessageText())));
	}
}
