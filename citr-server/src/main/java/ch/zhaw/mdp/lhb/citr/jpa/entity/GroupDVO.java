package ch.zhaw.mdp.lhb.citr.jpa.entity;

import javax.persistence.*;

/**
 * @author Simon Lang
 *
 * Data-Class for 'Group'.
 */
@Entity
@Table(name = "tbl_group")
@NamedQueries(
		{ @NamedQuery(name = "Group.findAll", query = "SELECT g FROM GroupDVO g")
})
public class GroupDVO {

	@Id
	@GeneratedValue
	private int id;
	
	private String name;
	private String state;
	private String mode;

    /**
     * Creates a new instance
     */
    public GroupDVO() {
        // every new group is active
        this.setState("active");
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param aName the name to set
	 */
	public void setName(String aName) {
		name = aName;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param aState the state to set
	 */
	public void setState(String aState) {
		state = aState;
	}

	/**
	 * @return the mode
	 */
	public String getMode() {
		return mode;
	}

	/**
	 * @param aMode the mode to set
	 */
	public void setMode(String aMode) {
		mode = aMode;
	}
	
}
