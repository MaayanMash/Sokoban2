package model.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "Users")
public class Users {
	@Id
	@Column(name="userName")
	private String userName;

	

	public Users(String name) {
		setName(name);
	}

	public Users() {
	}


	public String getName() {
		return this.userName;
	}

	public void setName(String name) {
		this.userName=name;
	}


@Override
	public String toString() {
		return userName;
	}
}