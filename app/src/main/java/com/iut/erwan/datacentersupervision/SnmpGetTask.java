package com.iut.erwan.datacentersupervision;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.EditText;

import com.adventnet.snmp.beans.SnmpTarget;

/**
 * Created by erwan on 19/03/15.
 */
public class SnmpGetTask extends AsyncTask<String, ProgressDialog, String[]> {

    ProgressDialog progressBar = null;
    SnmpTarget target = null;
    Boolean erreurFlag = true;
    MainActivity context = null;
    private EditText DDEditText;
    private EditText procUseEditText;
    private String host = null;
    private int port = 0;
    private String community = null;
    private Boolean disk = null;

    public SnmpGetTask(MainActivity context, String host, int port, String community, Boolean disk){
        this.context = context;
        this.host = host;
        this.port = port;
        this.community = community;
        this.disk = disk;
    }


    @Override
    protected void onPreExecute(){
       /* progressBar = new ProgressDialog(context.getApplicationContext());
        progressBar.setMessage("Progression.");
        progressBar.show();*/
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
        // progressBar.dismiss();
        if(disk) {
            DDEditText = (EditText) context.findViewById(R.id.DDEditText);
            DDEditText.setText(params[0] + " °C");
        } else {
            procUseEditText = (EditText) context.findViewById(R.id.procUseEditText);
            procUseEditText.setText(params[0]);
        }


    }

}
