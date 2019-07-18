package by.tolkun.barbershop.dao;

import entity.Entity;
import exception.PersistentException;

import java.util.List;

public interface Dao<T extends Entity> {
    int create(T entity) throws PersistentException;

    T read(int id) throws PersistentException;

    List<T> readAll() throws PersistentException;

    void update(T entity) throws PersistentException;

    void delete(int id) throws PersistentException;
}
