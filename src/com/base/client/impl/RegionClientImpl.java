package com.base.client.impl;

import com.base.client.RegionClient;
import com.base.connection.BaseConnection;
import com.base.list.ListConnection;
import com.model.child.Region;
import javafx.collections.ObservableList;

import java.sql.*;

public class RegionClientImpl implements RegionClient {

    private static RegionClientImpl regionClient;
    private ObservableList<Region> regionList;

    private RegionClientImpl() {
        regionList = ListConnection.getInstance().getRegionList();
    }

    public static RegionClientImpl getInstance() {
        if (regionClient == null) {
            regionClient = new RegionClientImpl();
        }
        return regionClient;
    }

    @Override
    public boolean add(Region region) throws SQLException, ClassNotFoundException {
        if (region == null) return false;
        String query = "INSERT INTO Region VALUE (?,?)";
        Connection conn = BaseConnection.createConnection().getConnection();
        conn.setAutoCommit(false);
        try {
            PreparedStatement state = conn.prepareStatement(query);
            state.setObject(1, region.getId());
            state.setObject(2, region.getVertex());

            if(state.executeUpdate()>0){
                regionList.add(region);
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
        String query = "DELETE FROM Region WHERE regionId = ?";
        Connection conn = BaseConnection.createConnection().getConnection();
        PreparedStatement state = conn.prepareStatement(query);
        state.setObject(1, id);
        if (state.executeUpdate() > 0) {
            Region region = new Region(id);
            regionList.remove(region);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Region search(int id) {
        if (id < 0) return null;
        Region region = new Region(id);
        int index = regionList.indexOf(region);
        if (index != -1) {
            return regionList.get(index);
        }
        return null;
    }

    @Override
    public ObservableList<Region> getAll() {
        return regionList;
    }

    @Override
    public void loadAll() throws SQLException, ClassNotFoundException {
        regionList.clear();
        String query = "SELECT * FROM Region";
        Connection conn = BaseConnection.createConnection().getConnection();
        Statement state = conn.createStatement();
        ResultSet result = state.executeQuery(query);

        while (result.next()) {
            Region region = new Region();
            region.setId(result.getInt("regionId"));
            region.setVertex(result.getString("vertex"));
            regionList.add(region);

        }
        System.out.println("Region List Loaded : " + regionList.size());
    }

    @Override
    public int getNextId() throws SQLException, ClassNotFoundException {
        String query = "SELECT regionId+1 AS nextID FROM Region ORDER BY 1 DESC LIMIT 1";
        Connection conn = BaseConnection.createConnection().getConnection();
        PreparedStatement state = conn.prepareStatement(query);
        ResultSet result = state.executeQuery();
        if (result.next()) {
            return result.getInt("nextID");
        }
        return 0;
    }
}
