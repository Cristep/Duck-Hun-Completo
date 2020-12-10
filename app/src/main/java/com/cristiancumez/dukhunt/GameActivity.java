package com.cristiancumez.dukhunt;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import common.Constantes;

public class GameActivity extends AppCompatActivity {
    TextView tvCountDuck,tvTime,tvNick;
    ImageView ivDuck;
    int counter = 0;
    int anchoPantalla;
    int altoPantalla;
    Random aleatorio;
    boolean gameOver = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        initviewComponents();
        eventos();
        initPantalla();

        initCuentaAtras();

    }

    private void initCuentaAtras() {
        // Cuenta atras
        new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {
                long segundosRestantes = millisUntilFinished / 1000;

                tvTime.setText(segundosRestantes + "s");
            }

            public void onFinish() {
                tvTime.setText("0s");
                gameOver = true;
                mostrarDialogoGameOver();
            }
        }.start();
    }

    private void mostrarDialogoGameOver() {
        // 1. Cree una instancia de un <code> <a href="/reference/android/app/AlertDialog.Builder.html"> AlertDialog.Builder </a> </code> con su constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);

        // 2.Encadena varios métodos de establecimiento para establecer las características del diálogo
        builder.setMessage("Has conseguido cazar " + counter + " patos")
                .setTitle("Game Over");
        // Add the buttons
        builder.setPositiveButton("Reiniciar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                counter = 0;
                tvCountDuck.setText("0");
                gameOver = false;

                initCuentaAtras();

            }
        });
        builder.setNegativeButton("Salir", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
                dialog.dismiss();
                finish();
            }
        });

        // 3. Get the <code><a href="/reference/android/app/AlertDialog.html">AlertDialog</a></code> from <code><a href="/reference/android/app/AlertDialog.Builder.html#create()">create()</a></code>
        AlertDialog dialog = builder.create();
        //Mostrar dialogo
        dialog.show();

    }

    private void initPantalla() {
        //1. Obtener el tamaño de la pantalla del celualr
        // en el que estamos jugando

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        anchoPantalla = size.x;
        altoPantalla = size.y;
        //2. Inicializamos el objeto para generar  numero aleatorio.
        aleatorio = new Random();
    }


    private void initviewComponents() {
        tvCountDuck = findViewById(R.id.tvCountDuck);
        tvTime = findViewById(R.id.tvTime);
        tvNick = findViewById(R.id.tvNick);
        ivDuck = findViewById(R.id.imageViewDuck);

        // cambiar tipo de fuentw
        Typeface typeface = Typeface.createFromAsset(getAssets(), "pixel.ttf");
        tvCountDuck.setTypeface(typeface);
        tvTime.setTypeface(typeface);
        tvNick.setTypeface(typeface);


        //ExtrAS obtener nick y setear
        Bundle extras = getIntent().getExtras();
        String nick = extras.getString(Constantes.EXTRA_NICK);
        tvNick.setText(nick);




    }
    //Con todo este codigo movemos al pato
    private void eventos() {
        ivDuck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!gameOver){

                }
                counter++;
                tvCountDuck.setText(String.valueOf(counter));
                ivDuck.setImageResource(R.drawable.duck_clicked);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ivDuck.setImageResource(R.drawable.duck);
                        moveDuck();
                    }
                },500);

            }
        });


    }

    private void moveDuck() {
        int  min = 0;
        int maximoX = anchoPantalla-ivDuck.getWidth();
        int maximoY = altoPantalla-ivDuck.getHeight();
        // Generamos 2 numeros aleatorios , uno para la cordenad
        // x y otro para la cordenada Y.
        int randomX = aleatorio.nextInt(((maximoX-min)+1)+min);
        int randomY = aleatorio.nextInt(((maximoY-min)+1)+min);
        // Utilizamos los numeros aleatorios para mover el pato
        // a esa posicion
        ivDuck.setX(randomX);
        ivDuck.setY(randomY);
    }

}
