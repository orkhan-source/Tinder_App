package Dao;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
    void add(T t);
    void remove(int id);
    T get(int id);
    List<T> getAll();
}
