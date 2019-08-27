
package com.model.child;

import com.manifest.Symbol;
import com.model.superb.SuperModel;

import java.util.Objects;

public class Dustbin extends SuperModel implements Comparable<Dustbin>{
    private int id;
    private double latitude;
    private double longitude;
    private int regionId;
    private int gatewayId;

    public Dustbin() {
    }

    public Dustbin(int id) {
        this.id = id;
    }

    public Dustbin(int id, double latitude, double longitude, int regionId, int gatewayId) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.regionId = regionId;
        this.gatewayId = gatewayId;
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

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public int getGatewayId() {
        return gatewayId;
    }

    public void setGatewayId(int gatewayId) {
        this.gatewayId = gatewayId;
    }

    //---------------------------- Calculatons ------------------------------------------//
   

    //---------------------------- Override Methods -------------------------------------//
    
    @Override
    public String toString() {
        return  getId()        + Symbol.SPLIT +
                getLatitude()  + Symbol.SPLIT +
                getLongitude() + Symbol.SPLIT +
                getRegionId()  + Symbol.SPLIT +
                getGatewayId();
    }      

    @Override
    public int compareTo(Dustbin dto) {
        boolean logic = dto.getId() > this.getId();
        return  logic ? -1 : !logic ? 1 : 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Dustbin) {
            return ((Dustbin)obj).getId() == this.getId();
        }
        return false;
    }

    @Override
    public int hashCode() {        
        int hash = Objects.hashCode(String.format("%05d", this.getId()));
        return hash;
    } 
}
