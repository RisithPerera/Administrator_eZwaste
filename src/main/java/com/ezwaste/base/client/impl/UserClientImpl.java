package com.ezwaste.base.client.impl;

import com.ezwaste.base.connection.BaseConnection;
import com.ezwaste.base.client.UserClient;
import com.ezwaste.base.list.ListConnection;
import com.ezwaste.model.child.User;
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
        if (user == null) return false;
        String query = "DELETE FROM User WHERE username = "+ user.getUserName();
        Connection conn = BaseConnection.createConnection().getConnection();
        PreparedStatement state = conn.prepareStatement(query);
        return (state.executeUpdate() > 0);
    }


    @Override
    public User getUser(String userName, String password) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM User WHERE userName LIKE '"+userName+"' AND userPassword LIKE '"+password+"'";
        Connection conn = BaseConnection.createConnection().getConnection();
        PreparedStatement state = conn.prepareStatement(query);
        ResultSet result = state.executeQuery();
        if (result.next()) {
            User user = new User();
            user.setUserName(result.getString("userName"));
            user.setUserPassword(result.getString("userPassword"));
            return user;
        }
        return null;
    }
}
