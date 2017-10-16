/**
 * Created by PENGFEI XU on 2017.
 */
package com.example.android.aurin_android_pengfeixu;

import java.io.Serializable;

public class BBOX implements Serializable{

    //private String name;
    private double lowerLon = 0.0;
    private double higherLon = 0.0;
    private double lowerLa = 0.0;
    private double higherLa = 0.0;

    // constructors function
    public BBOX(){

    }

    public BBOX(double lowerLon, double higherLon, double lowerLa, double higherLa){
        this.lowerLon = lowerLon;
        this.higherLon = higherLon;
        this.lowerLa = lowerLa;
        this.higherLa = higherLa;
    }

    // set and get methods.
    public void setLowerLon(Double lowerLon) {
        this.lowerLon = lowerLon;
    }

    public Double getLowerLon() {
        return lowerLon;
    }

    public void setHigherLon(Double higherLon) {
        this.higherLon = higherLon;
    }

    public Double getHigherLon() {
        return higherLon;
    }

    public void setLowerLa(Double lowerLa) {
        this.lowerLa = lowerLa;
    }

    public Double getLowerLa() {
        return lowerLa;
    }

    public void setHigherLa(Double higherLa) {
        this.higherLa = higherLa;
    }

    public Double getHigherLa() {
        return higherLa;
    }
}
