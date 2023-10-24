package com.example.cimatec_movie3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference reference= FirebaseDatabase.getInstance().getReference();
    private Button btnadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnadd = findViewById(R.id.btnadd);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(),adicionarActivity.class);
                startActivity(intent);

            }
        });


        DatabaseReference movies = reference.child("movie");

        Movie m = new Movie();
        m.setNome("rei leao");
        m.setAno(1998);
        m.setCurtida(10);
        movies.child("1t111").child("filme3").setValue(m);


        m.setNome("");
        m.setAno(1998);
        m.setCurtida(10);
        movies.child("1t111").child("filme2").setValue(m);
    }
}