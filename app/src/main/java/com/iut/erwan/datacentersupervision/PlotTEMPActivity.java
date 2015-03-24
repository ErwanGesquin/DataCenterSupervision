package com.iut.erwan.datacentersupervision;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.PointLabelFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;

import java.util.Arrays;

/**
 * Created by erwan on 24/03/15.
 */
public class PlotTEMPActivity extends Activity{

    private String LISTE_TEMP_KEY;
    private XYPlot plot;

    public PlotTEMPActivity(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);

        setContentView(R.layout.activity_plot_temp);

        plot = (XYPlot) findViewById(R.id.activity_plot_temp);

        Number[] series1Numbers = {1, 8, 5, 2, 7, 4};
        Number[] series2Numbers = {4, 6, 3, 8, 2, 10};

        XYSeries series1 = new SimpleXYSeries(Arrays.asList(series1Numbers), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Serie1");
        XYSeries series2 = new SimpleXYSeries(Arrays.asList(series2Numbers), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Serie2");

        LineAndPointFormatter serie1format = new LineAndPointFormatter();
        serie1format.setPointLabelFormatter(new PointLabelFormatter());
        serie1format.configure(getApplicationContext(), R.xml.test_serie_1_format);

        plot.addSeries(series1, serie1format);

        LineAndPointFormatter serie2format = new LineAndPointFormatter();
        serie2format.setPointLabelFormatter(new PointLabelFormatter());
        serie2format.configure(getApplicationContext(), R.xml.test_serie_2_format);

        plot.addSeries(series2, serie2format);


    }

    protected void onSavedInstance(Bundle savedInstanceState){

    }

}