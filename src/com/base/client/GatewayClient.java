package com.base.client;

import com.model.child.Gateway;
import javafx.collections.ObservableList;
import java.sql.SQLException;

public interface GatewayClient {
    public boolean add(Gateway gateway) throws SQLException, ClassNotFoundException;
    public Gateway search(int t);
    public boolean delete(int t) throws SQLException, ClassNotFoundException;
    public ObservableList<Gateway> getAll();
    public void loadAll() throws SQLException, ClassNotFoundException;
    public int getNextId() throws SQLException, ClassNotFoundException;
}