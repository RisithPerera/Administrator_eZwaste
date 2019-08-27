package com.base.client;

import com.model.child.User;
import javafx.collections.ObservableList;
import java.sql.SQLException;

public interface UserClient {
    public boolean add(User user) throws SQLException, ClassNotFoundException;
    public User search(String username) throws SQLException, ClassNotFoundException;
    public boolean delete(User user) throws SQLException, ClassNotFoundException;
    public ObservableList<User> getAll();
    public void loadAll() throws SQLException, ClassNotFoundException;
}