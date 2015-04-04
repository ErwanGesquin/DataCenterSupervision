package com.iut.erwan.datacentersupervision;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StatsMPActivity extends ActionBarActivity {
    static final private int MENU_PREFERENCES = Menu.FIRST;
    static final private int CODE_REQUETE_PREFERENCES = 1;
    static final private String LISTE_TEMP_KEY = "LISTE_MP_KEY";
    static final private String ARRAY_TEMP_KEY = "ARRAY_MP_KEY";
    static final private String TABLE_MP = "Table Processeurs";
    static final public String PARAM_SOURCE = "Table des proc";

    private ClientSQLmetier clientBDD;

    private ArrayUsageMPAdapter arrayMPAdapt;
    private ArrayList<UsageMP> arrayMP = new ArrayList<UsageMP>();
    private ListView listeView;
    private ArrayList<Integer> mp_vals = new ArrayList<Integer>();

    private Button plotMPBtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proc_stats);

        this.listeView = (ListView)this.findViewById(R.id.listViewProc);
        int layoutID = R.layout.item_perso_temp;
        if(savedInstanceState!=null) {
            this.arrayMP = (ArrayList<UsageMP>) savedInstanceState.getSerializable(StatsMPActivity.TABLE_MP);
        }
        arrayMPAdapt = new ArrayUsageMPAdapter(this,layoutID,arrayMP);
        this.listeView.setAdapter(arrayMPAdapt);

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

        arrayMP.clear();
        Thread t = new Thread( new Runnable()
        {
            public void run()
            {
                try{
                    ResultSet res = clientBDD.getTableUsageMP(6);

                    while(res.next()){
                        final UsageMP MP = new UsageMP(res.getString(1),res.getInt(2),res.getInt(3),res.getInt(4),res.getInt(5),res.getInt(6),res.getInt(7),res.getInt(8),res.getInt(9),res.getInt(10));
                        arrayMP.add(MP);
                        mp_vals.add(MP.mp1);
                        mp_vals.add(MP.mp2);
                        mp_vals.add(MP.mp3);
                        mp_vals.add(MP.mp4);
                        mp_vals.add(MP.mp5);
                        mp_vals.add(MP.mp6);
                        mp_vals.add(MP.mp7);
                        mp_vals.add(MP.mp8);
                    }
                    res.close();
                    runOnUiThread(new Runnable()
                    {
                        public void run()
                        {
                            arrayMPAdapt.notifyDataSetChanged();
                        }
                    });
                }catch(java.sql.SQLException e){
                    e.printStackTrace();
                }

            }
        });
        t.start();

        plotMPBtn = (Button) findViewById(R.id.Graphique);
        plotMPBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StatsMPActivity.this, PlotMPActivity.class);
                intent.putIntegerArrayListExtra(PARAM_SOURCE, mp_vals);
                startActivity(intent);

            }
        });
    }
}
