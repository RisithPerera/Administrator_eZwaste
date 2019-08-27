package com.base.client.impl;

import com.base.client.DustbinClient;
import com.base.connection.BaseConnection;
import com.base.list.ListConnection;
import com.model.child.Dustbin;
import javafx.collections.ObservableList;

import java.sql.*;

public class DustbinClientImpl implements DustbinClient {

    private static DustbinClientImpl dustbinClient;
    private ObservableList<Dustbin> dustbinList;

    private DustbinClientImpl() {
        dustbinList = ListConnection.getInstance().getDustbinList();
    }

    public static DustbinClientImpl getInstance() {
        if (dustbinClient == null) {
            dustbinClient = new DustbinClientImpl();
        }
        return dustbinClient;
    }

    @Override
    public boolean add(Dustbin dustbin) throws SQLException, ClassNotFoundException {
        if (dustbin == null) return false;
        String query = "INSERT INTO Dustbin VALUE (?,?,?,?,?)";
        Connection conn = BaseConnection.createConnection().getConnection();
        conn.setAutoCommit(false);
        try {
            PreparedStatement state = conn.prepareStatement(query);
            state.setObject(1, dustbin.getId());
            state.setObject(2, dustbin.getLatitude());
            state.setObject(3, dustbin.getLongitude());
            state.setObject(4, dustbin.getRegionId());
            state.setObject(5, dustbin.getGatewayId());

            if(state.executeUpdate()>0){
                dustbinList.add(dustbin);
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
        String query = "DELETE FROM Dustbin WHERE dustbinId = ?";
        Connection conn = BaseConnection.createConnection().getConnection();
        PreparedStatement state = conn.prepareStatement(query);
        state.setObject(1, id);
        if (state.executeUpdate() > 0) {
            Dustbin dustbin = new Dustbin(id);
            dustbinList.remove(dustbin);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Dustbin search(int id) {
        if (id < 0) return null;
        Dustbin dustbin = new Dustbin(id);
        int index = dustbinList.indexOf(dustbin);
        if (index != -1) {
            return dustbinList.get(index);
        }
        return null;
    }

    @Override
    public ObservableList<Dustbin> getAll() {
        return dustbinList;
    }

    @Override
    public void loadAll() throws SQLException, ClassNotFoundException {
        dustbinList.clear();
        String query = "SELECT * FROM Dustbin";
        Connection conn = BaseConnection.createConnection().getConnection();
        Statement state = conn.createStatement();
        ResultSet result = state.executeQuery(query);

        while (result.next()) {
            Dustbin dustbin = new Dustbin();
            dustbin.setId(result.getInt("dustbinId"));
            dustbin.setLatitude(result.getDouble("latitude"));
            dustbin.setLongitude(result.getDouble("longitude"));
            dustbin.setRegionId(result.getInt("regionId"));
            dustbin.setGatewayId(result.getInt("gatewayId"));
            dustbinList.add(dustbin);

        }
        System.out.println("Dustbin List Loaded : " + dustbinList.size());
    }


    @Override
    public int getNextId() throws SQLException, ClassNotFoundException {
        String query = "SELECT dustbinId+1 AS nextID FROM Dustbin ORDER BY 1 DESC LIMIT 1";
        Connection conn = BaseConnection.createConnection().getConnection();
        PreparedStatement state = conn.prepareStatement(query);
        ResultSet result = state.executeQuery();
        if (result.next()) {
            return result.getInt("nextID");
        }
        return 0;
    }
}