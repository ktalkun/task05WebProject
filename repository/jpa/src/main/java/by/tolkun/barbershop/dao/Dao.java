package by.tolkun.barbershop.dao;

import by.tolkun.barbershop.entity.Entity;

import java.util.List;

public interface Dao<T extends Entity> {
    int create(T entity);

    T read(int id);

    List<T> readAll();

    void update(T entity);

    void delete(int id);
}
