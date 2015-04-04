package com.iut.erwan.datacentersupervision;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StatsDDActivity extends ActionBarActivity {
    static final private int MENU_PREFERENCES = Menu.FIRST;
    static final private int CODE_REQUETE_PREFERENCES = 1;
    static final private String LISTE_TEMP_KEY = "LISTE_DD_KEY";
    static final private String ARRAY_TEMP_KEY = "ARRAY_DD_KEY";
    static final private String TABLE_DD = "Table Disques Durs";
    static final public String PARAM_SOURCE = "Table des disques";

    private ClientSQLmetier clientBDD;

    private ArrayUsageDDAdapter arrayDDAdapt;
    private ArrayList<UsageDD> arrayDD = new ArrayList<UsageDD>();
    private ListView listeView;
    private ArrayList<Integer> dd_vals = new ArrayList<Integer>();

    private Button plotDDBtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dd_stats);

        this.listeView = (ListView)this.findViewById(R.id.listViewDD);
        int layoutID = R.layout.item_perso_temp;
        if(savedInstanceState!=null) {
            this.arrayDD = (ArrayList<UsageDD>) savedInstanceState.getSerializable(StatsDDActivity.TABLE_DD);
        }
        arrayDDAdapt = new ArrayUsageDDAdapter(this,layoutID,arrayDD);
        this.listeView.setAdapter(arrayDDAdapt);

        plotDDBtn = (Button) findViewById(R.id.btnPlotTemp);

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

        arrayDD.clear();
        Thread t = new Thread( new Runnable()
        {
            public void run()
            {
                try{
                    ResultSet res = clientBDD.getTableUsageDD(6);

                    while(res.next()){
                        final UsageDD DD = new UsageDD(res.getString(1),res.getInt(2),res.getLong(3),res.getLong(4));
                        arrayDD.add(DD);
                        dd_vals.add(DD.usage);
                    }
                    res.close();
                    runOnUiThread(new Runnable()
                    {
                        public void run()
                        {
                            arrayDDAdapt.notifyDataSetChanged();
                        }
                    });
                }catch(java.sql.SQLException e){
                    e.printStackTrace();
                }

            }
        });
        t.start();

        plotDDBtn = (Button) findViewById(R.id.graphDDBtn);
        plotDDBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StatsDDActivity.this, PlotDDActivity.class);
                intent.putIntegerArrayListExtra(PARAM_SOURCE, dd_vals);
                startActivity(intent);

            }
        });
    }
}
