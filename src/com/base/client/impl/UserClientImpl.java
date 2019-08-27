package com.base.client.impl;

import com.base.client.UserClient;
import com.base.connection.BaseConnection;
import com.base.list.ListConnection;
import com.model.child.Driver;
import com.model.child.User;
import javafx.collections.ObservableList;

import java.sql.*;

public class UserClientImpl implements UserClient {

    private static UserClientImpl userClient;
    private ObservableList<User> userList;

    private UserClientImpl() {
        userList = ListConnection.getInstance().getUserList();
    }

    public static UserClientImpl getInstance() {
        if (userClient == null) {
            userClient = new UserClientImpl();
        }
        return userClient;
    }

    @Override
    public boolean add(User user) throws SQLException, ClassNotFoundException {
        if (user == null) return false;
        String query = "INSERT INTO User VALUE (?,?)";
        Connection conn = BaseConnection.createConnection().getConnection();
        conn.setAutoCommit(false);
        try {
            PreparedStatement state = conn.prepareStatement(query);
            state.setObject(1, user.getUserName());
            state.setObject(2, user.getUserPassword());

            if(state.executeUpdate()>0){
                conn.commit();
                return true;
            }
            conn.rollback();
            return false;

        }finally{
            conn.setAutoCommit(true);
        }
    }

    @Override
    public boolean delete(User user) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM User WHERE username = "+ user.getUserName();
        Connection conn = BaseConnection.createConnection().getConnection();
        PreparedStatement state = conn.prepareStatement(query);
        return (state.executeUpdate() > 0);
    }

    @Override
    public User search(String username)throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM User WHERE username"+ username;
        Connection conn = BaseConnection.createConnection().getConnection();
        Statement state = conn.createStatement();
        ResultSet result = state.executeQuery(query);
        User user = new User();
        while (result.next()) {
            user.setUserName(result.getString("userName"));
            user.setUserPassword(result.getString("userPassword"));
        }
        return user;
    }

    @Override
    public ObservableList<User> getAll() {
        return userList;
    }

    @Override
    public void loadAll() throws SQLException, ClassNotFoundException {
        userList.clear();
        String query = "SELECT * FROM User";
        Connection conn = BaseConnection.createConnection().getConnection();
        Statement state = conn.createStatement();
        ResultSet result = state.executeQuery(query);

        while (result.next()) {
            User user = new User();
            user.setUserName(result.getString("userName"));
            user.setUserPassword(result.getString("userPassword"));
            userList.add(user);

        }
        System.out.println("User List Loaded : " + userList.size());
    }
}
