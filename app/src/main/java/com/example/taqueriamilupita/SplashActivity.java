package com.example.taqueriamilupita;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.EditText;

/**
 * clase que va a iniciar la aplicacion para mostrar una imagen
 * (Splash screen)
 */
public class SplashActivity extends AppCompatActivity {

    //constructor de la clase
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //se selecciona un layout de los layout creaos, primero se crea uno por default
        setContentView(R.layout.activity_splash);

        //hondler que va a posponer el inicio de la actividad main
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //intento es usado para crear una nueva actividad
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                //se inicia la actividad
                startActivity(intent);
                //se termina esta
                finish();

            }// fin del run


        },3000);//fin de handler y tiempo que tarda en abrir


    }//fin del constructor



}//fin de la clase
