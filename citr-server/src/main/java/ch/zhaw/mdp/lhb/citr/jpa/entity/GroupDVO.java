package ch.zhaw.mdp.lhb.citr.jpa.entity;

import javax.persistence.*;

@Entity
@Table(name = "tbl_group")
@NamedQueries( { @NamedQuery(name = "Group.findAll", query = "SELECT g FROM GroupDVO g")
})
public class GroupDVO {

	private int id;
	private String name;
	private int state;
	private int mode;

	@Id
	@Column(name="grp_id")
	public int getId() {
		return id;
	}

	@Column(name="grp_id")
	public void setId(int id) {
		this.id = id;
	}

	@Column(name="grp_name")
	public String getName() {
		return name;
	}

	@Column(name="grp_name")
	public void setName(String name) {
		this.name = name;
	}

	@Column(name="grp_state")
	public int getState() {
		return state;
	}

	@Column(name="grp_state")
	public void setState(int state) {
		this.state = state;
	}

	@Column(name="grp_mode")
	public int getMode() {
		return mode;
	}

	@Column(name="grp_mode")
	public void setMode(int mode) {
		this.mode = mode;
	}
}
