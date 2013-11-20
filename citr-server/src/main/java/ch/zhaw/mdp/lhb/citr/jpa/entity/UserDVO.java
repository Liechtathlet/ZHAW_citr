package ch.zhaw.mdp.lhb.citr.jpa.entity;

import java.io.Serializable;
import java.util.List;

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
public class UserDVO implements Serializable {
	
	@Id
	@GeneratedValue
	private int id;
	
	private String openId;
	private String username;
	//TODO: Remove password field if OAuth is implemented.
	private String password;

	@OneToMany(mappedBy="user")
	private List<SubscriptionDVO> subscription;

	@OneToMany(mappedBy="owner", fetch = FetchType.EAGER)
	private List<GroupDVO> createdGroups;

	/**
	 * Creates a new instance of this class.
	 */
	public UserDVO() {
	    //TODO: Remove assignment if OAuth is implemented.
	    password = "4890bb244647d632e141e9e445e92359204777f849b41dd334adeae9f47cb4060d444976c36e1ed44808cd245b47f5a18a5d5d29ba639846d084734cd7f1b30c";
	}
	
	/**
	 * Creates a new instance of this class.
	 * 
	 * @param aOpenId The openId
	 * @param aUsername the username
	 */
	public UserDVO(String aOpenId, String aUsername) {
	    super();
	    openId = aOpenId;
	    username = aUsername;
	}

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

	public List<SubscriptionDVO> getSubscriptions() {
		return subscription;
	}

	public void setSubscription(List<SubscriptionDVO> subscriptionDVOs) {
		this.subscription = subscriptionDVOs;
	}

	public List<GroupDVO> getCreatedGroups() {
		return createdGroups;
	}

	public void setCreatedGroups(List<GroupDVO> createdGroups) {
		this.createdGroups = createdGroups;
	}
}
