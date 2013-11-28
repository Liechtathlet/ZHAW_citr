package ch.zhaw.mdp.lhb.citr.jpa.entity;

import javax.persistence.*;
import java.util.List;

/**
 * @author Simon Lang
 * 
 *         Data-Class for 'Group'.
 */
@Entity
@Table(name = "tbl_group")
@NamedQueries({ @NamedQuery(name = "Group.findAll", query = "SELECT g FROM GroupDVO g") })
public class GroupDVO {

    @Id
    @GeneratedValue
    private int id;

    private String name;
    private String state;
    private String mode;

	@OneToMany(mappedBy="group")
	private List<SubscriptionDVO> subscriptions;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "owner_usr_id")
	private UserDVO owner;

    public GroupDVO() {
	// every new group is active
	this.setState("active");
    }

    public int getId() {
	return id;
    }

    public void setId(int aId) {
	id = aId;
    }

    public String getName() {
	return name;
    }

    public void setName(String aName) {
	name = aName;
    }

    public String getState() {
	return state;
    }

    public void setState(String aState) {
	state = aState;
    }

    public String getMode() {
	return mode;
    }

    public void setMode(String aMode) {
	mode = aMode;
    }

	public List<SubscriptionDVO> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(List<SubscriptionDVO> subscriptionDVOs) {
		this.subscriptions = subscriptionDVOs;
	}
}
