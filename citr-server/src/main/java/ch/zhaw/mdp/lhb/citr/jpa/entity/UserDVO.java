package ch.zhaw.mdp.lhb.citr.jpa.entity;

import javax.persistence.*;

@Entity
@Table(name = "APPUSERS")
@NamedQueries( { @NamedQuery(name = "User.findAll", query = "SELECT p FROM UserDVO p"),
		@NamedQuery(name = "User.findUser", query = "SELECT p FROM UserDVO p where p.name = :name and p.age = :age")
	})
public class UserDVO {
	@Column(name="cit_id")
	private int id;
	private String name;
	private int age;

	public void setId(int id) {
		this.id = id;
	}

	@Id
	public int getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getAge() {
		return age;
	}
}
