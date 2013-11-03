package ch.zhaw.mdp.lhb.citr.jpa.entity;

import javax.persistence.*;

/**
 * @author Daniel Brun
 *
 * Data-Class for 'User'.
 */
@Entity
@Table(name = "tbl_user")
@NamedQueries( { @NamedQuery(name = "User.findAll", query = "SELECT p FROM UserDVO p"),
		@NamedQuery(name = "User.findUser", query = "SELECT p FROM UserDVO p where p.openId = :openId or p.username = :username")
	})
public class UserDVO {
	
	@Id
	@GeneratedValue
	private int id;
	
	private String openId;
	private String username;
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param aId the id to set
	 */
	public void setId(int aId) {
		id = aId;
	}
	/**
	 * @return the openId
	 */
	public String getOpenId() {
		return openId;
	}
	/**
	 * @param aOpenId the openId to set
	 */
	public void setOpenId(String aOpenId) {
		openId = aOpenId;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param aUsername the username to set
	 */
	public void setUsername(String aUsername) {
		username = aUsername;
	}
	
}
