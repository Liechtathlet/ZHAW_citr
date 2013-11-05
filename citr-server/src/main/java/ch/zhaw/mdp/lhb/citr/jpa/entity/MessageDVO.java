/**
 * 
 */
package ch.zhaw.mdp.lhb.citr.jpa.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Daniel Brun
 * @author Simon Lang
 *
 * Entity-Class for 'Message'.
 */
@Entity
@Table(name = "tbl_citation")
public class MessageDVO {

	@Id
	@GeneratedValue
	@Column(name = "cit_id")
	private long id;

	@Column(name = "cit_date", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date sendDate;
	@Column(name = "cit_text")
	private String message;
	@Column(name = "grp_id")
	private int groupId;
	@Column(name = "usr_id")
	private int userId;

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

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}
