package ch.zhaw.mdp.lhb.citr.jpa.entity;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_group")
@NamedQueries( { @NamedQuery(name = "Group.findAll", query = "SELECT g FROM GroupDVO g")
})
public class GroupDVO {

	private int grp_id;
	private String grp_name;
	private int grp_state;
	private int grp_mode;

	@Id
	public int getGrp_id() {
		return grp_id;
	}

	public void setGrp_id(int id) {
		this.grp_id = id;
	}

	public String getGrp_name() {
		return grp_name;
	}

	public void setGrp_name(String name) {
		this.grp_name = name;
	}

	public int getGrp_state() {
		return grp_state;
	}

	public void setGrp_state(int state) {
		this.grp_state = state;
	}

	public int getGrp_mode() {
		return grp_mode;
	}

	public void setGrp_mode(int mode) {
		this.grp_mode = mode;
	}
}
