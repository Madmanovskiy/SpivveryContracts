package Model.DAOs;


import java.util.List;

public interface Queryable<T> {

    public List<T> getAllItems();

    public T getItemById(String id);

    public void addItem(T t);



}
