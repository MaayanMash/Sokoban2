package model.db;

import java.io.Serializable;

import javax.persistence.Embeddable;


@Embeddable
public class UserLevelKey implements Serializable{

	//forigen key
	private String levelName;
	private String userName;

	//def C'tor
	public UserLevelKey() {
	}
	//C'tor
	public UserLevelKey(String UserName, String LevelName) {
		super();
		this.levelName=LevelName;
		this.userName=UserName;
	}
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((levelName == null) ? 0 : levelName.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserLevelKey other = (UserLevelKey) obj;
		if (levelName == null) {
			if (other.levelName != null)
				return false;
		} else if (!levelName.equals(other.levelName))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return this.userName+" "+this.levelName;
	}

}