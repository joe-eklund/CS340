package database;

import java.io.Serializable;
import java.util.List;

public interface IMoveCommandDAO {
	public void add(Serializable command, int gameID);
	public List<Object> getAll(int gameID);
	public void clear();
}
