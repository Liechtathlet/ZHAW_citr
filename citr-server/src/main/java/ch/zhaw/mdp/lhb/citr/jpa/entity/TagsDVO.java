package ch.zhaw.mdp.lhb.citr.jpa.entity;

import javax.persistence.*;

@Entity
@Table(name = "tbl_tag")
public class TagsDVO {

	@Id
	@GeneratedValue
	private int id;

	@Column(name = "grp_id")
	private int groupId;

	private String title;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns({@JoinColumn(name = "grp_id", insertable=false, updatable=false)})
	private GroupDVO group;

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public GroupDVO getGroup() {
		return group;
	}

	public void setGroup(GroupDVO group) {
		this.group = group;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
}
