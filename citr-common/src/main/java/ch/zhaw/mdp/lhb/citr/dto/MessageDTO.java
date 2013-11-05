/**
 * 
 */
package ch.zhaw.mdp.lhb.citr.dto;


/**
 * @author Daniel Brun
 *
 * DTO-Class for 'Message'.
 */
public class MessageDTO {

	private String groupId;
	private String messageText;

	/**
	 * @param aGroupId the groupId to set
	 */
	public void setGroupId(String aGroupId) {
		groupId = aGroupId;
	}

	/**
	 * @param aMessageText the messageText to set
	 */
	public void setMessageText(String aMessageText) {
		messageText = aMessageText;
	}

	/**
	 * @return the groupId
	 */
	public String getGroupId() {
		return groupId;
	}

	/**
	 * @return the messageText
	 */
	public String getMessageText() {
		return messageText;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int hashCode = 17;
		
		hashCode += 37 * hashCode + groupId.hashCode();
		hashCode += 37 * hashCode + messageText.hashCode();
		
		return hashCode;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object anObj) {
		if(anObj == this){
			return true;
		}
		
		if(anObj == null || !(anObj instanceof MessageDTO)){
			return false;
		}

		MessageDTO message = (MessageDTO) anObj;
		
		return((groupId == null ? message.getGroupId()== null : groupId.equals(message.getGroupId())) &&
				(messageText == null ? message.getMessageText() == null : messageText.equals(message.getMessageText())));
	}
}
