package com.iut.erwan.datacentersupervision;

import java.io.Serializable;

public class UsageDD implements Serializable {
    private static final long serialVersionUID = 123456789L;
    public String adate;
    public int usage;
    public long capacite;
    public long utilise;

    public UsageDD(String adate,int usage,long capacite,long utilise)
    {
        this.adate = adate;
        this.usage = usage;
        this.capacite = capacite;
        this.utilise = utilise;
    }

    @Override
    public String toString()
    {
        return (this.adate+ "\t : "+this.usage+ "\t : "+this.capacite+ "\t : "+this.utilise);
    }
}
