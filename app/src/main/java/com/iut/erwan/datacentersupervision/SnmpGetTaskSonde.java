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
    Boolean erreurFlag = null;
    MainActivity context = null;
    private EditText BaieTempEditText;

    public SnmpGetTaskSonde(MainActivity context){
        this.context = context;
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
        target.setTargetHost(params[1]);
        target.setObjectID(params[2]);

        return target.snmpGet();
    }



    @Override
    protected void onPostExecute(String params){
        progressBar.dismiss();
        BaieTempEditText = (EditText) context.findViewById(R.id.BaieTempEditText);
        BaieTempEditText.setText(params + " °C");
    }


}
