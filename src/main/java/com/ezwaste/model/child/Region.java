package com.ezwaste.model.child;

import com.ezwaste.manifest.Symbol;
import com.ezwaste.model.superb.SuperModel;

import java.util.Objects;

public class Region extends SuperModel implements Comparable<Region> {

    private int id;
    private String vertex;

    public Region() {}

    public Region(int id) {
        this.id = id;
    }

    public Region(int id, String vertex) {
        this.id = id;
        this.vertex = vertex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVertex() {
        return vertex;
    }

    public void setVertex(String vertex) {
        this.vertex = vertex;
    }

    //---------------------------- Calculatons -------------------------------------//

    public String getIdText() {
        return Integer.toString(this.id);
    }

    //---------------------------- Override Methods -----------------------------//

    @Override
    public String toString() {
        return getId() + Symbol.SPLIT +
                getVertex();
    }

    @Override
    public int compareTo(Region dto) {
        boolean logic = dto.getId() > this.getId();
        return  logic ? -1 : !logic ? 1 : 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Region) {
            return ((Region)obj).getId() == this.getId();
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = Objects.hashCode(String.format("%05d", this.getId()));
        return hash;
    }
}
