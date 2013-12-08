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

	@Enumerated(EnumType.STRING)
	private Mode mode;

	public enum Mode {
		PUBLIC,
		PRIVATE,
	}

	@Enumerated(EnumType.STRING)
    private State state;

	public enum State {
		ACTIVE,
		PASSIVE
	}

	@OneToMany(mappedBy="group")
	private List<SubscriptionDVO> subscriptions;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "owner_usr_id")
	private UserDVO owner;

    public GroupDVO() {
		// every new group is active
		this.setState(State.ACTIVE);
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

    public State getState() {
		return state;
    }

    public void setState(State aState) {
		state = aState;
    }

    public Mode getMode() {
		return mode;
    }

    public void setMode(Mode aMode) {
		mode = aMode;
    }

	public List<SubscriptionDVO> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(List<SubscriptionDVO> subscriptionDVOs) {
		this.subscriptions = subscriptionDVOs;
	}

	public UserDVO getOwner() {
		return owner;
	}

	public void setOwner(UserDVO owner) {
		this.owner = owner;
	}
}

