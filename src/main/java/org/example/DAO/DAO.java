package org.example.DAO;

import java.util.List;

public interface DAO<T> {
     void create(T t);
     List<T> get();
     T getById(int id);
     void delete(int id);
     void update(T t);
}
