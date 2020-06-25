package com.example.taqueriamilupita;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.taqueriamilupita.R.string.mainCampoVaciojava;

/**
 * esta clase es donde se empieza a definir los datos del cliente
 */
public class MainActivity extends AppCompatActivity {
    //se obtiene los views de el archivo xml
    EditText nombre,telefono,calle,numero,colonia;

    /**
     * constructor de la clase main
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //declaracion de los views como variables locales para poder manipularlos
        nombre = (EditText) findViewById(R.id.txtNombre);
        telefono = (EditText)findViewById(R.id.txtTelefono);
        calle = (EditText) findViewById(R.id.txtCalle);
        numero = (EditText) findViewById(R.id.txtNumero);
        colonia = (EditText) findViewById(R.id.txtColonia);
        //test = (TextView) findViewById(R.id.txtTest);

        //metodo que obtiene las preferencias
        cargarPreferencias();
    }//fin del constructor

    /**
     * este metodo obtiene las preferencias de un objeto de guardado de informacion
     * Shared preferences con los datos del cliente
     */
    private void cargarPreferencias()
    {
        //se llama al objeto preferences de shared preferences
        // para poder realizar una accion de edicion como un objeto
        SharedPreferences preferences = getSharedPreferences("Clientes", Context.MODE_PRIVATE);

        //crecion de atributos que van a obtener  los datos guardados
        String nombre = preferences.getString("nombre","no");
        String telefono = preferences.getString("telefono","no");
        String calle = preferences.getString("calle","no");
        String numero = preferences.getString("numero","no");
        String colonia = preferences.getString("colonia","no");

        this.nombre.setText(nombre);
        this.telefono.setText(telefono);
        this.calle.setText(calle);
        this.numero.setText(numero);
        this.colonia.setText(colonia);

    }//fin del metodo

    /**
     * Este metodo se le asigna al bton de guardar del xml de esta clase, llama el metodo para
     * guardar los datos en las preferencias
     * @param v
     */
    public void agregar(View v)
    {
        //if que llama al metodo validar para no guardar hasta que los campos se llenen
        if (validar())
        {
            guardarPreferencias();
            Intent intenet = new Intent(MainActivity.this,ordenar.class);
            startActivity(intenet);

        }//fin if
    }//fin del metodo

    /**
     * metodo que valida los campos por si estan vacios
     * @return boolean
     */
    public boolean validar()
    {
        //creacion de strings para guardar los datos de los texz view de el xml
        boolean retorno = true;
        String nom;
        String tel;
        String cal;
        String num;
        String col;

        // se asignan esos valores pero tambien se recortan para no tener espacios
        nom = nombre.getText().toString().trim();
        tel = telefono.getText().toString().trim();
        cal = calle.getText().toString().trim();
        num = numero.getText().toString().trim();
        col = colonia.getText().toString().trim();


        //serie de if para verificar si algun campo esta vacio

        //if para verificar nombre
        if (nom.isEmpty())
        {
            //envia un mensaje de error al regresar al textview y como esta vacio se regresa falso
            nombre.setError(getResources().getString(mainCampoVaciojava));
            retorno = false;

        }//fin if

        //if para verificar nombre
        if (tel.isEmpty())
        {
            //envia un mensaje de error al regresar al textview y como esta vacio se regresa falso
            telefono.setError(getResources().getString(mainCampoVaciojava));
            retorno = false;
        }//fin if

        //if para verificar nombre
        if(cal.isEmpty())
        {
            //envia un mensaje de error al regresar al textview y como esta vacio se regresa falso
            calle.setError(getResources().getString(mainCampoVaciojava));
            retorno=false;
        }//fin if

        //if para verificar nombre
        if(num.isEmpty())
        {
            //envia un mensaje de error al regresar al textview y como esta vacio se regresa falso
            numero.setError(getResources().getString(mainCampoVaciojava));
            retorno = false;
        }//fin if

        //if para verificar nombre
        if(col.isEmpty())
        {
            //envia un mensaje de error al regresar al textview y como esta vacio se regresa falso
             colonia.setError(getResources().getString(mainCampoVaciojava));
            retorno = false;
        }//fin if

        //se regresa a un booleano retorno
        return retorno;
    }//fin de validar

    private void guardarPreferencias()
    {
        //declracion de un shared preference que servira
        //como un archivo de datos persistente dureante la ejecucion de la app
        SharedPreferences preferences = getSharedPreferences("Clientes", Context.MODE_PRIVATE);

        //serie de declaraciones de strings para
        //obtener los datos de las cajas de texto del xml

        String nombre1 = nombre.getText().toString();
        String telefono1 = telefono.getText().toString();
        String calle1 = calle.getText().toString();
        String numero1 = numero.getText().toString();
        String colonia1 = colonia.getText().toString();

        //se crea un objeto edit de shared prefernces para modificar a el shared
        // preference anterior
        SharedPreferences.Editor editor = preferences.edit();

        //editor eita los datos actuales con los obtenidos de las cajas de texto
        editor.putString("nombre", nombre1);
        editor.putString("telefono", telefono1);
        editor.putString("calle", calle1);
        editor.putString("numero", numero1);
        editor.putString("colonia", colonia1);

        //se guarda el cambio
        editor.commit();

    }//fin del metodo



}//fin de la clase
