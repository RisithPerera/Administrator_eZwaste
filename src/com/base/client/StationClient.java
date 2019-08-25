package com.base.client;

import com.model.child.Compartment;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface StationClient {
    public Station search(int id);
    public ObservableList<Station> getAll();
    public void loadAll() throws SQLException, ClassNotFoundException;
}
