package com.iut.erwan.datacentersupervision;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ArrayUsageDDAdapter extends ArrayAdapter<UsageDD> {
    private int item_id;
    private Context context;
    private ArrayList<UsageDD> objets;

    //Surcharge du constructeur ArrayAdapteur
    public ArrayUsageDDAdapter(Context context, int item_id, ArrayList<UsageDD> objets){
        super(context, item_id, objets);
        this.objets = objets;
    }

    // Retourne l'objet View, dessin de l'item(position) dans la vue liste
    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        // Vue � convertir
        View v = convertView;

        // Si la vue est null (non encore dessin�e) La dessiner
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_perso_dd, null);
        }

        // Lire l'objet courant
        UsageDD fcourant = objets.get(position);
        if (fcourant != null) {
            // récupérer les instances des contr�les de l'item
            TextView tv_adate = (TextView) v.findViewById(R.id.adate);
            TextView tv_usage = (TextView) v.findViewById(R.id.usage);
            TextView tv_capacite = (TextView) v.findViewById(R.id.capacite);
            TextView tv_utilise = (TextView) v.findViewById(R.id.utilise);


            // Assigner les contr�les avec les valeurs de l'objet courant
            if (tv_adate != null){
                tv_adate.setText(fcourant.adate);
            }
            if (tv_usage != null){
                tv_usage.setText(fcourant.usage);
            }
            if (tv_capacite != null){
                tv_capacite.setText(fcourant.capacite);
            }
            if (tv_utilise != null){
                tv_utilise.setText(fcourant.utilise);
            }

        }
        // retourne la vue du layout item � dessiner
        return v;
    }
}
