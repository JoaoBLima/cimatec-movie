package com.example.cimatec_movie3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class FilmesActivity extends AppCompatActivity {
    private ListView lstview2;
    private Button btnvoltar;
    private List<Movie> filmesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filmes);
        lstview2 = findViewById(R.id.lstview2);
        btnvoltar = findViewById(R.id.btnvoltar);

        btnvoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });



        // Receba a matrícula da MainActivity
        String matricula = getIntent().getStringExtra("matricula");

        // Consulte o banco de dados para obter os filmes associados a essa matrícula
        DatabaseReference filmesRef = FirebaseDatabase.getInstance().getReference().child("movie").child(matricula);
        filmesRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                filmesList.clear();
                for (DataSnapshot filmeSnapshot : dataSnapshot.getChildren()) {
                    Movie filme = filmeSnapshot.getValue(Movie.class);
                    filmesList.add(filme);
                }

                updateList();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Trate erros, se necessário.
            }
        });
    }

    private void updateList() {
        ArrayAdapter<Movie> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, filmesList);
        lstview2.setAdapter(adapter);
    }
}
