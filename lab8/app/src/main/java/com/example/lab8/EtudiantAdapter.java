package com.example.lab8;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import com.example.lab8.beans.Etudiant;

public class EtudiantAdapter extends ArrayAdapter<Etudiant> {

    public EtudiantAdapter(@NonNull Context context, @NonNull List<Etudiant> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_etudiant, parent, false);
        }

        Etudiant e = getItem(position);

        TextView tvName = convertView.findViewById(R.id.tvName);
        TextView tvInfo = convertView.findViewById(R.id.tvInfo);

        // Affichage avec tes noms +h1
        tvName.setText(e.getLastNameh1() + " " + e.getFirstNameh1());
        tvInfo.setText("Ville: " + e.getCityh1() + " | Sexe: " + e.getGenderh1() + " | id=" + e.getId());

        return convertView;
    }
}
