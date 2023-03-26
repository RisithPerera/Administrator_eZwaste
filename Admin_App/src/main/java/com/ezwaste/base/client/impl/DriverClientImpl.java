package com.ezwaste.base.client.impl;

import com.ezwaste.base.connection.BaseConnection;
import com.ezwaste.base.client.DriverClient;
import com.ezwaste.base.list.ListConnection;
import com.ezwaste.model.child.Driver;
import javafx.collections.ObservableList;

import java.sql.*;

public class DriverClientImpl implements DriverClient {

    private static DriverClientImpl driverClient;
    private ObservableList<Driver> driverList;

    private DriverClientImpl() {
        driverList = ListConnection.getInstance().getDriverList();
    }

    public static DriverClientImpl getInstance() {
        if (driverClient == null) {
            driverClient = new DriverClientImpl();
        }
        return driverClient;
    }

    @Override
    public boolean add(Driver driver) throws SQLException, ClassNotFoundException {
        if (driver == null) return false;
        String query = "INSERT INTO Driver VALUE (?,?,?,?,?,?,?,?,?,?,?)";
        Connection conn = BaseConnection.createConnection().getConnection();
        conn.setAutoCommit(false);
        try {
            PreparedStatement state = conn.prepareStatement(query);
            state.setObject( 1, driver.getId());
            state.setObject( 2, driver.getUsername());
            state.setObject( 3, driver.getPassword());
            state.setObject( 4, driver.getFName());
            state.setObject( 5, driver.getLName());
            state.setObject( 6, driver.getStreet());
            state.setObject( 7, driver.getCity());
            state.setObject( 8, driver.getDistrict());
            state.setObject( 9, driver.getContact());
            state.setObject(10, driver.getRegionId());
            state.setObject(11, driver.getReputation());

            if(state.executeUpdate()>0){
                driverList.add(driver);
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
    public boolean delete(int id) throws SQLException, ClassNotFoundException {
        if (id < 0) return false;
        String query = "DELETE FROM Driver WHERE driverId = ?";
        Connection conn = BaseConnection.createConnection().getConnection();
        PreparedStatement state = conn.prepareStatement(query);
        state.setObject(1, id);
        if (state.executeUpdate() > 0) {
            Driver driver = new Driver(id);
            driverList.remove(driver);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Driver search(int id) {
        if (id < 0) return null;
        Driver driver = new Driver(id);
        int index = driverList.indexOf(driver);
        if (index != -1) {
            return driverList.get(index);
        }
        return null;
    }

    @Override
    public ObservableList<Driver> getAll() {
        return driverList;
    }

    @Override
    public void loadAll() throws SQLException, ClassNotFoundException {
        driverList.clear();
        String query = "SELECT * FROM Driver";
        Connection conn = BaseConnection.createConnection().getConnection();
        Statement state = conn.createStatement();
        ResultSet result = state.executeQuery(query);

        while (result.next()) {
            Driver driver = new Driver();
            driver.setId(result.getInt("driverId"));
            driver.setPassword(result.getString("userName"));
            driver.setPassword(result.getString("password"));
            driver.setFName(result.getString("fName"));
            driver.setLName(result.getString("lName"));
            driver.setStreet(result.getString("street"));
            driver.setCity(result.getString("city"));
            driver.setDistrict(result.getString("district"));
            driver.setContact(result.getString("contact"));
            driver.setRegionId(result.getInt("regionId"));
            driver.setReputation(result.getDouble("reputation"));
            driverList.add(driver);

        }
        System.out.println("Driver List Loaded : " + driverList.size());
    }

    @Override
    public int getNextId() throws SQLException, ClassNotFoundException {
        String query = "SELECT driverId+1 AS nextID FROM Driver ORDER BY 1 DESC LIMIT 1";
        Connection conn = BaseConnection.createConnection().getConnection();
        PreparedStatement state = conn.prepareStatement(query);
        ResultSet result = state.executeQuery();
        if (result.next()) {
            return result.getInt("nextID");
        }
        return 0;
    }
}
