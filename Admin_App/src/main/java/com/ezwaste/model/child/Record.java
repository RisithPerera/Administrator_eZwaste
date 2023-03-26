
package com.ezwaste.model.child;

import com.ezwaste.manifest.Symbol;
import com.ezwaste.model.superb.SuperModel;

import java.util.Objects;

public class Record extends SuperModel {

    private String recordDT;
    private int dustbinId;
    private double level;

    public Record() {}

    public Record(String recordDT, int dustbinId, double level) {
        this.recordDT = recordDT;
        this.dustbinId = dustbinId;
        this.level = level;
    }

    public String getId(){
        return recordDT + Symbol.SPLIT + dustbinId;
    }
    public String getRecordDT() {
        return recordDT;
    }

    public void setRecordDT(String recordDT) {
        this.recordDT = recordDT;
    }

    public int getDustbinId() {
        return dustbinId;
    }

    public void setDustbinId(int dustbinId) {
        this.dustbinId = dustbinId;
    }

    public double getLevel() {
        return level;
    }

    public void setLevel(double level) {
        this.level = level;
    }

    //---------------------------- Calculatons -------------------------------------//

    //---------------------------- Override Methods --------------------------------//
    
    @Override
    public String toString() {
        return  getRecordDT()  + Symbol.SPLIT +
                getDustbinId() + Symbol.SPLIT +
                getLevel();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Record) {
            return this.getId().equals(((Record) obj).getId());
        }
        return false;
    }

    @Override
    public int hashCode() {        
        int hash = Objects.hashCode(String.format("%05d", this.getId()));
        return hash;
    } 
}
