package Dao;

import java.util.List;

public interface DAO<T> {
    void add(T t);
    void remove(int id);
    T get(int id);
    List<T> getAl();
}
