package com.example.cimatec_movie3;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
    private Button btnadd;
    private ListView lstview;
    private List<String> matriculas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnadd = findViewById(R.id.btnadd);
        lstview = findViewById(R.id.lstview2);
        matriculas = new ArrayList<>();

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), adicionarActivity.class);
                startActivity(intent);
            }
        });

        DatabaseReference movies = reference.child("movie");

        movies.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot matriculaSnapshot : dataSnapshot.getChildren()) {
                    String matricula = matriculaSnapshot.getKey();
                    matriculas.add(matricula);
                }
                updateList();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void updateList() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, matriculas);
        lstview.setAdapter(adapter);

        lstview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String matricula = matriculas.get(position);
                Intent intent = new Intent(MainActivity.this, FilmesActivity.class);
                intent.putExtra("matricula", matricula);
                startActivity(intent);
            }
        });
    }
}
