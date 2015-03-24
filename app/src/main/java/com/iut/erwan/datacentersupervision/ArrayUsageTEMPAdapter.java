package com.iut.erwan.datacentersupervision;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ArrayUsageTEMPAdapter extends ArrayAdapter<TEMP>{
    private int item_id;
    private Context context;
    private ArrayList<TEMP> objets;

    //Surcharge du constructeur ArrayAdapteur
    public ArrayUsageTEMPAdapter(Context context, int item_id, ArrayList<TEMP> objets){
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
            v = inflater.inflate(R.layout.item_perso_temp, null);
        }

        // Lire l'objet courant
        TEMP fcourant = objets.get(position);
        if (fcourant != null) {
            // récupérer les instances des contr�les de l'item
            TextView tv_adate = (TextView) v.findViewById(R.id.adate);
            TextView tv_temp = (TextView) v.findViewById(R.id.temp);
            TextView tv_nomBaie = (TextView) v.findViewById(R.id.nomBaie);


            // Assigner les contr�les avec les valeurs de l'objet courant
            if (tv_adate != null){
                tv_adate.setText(fcourant.adate);
            }
            if (tv_temp != null){
                tv_temp.setText(fcourant.temp);
            }
            if (tv_nomBaie != null){
                tv_nomBaie.setText(fcourant.nomBaie);
            }

        }
        // retourne la vue du layout item � dessiner
        return v;


    }
}
