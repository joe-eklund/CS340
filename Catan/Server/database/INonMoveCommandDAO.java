package database;

import java.io.Serializable;
import java.util.List;

public interface INonMoveCommandDAO {
	public void add(Serializable command);
	public List<Object> getAll();
	public void clear();
}
