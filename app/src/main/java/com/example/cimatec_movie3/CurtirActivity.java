package com.example.cimatec_movie3;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
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
    private String matricula, filmeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curtir);

        // Receba a matrícula e o ID do filme da FilmesActivity
        matricula = getIntent().getStringExtra("matricula");
        filmeId = getIntent().getStringExtra("id");

        DatabaseReference filmeReference = FirebaseDatabase.getInstance().getReference().child("movie").child(matricula).child(filmeId);
        filmeReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                filme = snapshot.getValue(Movie.class);

                if (filme != null) {
                    // Defina os campos de texto com os dados do filme
                    nomeFilme.setText(filme.getNome());
                    ano.setText("Lançamento: " + filme.getAno());
                    curtidas.setText(filme.getCurtida() + " curtidas");
                } else {
                    // Lide com o caso em que o filme não foi encontrado, talvez exibindo uma mensagem de erro.
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Trate erros, se necessário.
            }
        });

        nomeFilme = findViewById(R.id.nomeFilme);
        ano = findViewById(R.id.ano);
        curtidas = findViewById(R.id.txtCurtidas);

        btVoltar = findViewById(R.id.btVoltar);
        btCurtir = findViewById(R.id.btCurtir);

        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent voltar = new Intent(getApplicationContext(), FilmesActivity.class);
                voltar.putExtra("matricula", matricula); // Envie a matrícula de volta à FilmesActivity
                startActivity(voltar);
            }
        });

        btCurtir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (filme != null) {
                    int like = filme.getCurtida();
                    like += 1;
                    filmeReference.child("curtida").setValue(like);
                    curtidas.setText(like + " curtidas");
                }
            }
        });
    }
}
