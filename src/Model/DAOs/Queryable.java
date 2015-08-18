package Model.DAOs;


import java.sql.SQLException;
import java.util.List;

public interface Queryable<T> {

    public List<T> getAllItems() throws SQLException;

    public T getItemById(String id) throws SQLException;

    public void addItem(T t) throws SQLException;



}
