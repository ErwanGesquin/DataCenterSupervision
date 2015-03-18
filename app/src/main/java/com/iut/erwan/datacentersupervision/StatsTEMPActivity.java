package com.iut.erwan.datacentersupervision;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ListView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StatsTEMPActivity extends ActionBarActivity{

    static final private String LISTE_TEMP_KEY = "LISTE_TEMP_KEY";
    static final private String ARRAY_TEMP_KEY = "ARRAY_TEMP_KEY";
    static final private String TABLE_T = "Table Temperatures";

    private String ip;
    private String port;
    private String username;
    private String password;
    private String bdd = "Supervision";
    private int timeout = 5;

    private ClientSQLmetier clientBDD;

    private ArrayUsageTEMPAdapter arrayTEMPAdapt;
    private ArrayList <TEMP> arrayTEMP = new ArrayList<TEMP>();
    private ListView listeView;

    private ProgressDialog dialogP;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_stats);
        this.updateAttributsFromPreferences();

        this.listeView = (ListView)this.findViewById(R.id.listViewTemp);
        int layoutID = R.layout.item_perso_temp;
        if(savedInstanceState!=null) {
            this.arrayTEMP = (ArrayList<TEMP>) savedInstanceState.getSerializable(StatsTEMPActivity.TABLE_T);
        }
        arrayTEMPAdapt = new ArrayUsageTEMPAdapter(this,layoutID,arrayTEMP);
        this.listeView.setAdapter(arrayTEMPAdapt);

        try {
            clientBDD = new ClientSQLmetier(this.ip, this.port,this.bdd, this.username, this.password, this.timeout);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        arrayTEMP.clear();
        Thread t = new Thread( new Runnable()
        {
            public void run()
            {
                try{
                    ResultSet res = clientBDD.getTableTEMP();

                    while(res.next()){
                        Log.i("Données SQL :", res.getString(3));
                        final TEMP T = new TEMP(res.getString(1),res.getString(2),res.getString(3));
                        runOnUiThread(new Runnable()
                        {
                            public void run()
                            {
                                arrayTEMP.add(T);
                                arrayTEMPAdapt.notifyDataSetChanged();
                            }
                        });
                    }
                    res.close();
                }catch(java.sql.SQLException e){
                    e.printStackTrace();
                }

            }
        });
        t.start();
    }

    private void updateAttributsFromPreferences(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        this.ip = prefs.getString( PreferencesFragments.PREFKEY_IPSERVEUR, "82.233.223.249");
        this.port = prefs.getString( PreferencesFragments.PREFKEY_PORTSERVEUR, "1433");
        this.username =  prefs.getString( PreferencesFragments.PREFKEY_USERNAME, "Supervision");
        this.password = prefs.getString( PreferencesFragments.PREFKEY_PASSWORD, "Password1234");
    }

}
