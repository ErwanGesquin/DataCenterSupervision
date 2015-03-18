package com.iut.erwan.datacentersupervision;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.EditText;

import com.adventnet.snmp.beans.SnmpTarget;

/**
 * Created by erwan on 10/03/15.
 */
public class SnmpGetTaskSonde extends AsyncTask<String, ProgressDialog, String[]> {

    ProgressDialog progressBar = null;
    SnmpTarget target = null;
    Boolean erreurFlag = true;
    MainActivity context = null;
    private EditText BaieTempEditText;
    private String host = null;
    private int port = 0;
    private String community = null;

    public SnmpGetTaskSonde(MainActivity context, String host, int port, String community){
        this.context = context;
        this.host = host;
        this.port = port;
        this.community = community;
    }


    @Override
    protected void onPreExecute(){

    }

    @Override
    protected String[] doInBackground(String... params) {

        this.target = new SnmpTarget();
        this.target.setTargetHost(host);
        this.target.setTargetPort(port);
        this.target.setCommunity(community);
        this.target.setSnmpVersion(SnmpTarget.VERSION1);
        this.target.setObjectID(String.valueOf(params[0]));

        String[] res = this.target.snmpGetList();

        return res;
    }



    @Override
    protected void onPostExecute(String[] params){

        BaieTempEditText = (EditText) context.findViewById(R.id.BaieTempEditText);
        BaieTempEditText.setText(params[0] + " °C");
    }


}
