package com.base.client;

import com.model.child.Compartment;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface CompartmentClient {
    public boolean add(Compartment compartment,  ObservableList<Seat> seatList) throws SQLException, ClassNotFoundException;
    public Compartment search(int compartmentId) throws SQLException, ClassNotFoundException;
    public ObservableList<Compartment> getEngineCompartments(Engine engine) throws SQLException, ClassNotFoundException;
    public int getNextId() throws SQLException, ClassNotFoundException;
}
