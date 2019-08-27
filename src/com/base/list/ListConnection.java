/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.list;


import com.model.child.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author risit
 */
public class ListConnection {

    private static ListConnection listConnection;

    private final ObservableList<Driver> driverList;
    private final ObservableList<Dustbin> dustbinList;
    private final ObservableList<Gateway> gatewayList;
    private final ObservableList<Record> recordList;
    private final ObservableList<Region> regionList;
    private final ObservableList<User> userList;

    private ListConnection() {
        this.driverList = FXCollections.observableArrayList();
        this.dustbinList = FXCollections.observableArrayList();
        this.gatewayList = FXCollections.observableArrayList();
        this.recordList = FXCollections.observableArrayList();
        this.regionList = FXCollections.observableArrayList();
        this.userList = FXCollections.observableArrayList();

    }

    public static ListConnection getInstance() {
        if (listConnection == null) {
            listConnection = new ListConnection();
        }
        return listConnection;
    }

    public ObservableList<Driver> getDriverList() {return driverList;}
    public ObservableList<Dustbin> getDustbinList() {return dustbinList;}
    public ObservableList<Gateway> getGatewayList() {return gatewayList;}
    public ObservableList<Record> getRecordList() {return recordList;}
    public ObservableList<Region> getRegionList() {return regionList;}
    public ObservableList<User> getUserList() {return userList;}

}
