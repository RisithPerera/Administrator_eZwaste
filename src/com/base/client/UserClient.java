package com.base.client;

import com.model.child.User;
import java.sql.SQLException;

public interface UserClient {
    public boolean add(User user) throws SQLException, ClassNotFoundException;
    public boolean delete(User user) throws SQLException, ClassNotFoundException;
    public User getUser(String username, String password) throws SQLException, ClassNotFoundException;
}