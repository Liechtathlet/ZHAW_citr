package ch.zhaw.mdp.lhb.citr.jpa.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Simon Lang
 *
 * Data-Class for 'User Group'.
 */
@Entity
@Table(name = "tbl_user_group")
public class UserGroupDVO implements Serializable {
	@Id
	@Column(name = "usr_id")
	private int userId;

	@Id
	@Column(name = "grp_id")
	private int groupId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns({@JoinColumn(name = "usr_id", insertable=false, updatable=false)})
	private UserDVO user;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns({@JoinColumn(name = "grp_id", insertable=false, updatable=false)})
	private GroupDVO group;

	@Column(name = "usg_state")
	private String state;


	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public UserDVO getUser() {
		return user;
	}

	public void setUser(UserDVO user) {
		this.user = user;
	}

	public GroupDVO getGroup() {
		return group;
	}

	public void setGroup(GroupDVO group) {
		this.group = group;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
