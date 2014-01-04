package ch.zhaw.mdp.lhb.citr.jpa.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data-Class for 'Group'.
 * @author Simon Lang
 */
@Entity
@Table(name = "tbl_group")
@NamedQueries({@NamedQuery(name = "Group.findAll", query = "SELECT g FROM GroupDVO g")})
public class GroupDVO {

	@Id
	@GeneratedValue
	private int id;

	private String name;

	@Enumerated(EnumType.STRING)
	private Mode mode;

	@OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
	private List<TagsDVO> tags;

	public enum Mode {
		PUBLIC, PRIVATE,
	}

	@Enumerated(EnumType.STRING)
	private State state;

	public enum State {
		ACTIVE, PASSIVE
	}

	@OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
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

	public void setTags(String tags) {
		String[] tagArray = tags.split(",");
		this.tags = new ArrayList<TagsDVO>();
		for (String tag : tagArray) {
			TagsDVO tagDVO = new TagsDVO();
			tagDVO.setTitle(tag.trim());
			tagDVO.setGroup(this);
			this.tags.add(tagDVO);
		}
	}

	public void setTags(List<TagsDVO> tags) {
		this.tags = tags;
	}

	public List<TagsDVO> getTags() {
		return tags;
	}
}
