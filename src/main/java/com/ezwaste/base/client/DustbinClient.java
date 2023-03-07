package com.ezwaste.base.client;

import com.ezwaste.model.child.Dustbin;
import javafx.collections.ObservableList;
import java.sql.SQLException;


public interface DustbinClient {
    public boolean add(Dustbin dustbin) throws SQLException, ClassNotFoundException;
    public Dustbin search(int t);
    public boolean delete(int t) throws SQLException, ClassNotFoundException;
    public ObservableList<Dustbin> getAll();
    public void loadAll() throws SQLException, ClassNotFoundException;
    public int getNextId() throws SQLException, ClassNotFoundException;
}