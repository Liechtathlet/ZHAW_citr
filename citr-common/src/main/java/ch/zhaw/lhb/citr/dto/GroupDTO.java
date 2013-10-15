package ch.zhaw.lhb.citr.dto;

/**
 * @author Daniel Brun
 * @author Simon Lang
 *
 * DTO-Class for 'Group'.
 */
public class GroupDTO {
	private int id;
	private String name;
	private int state;
	private int mode;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}
}
