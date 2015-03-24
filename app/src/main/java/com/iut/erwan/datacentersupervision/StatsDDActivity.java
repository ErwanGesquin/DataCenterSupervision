package com.iut.erwan.datacentersupervision;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
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

    private ClientSQLmetier clientBDD;

    private ArrayUsageDDAdapter arrayDDAdapt;
    private ArrayList<UsageDD> arrayDD = new ArrayList<UsageDD>();
    private ListView listeView;

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
    }
}
