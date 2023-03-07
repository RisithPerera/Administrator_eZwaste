package com.base.client.impl;

import com.base.client.RecordClient;
import com.base.connection.BaseConnection;
import com.base.list.ListConnection;
import com.model.child.Dustbin;
import com.model.child.Record;
import javafx.collections.ObservableList;

import java.sql.*;

public class RecordClientImpl implements RecordClient {

    private static RecordClientImpl recordClient;
    private ObservableList<Record> recordList;

    private RecordClientImpl() {
        recordList = ListConnection.getInstance().getRecordList();
    }

    public static RecordClientImpl getInstance() {
        if (recordClient == null) {
            recordClient = new RecordClientImpl();
        }
        return recordClient;
    }

    @Override
    public boolean add(Record record) throws SQLException, ClassNotFoundException {
        if (record == null) return false;
        String query = "INSERT INTO Record VALUE (?,?,?)";
        Connection conn = BaseConnection.createConnection().getConnection();
        conn.setAutoCommit(false);
        try {
            PreparedStatement state = conn.prepareStatement(query);
            state.setObject(1, record.getRecordDT());
            state.setObject(2, record.getDustbinId());
            state.setObject(3, record.getLevel());

            if(state.executeUpdate()>0){
                recordList.add(record);
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
    public boolean delete(Dustbin dustbin) throws SQLException, ClassNotFoundException {
        if (dustbin == null) return false;
        String query = "DELETE FROM Record WHERE dustbinId = ?";
        Connection conn = BaseConnection.createConnection().getConnection();
        PreparedStatement state = conn.prepareStatement(query);
        state.setObject(1, dustbin.getId());
        if (state.executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public ObservableList<Record> getRecord(Dustbin dustbin) throws SQLException, ClassNotFoundException {

        String query = "SELECT * FROM Record where dustbinId = "+dustbin.getId();
        return retrieveData(query);
    }

    @Override
    public ObservableList<Record> getCurrentLevels() throws SQLException, ClassNotFoundException {

        String query = "select t1.dustbinId, t1.level\n" +
                "from record t1\n" +
                "inner join\n" +
                "(\n" +
                "  select max(recordDT) mxdate, dustbinId\n" +
                "  from record\n" +
                "  group by dustbinId\n" +
                ") t2\n" +
                "  on t1.dustbinId = t2.dustbinId\n" +
                "  and t1.recordDT = t2.mxdate";

        return retrieveData(query);
    }

    //------------------------------------------ Utility Functions ------------------------------------//
    private ObservableList<Record> retrieveData(String query) throws SQLException, ClassNotFoundException {
        recordList.clear();
        Connection conn = BaseConnection.createConnection().getConnection();
        Statement state = conn.createStatement();
        ResultSet result = state.executeQuery(query);

        while (result.next()) {
            Record record = new Record();
            //record.setRecordDT(result.getString("recordDT"));
            record.setDustbinId(result.getInt("dustbinId"));
            record.setLevel(result.getDouble("level"));
            recordList.add(record);
        }
        //System.out.println("Record List Loaded : " + recordList.size());
        return recordList;
    }
}
