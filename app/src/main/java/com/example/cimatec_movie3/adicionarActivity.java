package com.example.cimatec_movie3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class adicionarActivity extends AppCompatActivity {
    private Button button2;
    private EditText txtNum, txtNome, txtAno, txtMat;
    private Movie filme;
    private DatabaseReference reference=FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar);
        DatabaseReference movies = reference.child("movie");

        txtNum = findViewById(R.id.editTxtNum);
        int nFilme = Integer.parseInt(txtNum.getText().toString());

        txtAno = findViewById(R.id.editTxtAno);
        txtNome = findViewById(R.id.editTxtNome);
        txtMat = findViewById(R.id.editTxtMat);

        button2= findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filme.setAno(Integer.parseInt(txtAno.getText().toString()));
                filme.setNome(txtNome.getText().toString());
                filme.setCurtida(0);
                String matricula = txtMat.getText().toString();

                switch (nFilme){
                    case 1:
                        movies.child(matricula).child("filme1").setValue(filme);
                        break;
                    case 2:
                        movies.child(matricula).child("filme2").setValue(filme);
                        break;
                    case 3:
                        movies.child(matricula).child("filme3").setValue(filme);
                        break;
                    case 4:
                        movies.child(matricula).child("filme4").setValue(filme);
                        break;
                    default:
                        movies.child(matricula).child("filme4").setValue(filme);
                        break;
                }
            }
        });
    }
}