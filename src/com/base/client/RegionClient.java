package com.base.client;

import com.model.child.Region;
import javafx.collections.ObservableList;
import java.sql.SQLException;


public interface RegionClient {
    public boolean add(Region region) throws SQLException, ClassNotFoundException;
    public Region search(int t);
    public boolean delete(int t) throws SQLException, ClassNotFoundException;
    public ObservableList<Region> getAll();
    public void loadAll() throws SQLException, ClassNotFoundException;
    public int getNextId() throws SQLException, ClassNotFoundException;
}