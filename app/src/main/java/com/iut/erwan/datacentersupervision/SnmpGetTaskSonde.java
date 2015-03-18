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
public class SnmpGetTaskSonde extends AsyncTask<String, ProgressDialog, String> {

    ProgressDialog progressBar = null;
    SnmpTarget target = null;
    Boolean erreurFlag = true;
    MainActivity context = null;
    private EditText BaieTempEditText;
    private String host = null;
    private int port = 0;
    private String OID = ".1.3.6.1.4.1.21796.4.1.3.1.4.1";

    public SnmpGetTaskSonde(MainActivity context, String host, int port){
        this.context = context;
        this.host = host;
        this.port = port;
    }


    @Override
    protected void onPreExecute(){
        progressBar = new ProgressDialog(context.getApplicationContext());
        progressBar.setTitle("Progession");
        progressBar.show();
    }

    @Override
    protected String doInBackground(String[] params) {

        target = new SnmpTarget();
        target.setTargetHost(host);
        target.setTargetPort(port);
        target.setObjectID(OID);

        return target.snmpGet();
    }



    @Override
    protected void onPostExecute(String params){
        progressBar.dismiss();
        BaieTempEditText = (EditText) context.findViewById(R.id.BaieTempEditText);
        BaieTempEditText.setText(params + " °C");
    }


}
