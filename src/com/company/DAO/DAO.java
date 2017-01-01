package com.company.DAO;

import java.sql.Connection;

/**
 * Created by lhadj on 1/1/2017.
 */
public abstract class DAO<T> {
    protected Connection connection = null ;

    public DAO(Connection connection){
        this.connection = connection;
    }

    public abstract boolean create(T obj);
    public abstract boolean delete(T obj);
    public abstract boolean update(T obj);
    public abstract T find(int id);
}
