package com.example.lab8;
import com.example.lab8.beans.Etudiant;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;



public class AddEtudiant extends AppCompatActivity implements View.OnClickListener {

    private EditText nom, prenom;
    private Spinner ville;
    private RadioButton m, f;
    private Button add;
    private RequestQueue requestQueue;
    private Button btnListe;
    private static final String insertUrl =
            "http://http://10.0.2.2/lab8/ws/createStudent.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_etudiant);

        nom = findViewById(R.id.nom);
        prenom = findViewById(R.id.prenom);
        ville = findViewById(R.id.ville);
        m = findViewById(R.id.m);
        f = findViewById(R.id.f);
        add = findViewById(R.id.add);
        btnListe = findViewById(R.id.btnListe);

        requestQueue = Volley.newRequestQueue(this);
        add.setOnClickListener(this);
        btnListe.setOnClickListener(v -> {
            Intent intent = new Intent(AddEtudiant.this, ListEtudiantsActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onClick(View v) {
        if (v == add) {
            envoyerEtudiant();
        }
    }

    private void envoyerEtudiant() {

        StringRequest request = new StringRequest(Request.Method.POST, insertUrl,
                response -> {
                    Log.d("RESPONSE", response);

                    Type type = new TypeToken<Collection<Etudiant>>(){}.getType();
                    Collection<Etudiant> etudiants = new Gson().fromJson(response, type);

                    for (Etudiant e : etudiants) {
                        Log.d("ETUDIANT", e.toString());
                    }
                },
                error -> Log.e("VOLLEY", "Erreur : " + error.toString())
        ) {
            @Override
            protected Map<String, String> getParams() {
                String gender = m.isChecked() ? "homme" : "femme";

                Map<String, String> params = new HashMap<>();
                params.put("nom", nom.getText().toString());
                params.put("prenom", prenom.getText().toString());
                params.put("ville", ville.getSelectedItem().toString());
                params.put("sexe", gender);
                return params;
            }
        };

        requestQueue.add(request);
    }
}