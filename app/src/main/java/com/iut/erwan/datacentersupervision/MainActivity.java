package com.iut.erwan.datacentersupervision;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity {

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
                task = new SnmpGetTaskSonde(MainActivity.this);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
