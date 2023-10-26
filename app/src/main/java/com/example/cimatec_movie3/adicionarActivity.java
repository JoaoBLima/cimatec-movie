package com.example.cimatec_movie3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class adicionarActivity extends AppCompatActivity {
    private Button button2;
    private Button btnvoltar2;
    private EditText txtNum, txtNome, txtAno, txtMat;
    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar);

        txtNum = findViewById(R.id.editTxtNum);
        txtAno = findViewById(R.id.editTxtAno);
        txtNome = findViewById(R.id.editTxtNome);
        txtMat = findViewById(R.id.editTxtMat);
        btnvoltar2 = findViewById(R.id.btnvoltar2);

        btnvoltar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });


        button2 = findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String numText = txtNum.getText().toString();
                if (!numText.isEmpty()) {
                    int nFilme = Integer.parseInt(numText);
                    DatabaseReference movies = reference.child("movie");
                    String matricula = txtMat.getText().toString();

                    // Consulta o banco de dados para obter o número do próximo filme
                    movies.child(matricula).get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
                        @Override
                        public void onSuccess(DataSnapshot dataSnapshot) {
                            long numFilmes = dataSnapshot.getChildrenCount();
                            String nomeFilme = "filme" + (numFilmes + 1);
                            Movie filme = new Movie();
                            filme.setAno(Integer.parseInt(txtAno.getText().toString()));
                            filme.setNome(txtNome.getText().toString());
                            filme.setCurtida(0);

                            // Salva o filme com o novo nome
                            movies.child(matricula).child(nomeFilme).setValue(filme);
                        }
                    });
                } else {

                }
            }
        });
    }
}
