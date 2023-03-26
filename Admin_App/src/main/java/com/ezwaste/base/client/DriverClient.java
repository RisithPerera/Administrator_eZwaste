package com.ezwaste.base.client;
import com.ezwaste.model.child.Driver;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface DriverClient {
    public boolean add(Driver driver) throws SQLException, ClassNotFoundException;
    public Driver search(int t);
    public boolean delete(int t) throws SQLException, ClassNotFoundException;
    public ObservableList<Driver> getAll();
    public void loadAll() throws SQLException, ClassNotFoundException;
    public int getNextId() throws SQLException, ClassNotFoundException;
}