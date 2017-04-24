package model.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Levels")
public class Levels {
	@Id
	@Column(name="levelName")
	private String levelName;
	

	public Levels(String lName) {
		setName(lName);
	}

	public Levels() {
	}

	public String getName() {
		return this.levelName;
	}

	public void setName(String name) {
		this.levelName=name;
	}


@Override
	public String toString() {
		return levelName;
	}
}