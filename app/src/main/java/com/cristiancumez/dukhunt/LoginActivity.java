package com.cristiancumez.dukhunt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import common.Constantes;

public class LoginActivity extends AppCompatActivity {
    EditText editName;
    Button bStart;
    String nick;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editName = findViewById(R.id.editName);
        bStart =  findViewById(R.id.bStart);

        // cambiar tipo de fuentw
        Typeface typeface = Typeface.createFromAsset(getAssets(), "pixel.ttf");
        editName.setTypeface(typeface);
        bStart.setTypeface(typeface);

        //Evento click y cambiar de activyty
        bStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nick=editName.getText().toString();

                if(nick.isEmpty()) {
                    editName.setError("Porfavor Ingrese su Usuario");

                }else {
                    editName.setText("");
                    Intent i = new Intent(LoginActivity.this,GameActivity.class);
                    i.putExtra(Constantes.EXTRA_NICK, nick );
                    startActivity(i);
                }
            }
        });


    }
}