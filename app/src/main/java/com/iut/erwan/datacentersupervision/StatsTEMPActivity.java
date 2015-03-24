package com.iut.erwan.datacentersupervision;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StatsTEMPActivity extends ActionBarActivity{
    static final private int MENU_PREFERENCES = Menu.FIRST;
    static final private int CODE_REQUETE_PREFERENCES = 1;
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
    private Button plotTempBtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_stats);

        this.listeView = (ListView)this.findViewById(R.id.listViewTemp);
        int layoutID = R.layout.item_perso_temp;
        if(savedInstanceState!=null) {
            this.arrayTEMP = (ArrayList<TEMP>) savedInstanceState.getSerializable(StatsTEMPActivity.TABLE_T);
        }
        arrayTEMPAdapt = new ArrayUsageTEMPAdapter(this,layoutID,arrayTEMP);
        this.listeView.setAdapter(arrayTEMPAdapt);

        try {
            this.clientBDD = new ClientSQLmetier("82.233.223.249", "1433", "Supervision", "supervision", "Password1234", 5);
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

        plotTempBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(StatsTEMPActivity.this, PlotTEMPActivity.class);
                    startActivity(intent);
            }
        });
    }
}
