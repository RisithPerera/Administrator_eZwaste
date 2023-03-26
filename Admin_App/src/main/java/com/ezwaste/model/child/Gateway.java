package com.ezwaste.model.child;

import com.ezwaste.manifest.Symbol;
import com.ezwaste.model.superb.SuperModel;

import java.util.Objects;

public class Gateway extends SuperModel implements Comparable<Gateway> {

    private int id;
    private double latitude;
    private double longitude;

    public Gateway() {
    }

    public Gateway(int id) {
        this.id = id;
    }

    public Gateway(int id, double latitude, double longitude) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    //---------------------------- Calculatons -------------------------------------//

    public String getIdText() {
        return Integer.toString(this.id);
    }

    //---------------------------- Override Methods -----------------------------//

    @Override
    public String toString() {
        return  getId()          + Symbol.SPLIT +
                getLatitude()    + Symbol.SPLIT +
                getLongitude();
    }

    @Override
    public int compareTo(Gateway dto) {
        boolean logic = dto.getId() > this.getId();
        return  logic ? -1 : !logic ? 1 : 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Gateway) {
            return ((Gateway)obj).getId() == this.getId();
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = Objects.hashCode(String.format("%05d", this.getId()));
        return hash;
    }
}
