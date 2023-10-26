package com.example.cimatec_movie3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CurtirActivity extends AppCompatActivity {

    private Button btVoltar, btCurtir;
    private TextView nomeFilme, ano, curtidas;
    private Movie filme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curtir);


        String id = getIntent().getStringExtra("id");
        String matricula = getIntent().getStringExtra("matricula");
        DatabaseReference filmeReference = FirebaseDatabase.getInstance().getReference().child(matricula).child(id);
        filmeReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                filme = snapshot.getValue(Movie.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        nomeFilme = findViewById(R.id.nomeFilme);
        ano = findViewById(R.id.ano);
        curtidas = findViewById(R.id.txtCurtidas);

        nomeFilme.setText(filme.getNome());
        ano.setText("Lançamento: "+filme.getAno());
        curtidas.setText(filme.getCurtida()+" curtidas");


        btVoltar = findViewById(R.id.btVoltar);
        btCurtir = findViewById(R.id.btCurtir);
        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent voltar = new Intent(getApplicationContext(), FilmesActivity.class);
                startActivity(voltar);
            }
        });

        btCurtir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int like = filme.getCurtida();
                int novoLike = like+1;
                filmeReference.child("curtidas").setValue(novoLike);
                curtidas.setText(novoLike+" curtidas");
            }
        });
    }
}