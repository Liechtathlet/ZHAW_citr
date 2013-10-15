package ch.zhaw.mdp.lhb.citr.jpa.entity;

import javax.persistence.*;

@Entity
@Table(name = "tbl_group")
@NamedQueries(
		{ @NamedQuery(name = "Group.findAll", query = "SELECT g FROM GroupDVO g")
})
public class GroupDVO {

	private int id;
	private String name;
	private String state;
	private String mode;

    public GroupDVO() {
        // every new group is active
        this.setState("active");
    }


    @Id
	@GeneratedValue
	@Column(name="grp_id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="grp_name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="grp_state")
	public String getState() {
		return state;
	}

	public void setState(String state) {
        // if (state == "deleted" || state == "active") {
            this.state = state;
        // }
	}

	@Column(name="grp_mode")
	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
        // if (mode == "public" || mode == "private") {
            this.mode = mode;
        // }
	}
}
