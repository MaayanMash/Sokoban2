package model.db;

import java.util.List;

public interface iDBManager {
	
	public void add(Object o) throws Exception;
	public void update(Object o) throws Exception;
	public List selectScore(String query);

}
