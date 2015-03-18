package com.iut.erwan.datacentersupervision;

import android.os.Parcelable;

import java.io.Serializable;

public class TEMP implements Serializable {
    private static final long serialVersionUID = 123456789L;
    public String adate;
    public String temp;
    public String nomBaie;

    public TEMP(String adate,String temp,String nomBaie)
    {
        this.adate = adate;
        this.temp = temp;
        this.nomBaie = nomBaie;
    }

    @Override
    public String  toString()
    {
        return (this.adate+ "\t : "+this.temp+ "\t : "+this.nomBaie);
    }
}
