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

	@ManyToMany(mappedBy="groups")
	private List<UserDVO> users;

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

	public List<UserDVO> getUsers() {
		return users;
	}

	public void setUsers(List<UserDVO> users) {
		this.users = users;
	}
}
