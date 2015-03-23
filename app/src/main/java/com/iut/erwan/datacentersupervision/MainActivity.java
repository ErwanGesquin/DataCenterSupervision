package com.iut.erwan.datacentersupervision;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity{

    //variables pour la vue des préférences
    static final private int MENU_PREFERENCES = Menu.FIRST;
    static final private int CODE_REQUETE_PREFERENCES = 1;
    private String ip;
    private String port;
    private String username;
    private String password;
    private String ip1;
    private String port1;
    private String lec1;
    private String ip2;
    private String port2;
    private String lec2;

    //variables pour les composants de la vue principale
    private Button discUsageBtn;
    private Button discStatBtn;
    private Button processorUsageBtn;
    private Button processorStatsBtn;
    private Button baieTempBtn;
    private Button statBaieTempBtn;



    SnmpGetTaskSonde taskSonde;
    SnmpGetTask taskDDProc;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.updateAttributsFromPreferences();

        discUsageBtn = (Button) findViewById(R.id.discUsageBtn);
        discStatBtn = (Button) findViewById(R.id.discStatBtn);
        processorUsageBtn = (Button) findViewById(R.id.processorUsageBtn);
        processorStatsBtn = (Button) findViewById(R.id.processorStatsBtn);
        baieTempBtn = (Button) findViewById(R.id.baieTempBtn);
        statBaieTempBtn = (Button) findViewById(R.id.statBaieTempBtn);





            baieTempBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isConnectingToInternet(MainActivity.this)) {
                        String[] OIDS = new String[1];
                        OIDS[0] = ".1.3.6.1.4.1.21796.4.1.3.1.4.1";
                        taskSonde = new SnmpGetTaskSonde(MainActivity.this, ip2, Integer.parseInt(port2), lec2);
                        taskSonde.execute(OIDS);
                    } else {
                        AlertDialog.Builder alertConn = new AlertDialog.Builder(MainActivity.this);
                        alertConn.setTitle("Vous n'êtes pas connecté à internet");
                        alertConn.show();
                    }
                }
            });

            discUsageBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isConnectingToInternet(MainActivity.this)) {
                        String[] OIDS = new String[4];
                        OIDS[0] = ".1.3.6.1.2.1.25.2.3.1.4.1";
                        OIDS[1] = ".1.3.6.1.2.1.25.2.3.1.5.1";
                        OIDS[2] = ".1.3.6.1.2.1.25.2.3.1.6.1";
                        taskDDProc = new SnmpGetTask(MainActivity.this, ip1, Integer.parseInt(port1), lec1, true);
                        taskDDProc.execute(OIDS);
                    } else {
                        AlertDialog.Builder alertConn = new AlertDialog.Builder(MainActivity.this);
                        alertConn.setTitle("Vous n'êtes pas connecté à internet");
                        alertConn.show();
                    }

                }
            });

            processorUsageBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isConnectingToInternet(MainActivity.this)) {
                        String[] OIDS = new String[10];

                        for (int k = 2; k <= 9; k++) {
                            OIDS[k - 2] = String.valueOf(".1.3.6.1.2.1.25.3.3.1.2." + k);
                        }
                        taskDDProc = new SnmpGetTask(MainActivity.this, ip1, Integer.parseInt(port1), lec1, false);
                        taskDDProc.execute(OIDS);
                    } else {
                        AlertDialog.Builder alertConn = new AlertDialog.Builder(MainActivity.this);
                        alertConn.setTitle("Vous n'êtes pas connecté à internet");
                        alertConn.show();
                    }
                }
            });


            statBaieTempBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isConnectingToInternet(MainActivity.this)) {
                        Intent intent = new Intent(MainActivity.this, StatsTEMPActivity.class);
                        startActivity(intent);
                    } else {
                        AlertDialog.Builder alertConn = new AlertDialog.Builder(MainActivity.this);
                        alertConn.setTitle("Vous n'êtes pas connecté à internet");
                        alertConn.show();
                    }
                }
            });

            discStatBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isConnectingToInternet(MainActivity.this)) {
                        //content
                    } else {
                        AlertDialog.Builder alertConn = new AlertDialog.Builder(MainActivity.this);
                        alertConn.setTitle("Vous n'êtes pas connecté à internet");
                        alertConn.show();
                    }
                }
            });

            processorStatsBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isConnectingToInternet(MainActivity.this)) {
                        //content
                    } else {
                        AlertDialog.Builder alertConn = new AlertDialog.Builder(MainActivity.this);
                        alertConn.setTitle("Vous n'êtes pas connecté à internet");
                        alertConn.show();
                    }
                }
            });


    }

    //méthode pour les préférences
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, MENU_PREFERENCES, Menu.NONE, R.string.menu_preferences);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case (MENU_PREFERENCES): {
                Class c = SetPreferencesFragmentActivity.class;
                Intent i = new Intent(this, c);
                startActivityForResult(i, CODE_REQUETE_PREFERENCES);
                return true;
            }
        }
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODE_REQUETE_PREFERENCES){
            this.updateAttributsFromPreferences();
        }
    }

    private void updateAttributsFromPreferences(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        this.ip = prefs.getString( PreferencesFragments.PREFKEY_IPSERVEUR, "82.233.223.249");
        this.port = prefs.getString( PreferencesFragments.PREFKEY_PORTSERVEUR, "1433");
        this.username =  prefs.getString( PreferencesFragments.PREFKEY_USERNAME, "Supervision");
        this.password = prefs.getString( PreferencesFragments.PREFKEY_PASSWORD, "Password1234");

        this.ip1 = prefs.getString( PreferencesFragments.PREFKEY_IPAGENTV1, "82.233.223.249");
        this.port1 =  prefs.getString( PreferencesFragments.PREFKEY_PORTAGENTV1, "161");
        this.lec1 = prefs.getString( PreferencesFragments.PREFKEY_COMMUNAUTEDISC, "DataCenterVDR");

        this.ip2 = prefs.getString( PreferencesFragments.PREFKEY_IPAGENTV1_S, "82.233.223.249");
        this.port2 =  prefs.getString( PreferencesFragments.PREFKEY_PORTAGENTV1_S, "1610");
        this.lec2 = prefs.getString( PreferencesFragments.PREFKEY_COMMUNAUTESONDE, "DataCenterVDR");
    }

    public boolean isConnectingToInternet(Context context){
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null){
                for (int i = 0; i < info.length; i++){
                    if (info[i].getState() == NetworkInfo.State.CONNECTED){
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
