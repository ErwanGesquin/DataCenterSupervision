package com.iut.erwan.datacentersupervision;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ArrayUsageMPAdapter extends ArrayAdapter<UsageMP> {

private int item_id;
private Context context;
private ArrayList<UsageMP> objets;

    //Surcharge du constructeur ArrayAdapteur
    public ArrayUsageMPAdapter(Context context, int item_id, ArrayList<UsageMP> objets){
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
        v = inflater.inflate(R.layout.item_perso_proc, null);
        }

        // Lire l'objet courant
        UsageMP fcourant = objets.get(position);
        if (fcourant != null) {
        // récupérer les instances des contr�les de l'item
            TextView tv_adate = (TextView) v.findViewById(R.id.adate);
            TextView tv_nbProcs = (TextView) v.findViewById(R.id.nbProcs);
            TextView tv_mp1 = (TextView) v.findViewById(R.id.mp1);
            TextView tv_mp2 = (TextView) v.findViewById(R.id.mp2);
            TextView tv_mp3 = (TextView) v.findViewById(R.id.mp3);
            TextView tv_mp4 = (TextView) v.findViewById(R.id.mp4);
            TextView tv_mp5 = (TextView) v.findViewById(R.id.mp5);
            TextView tv_mp6 = (TextView) v.findViewById(R.id.mp6);
            TextView tv_mp7 = (TextView) v.findViewById(R.id.mp7);
            TextView tv_mp8 = (TextView) v.findViewById(R.id.mp8);

            String nbProcs = Integer.toString(fcourant.nbProcs);
            String mp1 = Integer.toString(fcourant.mp1);
            String mp2 = Integer.toString(fcourant.mp2);
            String mp3 = Integer.toString(fcourant.mp3);
            String mp4 = Integer.toString(fcourant.mp4);
            String mp5 = Integer.toString(fcourant.mp5);
            String mp6 = Integer.toString(fcourant.mp6);
            String mp7 = Integer.toString(fcourant.mp7);
            String mp8 = Integer.toString(fcourant.mp8);

        // Assigner les contr�les avec les valeurs de l'objet courant
                if (tv_adate != null){
                tv_adate.setText(fcourant.adate);
                }
                if (tv_nbProcs != null){
                tv_nbProcs.setText(nbProcs);
                }
                if (tv_mp1 != null){
                    tv_mp1.setText(mp1);
                }
                if (tv_mp2 != null){
                    tv_mp2.setText(mp2);
                }
                if (tv_mp3 != null){
                    tv_mp3.setText(mp3);
                }
                if (tv_mp4 != null){
                    tv_mp4.setText(mp4);
                }
                if (tv_mp5 != null){
                    tv_mp5.setText(mp5);
                }
                if (tv_mp6 != null){
                    tv_mp6.setText(mp6);
                }
                if (tv_mp7 != null){
                    tv_mp7.setText(mp7);
                }
                if (tv_mp8 != null){
                    tv_mp8.setText(mp8);
                }
            }
        // retourne la vue du layout item � dessiner
            return v;
        }
}
