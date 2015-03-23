package com.iut.erwan.datacentersupervision;

import java.io.Serializable;

public class UsageMP implements Serializable {
    private static final long serialVersionUID = 123456789L;
    public String adate;
    public int nbProcs;
    public int mp1;
    public int mp2;
    public int mp3;
    public int mp4;
    public int mp5;
    public int mp6;
    public int mp7;
    public int mp8;

    public UsageMP(String adate,int nbProcs,int mp1,int mp2,int mp3,int mp4,int mp5,int mp6,int mp7,int mp8)
    {
        this.adate = adate;
        this.nbProcs = nbProcs;
        this.mp1 = mp1;
        this.mp2 = mp2;
        this.mp3 = mp3;
        this.mp4 = mp4;
        this.mp5 = mp5;
        this.mp6 = mp6;
        this.mp7 = mp7;
        this.mp8 = mp8;
    }
}
