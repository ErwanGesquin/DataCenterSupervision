package com.iut.erwan.datacentersupervision;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;

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
    }

}
