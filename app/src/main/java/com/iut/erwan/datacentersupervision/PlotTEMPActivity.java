package com.iut.erwan.datacentersupervision;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.androidplot.xy.BoundaryMode;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.PointLabelFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by erwan on 24/03/15.
 */
public class PlotTEMPActivity extends Activity{

    private XYPlot plot;

    public PlotTEMPActivity(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);

        Intent intent = getIntent();
        final ArrayList<String> param_recu = intent.getStringArrayListExtra(StatsTEMPActivity.PARAM_SOURCE);

        setContentView(R.layout.activity_plot_temp);

        plot = (XYPlot) findViewById(R.id.activity_plot_temp);

        Number[] series1Numbers = convert(param_recu);

        XYSeries series1 = new SimpleXYSeries(Arrays.asList(series1Numbers), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Serie1");
        plot.setRangeBoundaries(0, 30, BoundaryMode.FIXED);

        LineAndPointFormatter serie1format = new LineAndPointFormatter();
        serie1format.setPointLabelFormatter(new PointLabelFormatter());
        serie1format.configure(getApplicationContext(), R.xml.test_serie_1_format);

        plot.addSeries(series1, serie1format);

    }

    protected void onSavedInstance(Bundle savedInstanceState){

    }

    public Number[] convert (ArrayList<String> list) {
        int i = 0;
        Number[] ret = new Number[list.size()];
        for (String temp : list) {
            ret[i] = Double.parseDouble(temp);
            i++;
        }
        return ret;
    }

}
