package com.example.cimatec_movie3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class adicionarActivity extends AppCompatActivity {
    private Button button2;
    private DatabaseReference reference=FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar);

        button2= findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (nFilme){
                    case : 1
                        //add
                    case : 2
                        //add
                    case : 3
                        //add
                    case : 4
                        //add
                }
            }
        });
    }
}