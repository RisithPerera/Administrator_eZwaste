package com.ezwaste.base.client;

import com.ezwaste.model.child.Dustbin;
import com.ezwaste.model.child.Record;
import javafx.collections.ObservableList;
import java.sql.SQLException;

public interface RecordClient {
    public boolean add(Record record) throws SQLException, ClassNotFoundException;
    public boolean delete(Dustbin dustbin) throws SQLException, ClassNotFoundException;
    public ObservableList<Record> getRecord(Dustbin dustbin) throws SQLException, ClassNotFoundException;
    public ObservableList<Record> getCurrentLevels() throws SQLException, ClassNotFoundException;
}