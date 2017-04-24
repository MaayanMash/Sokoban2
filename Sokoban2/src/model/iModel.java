package model;


import java.util.List;

import commons.Level2D;

public interface iModel {
	
	public Level2D getCurrentLevel();
	
	public void moveUp();
	public void moveDown();
	public void moveRight();
	public void moveLeft();
	
	public void txtLevelLoad(String path);
	public void objLevelLoad(String path);
	public void xmlLevelLoad(String path);

	public void txtLevelSave(String path);
	public void objLevelSave(String path);
	public void xmlLevelSave(String path);
	
	public void error();
	
	public int getSteps();
	
	public void addToDB(Object o) ;
	public void updateDB(Object o);
	public List selectScore(String query);
}
