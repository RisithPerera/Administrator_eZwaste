package com.ezwaste.base.client.impl;

import com.ezwaste.base.connection.BaseConnection;
import com.ezwaste.base.client.GatewayClient;
import com.ezwaste.base.list.ListConnection;
import com.ezwaste.model.child.Gateway;
import javafx.collections.ObservableList;

import java.sql.*;

public class GatewayClientImpl implements GatewayClient {

    private static GatewayClientImpl gatewayClient;
    private ObservableList<Gateway> gatewayList;

    private GatewayClientImpl() {
        gatewayList = ListConnection.getInstance().getGatewayList();
    }

    public static GatewayClientImpl getInstance() {
        if (gatewayClient == null) {
            gatewayClient = new GatewayClientImpl();
        }
        return gatewayClient;
    }

    @Override
    public boolean add(Gateway gateway) throws SQLException, ClassNotFoundException {
        if (gateway == null) return false;
        String query = "INSERT INTO Gateway VALUE (?,?,?)";
        Connection conn = BaseConnection.createConnection().getConnection();
        conn.setAutoCommit(false);
        try {
            PreparedStatement state = conn.prepareStatement(query);
            state.setObject(1, gateway.getId());
            state.setObject(2, gateway.getLatitude());
            state.setObject(3, gateway.getLongitude());
            if(state.executeUpdate()>0){
                gatewayList.add(gateway);
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
        String query = "DELETE FROM Gateway WHERE gatewayId = ?";
        Connection conn = BaseConnection.createConnection().getConnection();
        PreparedStatement state = conn.prepareStatement(query);
        state.setObject(1, id);
        if (state.executeUpdate() > 0) {
            Gateway gateway = new Gateway(id);
            gatewayList.remove(gateway);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Gateway search(int id) {
        if (id < 0) return null;
        Gateway gateway = new Gateway(id);
        int index = gatewayList.indexOf(gateway);
        if (index != -1) {
            return gatewayList.get(index);
        }
        return null;
    }

    @Override
    public ObservableList<Gateway> getAll() {
        return gatewayList;
    }

    @Override
    public void loadAll() throws SQLException, ClassNotFoundException {
        gatewayList.clear();
        String query = "SELECT * FROM Gateway";
        Connection conn = BaseConnection.createConnection().getConnection();
        Statement state = conn.createStatement();
        ResultSet result = state.executeQuery(query);

        while (result.next()) {
            Gateway gateway = new Gateway();
            gateway.setId(result.getInt("gatewayId"));
            gateway.setLatitude(result.getDouble("latitude"));
            gateway.setLongitude(result.getDouble("longitude"));

            gatewayList.add(gateway);

        }
        System.out.println("Gateway List Loaded : " + gatewayList.size());
    }


    @Override
    public int getNextId() throws SQLException, ClassNotFoundException {
        String query = "SELECT gatewayId+1 AS nextID FROM Gateway ORDER BY 1 DESC LIMIT 1";
        Connection conn = BaseConnection.createConnection().getConnection();
        PreparedStatement state = conn.prepareStatement(query);
        ResultSet result = state.executeQuery();
        if (result.next()) {
            return result.getInt("nextID");
        }
        return 0;
    }
}
