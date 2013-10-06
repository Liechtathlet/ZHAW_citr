/**
 * 
 */
package ch.zhaw.lhb.citr.dto;

import java.util.Date;

/**
 * @author Daniel Brun
 *
 * DTO-Class for 'Message'.
 */
public class MessageDTO {

	private long id;
	private Date sendDate;
	private String message;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
