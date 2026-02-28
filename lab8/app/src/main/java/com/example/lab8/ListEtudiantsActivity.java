package com.example.lab8;
import com.android.volley.toolbox.JsonArrayRequest;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.example.lab8.beans.Etudiant;

public class ListEtudiantsActivity extends AppCompatActivity {

    private ListView listView;
    private Button btnRefresh;
    private RequestQueue queue;

    private ArrayList<Etudiant> data = new ArrayList<>();
    private EtudiantAdapter adapter;
    private static final String BASE = "http://10.0.2.2:80";

    private static final String loadUrl   = BASE + "/lab8/ws/loadStudent.php";
    private static final String deleteUrl = BASE + "/lab8/ws/deleteStudent.php";
    private static final String updateUrl = BASE + "/lab8/ws/updateStudent.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_etudiants);

        listView = findViewById(R.id.listEtudiants);
        btnRefresh = findViewById(R.id.btnRefresh);


        queue = Volley.newRequestQueue(this);

        adapter = new EtudiantAdapter(this, data);
        listView.setAdapter(adapter);

        btnRefresh.setOnClickListener(v -> loadEtudiants());

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Etudiant selected = data.get(position);
            showActionsPopup(selected);
        });

        loadEtudiants(); // auto load
    }

    private void loadEtudiants() {
        StringRequest req = new StringRequest(Request.Method.GET, loadUrl,
                response -> {
                    Log.d("RESPONSE", response);

                    Type type = new TypeToken<Collection<Etudiant>>(){}.getType();
                    Collection<Etudiant> list = new Gson().fromJson(response, type);

                    data.clear();
                    data.addAll(list);
                    adapter.notifyDataSetChanged();
                },
                error -> {
                    Log.e("VOLLEY", "Load error", error);

                    if (error.networkResponse != null) {
                        Log.e("VOLLEY", "statusCode=" + error.networkResponse.statusCode);
                    } else {
                        Log.e("VOLLEY", "networkResponse is null (NoConnection / timeout)");
                    }

                    Toast.makeText(this, "Load error: " + error.toString(), Toast.LENGTH_LONG).show();
                }
        );

        queue.add(req);
    }

    private void showActionsPopup(Etudiant e) {
        String[] options = {"Modifier", "Supprimer"};
        new AlertDialog.Builder(this)
                .setTitle("Choisir une action (id=" + e.getId() + ")")
                .setItems(options, (dialog, which) -> {
                    if (which == 0) showEditPopup(e);
                    if (which == 1) confirmDelete(e);
                })
                .show();
    }

    private void confirmDelete(Etudiant e) {
        new AlertDialog.Builder(this)
                .setTitle("Confirmation")
                .setMessage("Supprimer cet étudiant ? (id=" + e.getId() + ")")
                .setPositiveButton("Oui", (d, w) -> deleteEtudiant(e.getId()))
                .setNegativeButton("Non", null)
                .show();
    }

    private void deleteEtudiant(int id) {
        StringRequest req = new StringRequest(Request.Method.POST, deleteUrl,
                response -> {
                    // on reçoit la liste mise à jour -> refresh direct
                    Type type = new TypeToken<Collection<Etudiant>>(){}.getType();
                    Collection<Etudiant> list = new Gson().fromJson(response, type);

                    data.clear();
                    data.addAll(list);
                    adapter.notifyDataSetChanged();
                },
                error -> Toast.makeText(this, "Delete error: " + error.toString(), Toast.LENGTH_LONG).show()
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> p = new HashMap<>();
                p.put("id", String.valueOf(id));
                return p;
            }
        };

        queue.add(req);
    }

    private void showEditPopup(Etudiant e) {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_edit_etudiant, null);

        EditText edNom = dialogView.findViewById(R.id.edNom);
        EditText edPrenom = dialogView.findViewById(R.id.edPrenom);
        Spinner spVille = dialogView.findViewById(R.id.spVille);
        RadioButton rbH = dialogView.findViewById(R.id.rbH);
        RadioButton rbF = dialogView.findViewById(R.id.rbF);

        // Pré-remplir
        edNom.setText(e.getLastNameh1());
        edPrenom.setText(e.getFirstNameh1());
        // Spinner: on laisse tel quel si tu veux simple (sinon je te montre comment sélectionner la ville)
        if ("homme".equalsIgnoreCase(e.getGenderh1())) rbH.setChecked(true); else rbF.setChecked(true);

        new AlertDialog.Builder(this)
                .setTitle("Modifier (id=" + e.getId() + ")")
                .setView(dialogView)
                .setPositiveButton("Enregistrer", (d, w) -> {
                    String gender = rbH.isChecked() ? "homme" : "femme";
                    updateEtudiant(
                            e.getId(),
                            edNom.getText().toString(),
                            edPrenom.getText().toString(),
                            spVille.getSelectedItem().toString(),
                            gender
                    );
                })
                .setNegativeButton("Annuler", null)
                .show();
    }

    private void updateEtudiant(int id, String nom, String prenom, String ville, String sexe) {
        StringRequest req = new StringRequest(Request.Method.POST, updateUrl,
                response -> {
                    Type type = new TypeToken<Collection<Etudiant>>(){}.getType();
                    Collection<Etudiant> list = new Gson().fromJson(response, type);

                    data.clear();
                    data.addAll(list);
                    adapter.notifyDataSetChanged();
                },
                error -> Toast.makeText(this, "Update error: " + error.toString(), Toast.LENGTH_LONG).show()
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> p = new HashMap<>();
                p.put("id", String.valueOf(id));
                p.put("nom", nom);
                p.put("prenom", prenom);
                p.put("ville", ville);
                p.put("sexe", sexe);
                return p;
            }
        };

        queue.add(req);
    }


}