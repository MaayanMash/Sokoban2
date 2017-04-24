package model.db;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;


@Entity(name = "UserLevel")
public class UserLevel implements Comparable<UserLevel>, Serializable{

	//forigen key
	@EmbeddedId
	private UserLevelKey key;

	@Column(name = "levelSteps")
	private int levelSteps;
	
	@Column(name = "levelTime")
	private int levelTime;
	
	public UserLevel() {
		this.key=new UserLevelKey();
		this.levelSteps=0;
		this.levelTime=0;
	}
	
	public UserLevel(String userName, String levelName) {
		key = new UserLevelKey(userName, levelName);
		this.levelSteps=0;
		this.levelTime=0;
	}
	
	public UserLevel(String userName, String levelName, int steps, int time) {
		key=new UserLevelKey(userName, levelName);
		this.levelSteps=steps;
		this.levelTime=time;
	}
	
	public int getLevelSteps() {
		return levelSteps;
	}

	public void setLevelSteps(int levelSteps) {
		this.levelSteps = levelSteps;
	}

	public int getLevelTime() {
		return levelTime;
	}

	public void setLevelTime(int levelTime) {
		this.levelTime = levelTime;
	}

	@Override
	public String toString() {
		return key.toString()+" "+this.levelSteps+" "+this.levelTime;
	}

	public UserLevelKey getKey() {
		return key;
	}

	public void setKey(UserLevelKey key) {
		this.key = key;
	}

	@Override
	public int compareTo(UserLevel o) {
		return this.key.getUserName().compareTo(o.key.getUserName());
	}

}