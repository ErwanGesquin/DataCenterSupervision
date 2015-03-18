package com.iut.erwan.datacentersupervision;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity{

    //variable pour la vue préférences
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

    private Button discUsageBtn;
    private Button discStatBtn;
    private Button processorUsageBtn;
    private Button processorStatsBtn;
    private Button baieTempBtn;
    private Button statBaieTempBtn;
    private EditText DDEditText;
    private EditText procUseEditText;


    SnmpGetTaskSonde task;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        discUsageBtn = (Button) findViewById(R.id.discUsageBtn);
        discStatBtn = (Button) findViewById(R.id.discStatBtn);
        processorUsageBtn = (Button) findViewById(R.id.processorUsageBtn);
        processorStatsBtn = (Button) findViewById(R.id.processorStatsBtn);
        baieTempBtn = (Button) findViewById(R.id.baieTempBtn);
        statBaieTempBtn = (Button) findViewById(R.id.statBaieTempBtn);
        DDEditText = (EditText) findViewById(R.id.DDEditText);
        procUseEditText = (EditText) findViewById(R.id.procUseEditText);




        baieTempBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task = new SnmpGetTaskSonde(MainActivity.this, ip1, Integer.parseInt(port1));
            }
        });

    }


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

}
