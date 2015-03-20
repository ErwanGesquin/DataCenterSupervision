package com.iut.erwan.datacentersupervision;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.EditText;

import com.adventnet.snmp.beans.SnmpTarget;

/**
 * Created by erwan on 19/03/15.
 */
public class SnmpGetTask extends AsyncTask<String, Integer, String[]> {

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
        if (context.isConnectingToInternet(context.getApplicationContext())) {
            this.context = context;
            this.host = host;
            this.port = port;
            this.community = community;
            this.disk = disk;
        } else {
            AlertDialog.Builder alertConn = new AlertDialog.Builder(context.getApplicationContext());
            alertConn.setTitle("Vous n'êtes pas connecté à internet");
            alertConn.show();
        }
    }


    @Override
    protected void onPreExecute(){
        progressBar = new ProgressDialog(context);
        progressBar.setMessage("Progression.");
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressBar.show();
    }

    @Override
    protected String[] doInBackground(String... params) {

        String[] res = null;
        this.target = new SnmpTarget();
        this.target.setTargetHost(host);
        this.target.setTargetPort(port);
        this.target.setCommunity(community);
        this.target.setSnmpVersion(SnmpTarget.VERSION1);
        if (disk){
            for (int i = 0; i < 4; i++) {
                this.target.addObjectID(String.valueOf(params[i]));
                res = this.target.snmpGetList();
            }

            res = this.target.snmpGetList();
        } else {
            for (int i = 0; i < 8; i++) {
                this.target.addObjectID(params[i]);
                res = this.target.snmpGetList();
            }
        }
        return res;
    }

    @Override
    protected void onPostExecute(String[] params){
        progressBar.dismiss();
        String procs = "";
        String dd = "";
        if(disk) {
            DDEditText = (EditText) context.findViewById(R.id.DDEditText);
            dd = params[2] + " / " + params[1] + " octets utilisés.";
            DDEditText.setText(dd);
        } else {
            procUseEditText = (EditText) context.findViewById(R.id.procUseEditText);
            for (int i = 0; i < 8; i++){
                if (i != 7) {
                    procs = procs + String.valueOf(params[i] + " % : ");
                } else {
                    procs = procs + String.valueOf(params[i] + " %.");
                }
            }

            procUseEditText.setText(procs);
        }
        progressBar.dismiss();

    }

}
