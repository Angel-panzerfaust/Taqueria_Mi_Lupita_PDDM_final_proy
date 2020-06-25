package com.example.taqueriamilupita;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * clase que permitira realizar una orden y confirmarla para enviarla
 */
public class ordenar extends AppCompatActivity {
    //declacion de los elementos del xml que se usaran en esta clase
    TextView destino,MI1,MI2,MI3;
    Spinner tacosPapa,tacosCarne, tacosBistec;


    /**
     * serie de getters y setters planeados para los
     * elementos xml que se usaran
     * @return
     */
    public String getNombreLocal() {
        return nombreLocal;
    }

    public void setNombreLocal(String nombreLocal) {
        this.nombreLocal = nombreLocal;
    }

    public String getTelefonoLocal() {
        return telefonoLocal;
    }

    public void setTelefonoLocal(String telefonaLocal) {
        this.telefonoLocal = telefonaLocal;
    }

    public String getCalleLocal() {
        return calleLocal;
    }

    public void setCalleLocal(String calleLocal) {
        this.calleLocal = calleLocal;
    }

    public String getNumeroLocal() {
        return numeroLocal;
    }

    public void setNumeroLocal(String numeroLocal) {
        this.numeroLocal = numeroLocal;
    }

    public String getColoniaLocal() {
        return coloniaLocal;
    }

    public void setColoniaLocal(String coloniaLocal) {
        this.coloniaLocal = coloniaLocal;
    }
    public String getFinalMessage() {
        return finalMessage;
    }

    public void setFinalMessage(String finalMessage) {
        this.finalMessage = finalMessage;
    }

    //los string utilizados para almacenar los datos de las cajas e texto(cliente)
    private String nombreLocal;
    private String telefonoLocal;
    private String calleLocal;
    private String numeroLocal;
    private String coloniaLocal;
    //una variable que permitira almacenar un mensaje final
    // con seus datos y los de su compra
    private String finalMessage;


    /**
     * constructor de la clase
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //seleccion del layout
        setContentView(R.layout.activity_ordenar);

        //ubicacion de la caja de texto destino, muestra los datoos del cliente
        destino = (TextView) findViewById(R.id.txtDestination);

        //ubicacion de los spiners del xml
        tacosPapa = (Spinner) findViewById(R.id.spnTacDorPapa);
        tacosCarne = (Spinner) findViewById(R.id.spnTacorCar);
        tacosBistec = (Spinner) findViewById(R.id.spnTacBistec);

        //ubicacion de los text del xml que tienen la designacion de la orden
        MI1 = (TextView) findViewById(R.id.lblmenuItem1) ;
        MI2 = (TextView) findViewById(R.id.lblMenuItem2) ;
        MI3 = (TextView) findViewById(R.id.lblMenuItem3) ;

        //llamada al metodo de cargar referencias que va a cargar datos al textview Cliente
        cargarPreferencias();
    }

    /**
     * cargara los datos del cliente a un textview
     */
    private void cargarPreferencias()
    {
        //se llama al objeto preferences de shared preferences
        // para poder realizar una accion como un objeto
        SharedPreferences preferences = getSharedPreferences("Clientes", Context.MODE_PRIVATE);

        //crecion de atributos que van a guardae los datos
        String nombre = preferences.getString("nombre","no");
        String telefono = preferences.getString("telefono","no");
        String calle = preferences.getString("calle","no");
        String numero = preferences.getString("numero","no");
        String colonia = preferences.getString("colonia","no");


        //llamado a los setters de las variables para almacenar los datos del cliente localmente
        setNombreLocal(nombre);
        setTelefonoLocal(telefono);
        setCalleLocal(calle);
        setNumeroLocal(numero);
        setColoniaLocal(colonia);


        //textView se llene con las datos del cliente
        destino.setText(getResources().getString(R.string.ordrStringNombrejava)+" "+getNombreLocal()+"\n"+
                getResources().getString(R.string.ordrStringTelefonojava)+" "+getTelefonoLocal()+"\n"+
                getResources().getString(R.string.ordrStringCallejava)+" "+getCalleLocal()+"\n"+
                getResources().getString(R.string.ordrStringNumerojava)+" "+getNumeroLocal()+"\n"+
                getResources().getString(R.string.ordrStringColoniajava)+" "+getColoniaLocal()+"\n");




    }//fin del metodo


    /**
     * metodo que revisa si ningun spinner se ha seleccionado
     * @return
     */
    private boolean  verificaSpinner()
    {

        //boolean que regresara el resultado
        boolean verificacion = true;

        //valores enteros que se guardan para poder verificar en los if
        int papa = Integer.parseInt(tacosPapa.getSelectedItem().toString());
        int carne = Integer.parseInt(tacosCarne.getSelectedItem().toString());
        int bistec = Integer.parseInt(tacosBistec.getSelectedItem().toString());

        //if que revisa si los 3 spinners estan vacios
        if(papa==0&&carne==0&&bistec==0)
        {
            verificacion = false;
        }//fin del if
        //regresa la verificacion
        return  verificacion;
    }//fin del metodo

    /**
     * regeresa a el main para llenar los datos del cliente
     * @param v
     */
    public void editarDatos(View v)
    {
        //se hace un intent para regresar al main y se inicia
        Intent editClient = new Intent(ordenar.this, MainActivity.class);
        startActivity(editClient);
    }//fin del metodo

    /**
     * metodo para el boton ordenar del xml
     * @param v
     */
    public void  confirmar(View v)
    {
        //if que verifica el contenido de los spinners
        if (verificaSpinner()==false)
        {
           Toast toast =  Toast.makeText(this,getResources().getString(R.string.ordrStringToastmssgjava),Toast.LENGTH_LONG);
           toast.show();
        }//fin if

        //else si no se detecta algun problema con los spinners continuar a un alert dialoge
        else
        {

            confirmDialogDemo();
            //Intent intent = new Intent(ordenar.this,resultView.class);
            //startActivity(intent);
        }//fin else

    }//fin del metodo

    /**
     * metodo que realiza las acciones para confirma la orden llamada desde el boton ordenar
     * confirm dialog demo
     */
    private void confirmDialogDemo() {

        //se llama al objeto preferences de shared preferences
        // para poder realizar una accion como un objeto
        SharedPreferences preferences = getSharedPreferences("Clientes", Context.MODE_PRIVATE);

        //crecion de atributos que van a guardae los datos
        String nombre = preferences.getString("nombre","no");
        final String telefono = preferences.getString("telefono","no");
        String calle = preferences.getString("calle","no");
        String numero = preferences.getString("numero","no");
        String colonia = preferences.getString("colonia","no");

        //se obtiene los datos del spinner como cantidad de ordenes e tacos
        int papa = Integer.parseInt(tacosPapa.getSelectedItem().toString());
        int carne = Integer.parseInt(tacosCarne.getSelectedItem().toString());
        int bistec = Integer.parseInt(tacosBistec.getSelectedItem().toString());

        //decalracion de un tabulador
        String tab="\u0009\u0009\u0009\u0009";

        //declaracion de mensages con las ordenes para poder mostrar en diferentes idiomas
        String menuItem1 = getResources().getString(R.string.ordrAlertDiaMenu1java);// MI1.getText().toString();
        String menuItem2 = getResources().getString(R.string.ordrAlertDiaMenu2java);//MI2.getText().toString();
        String menuItem3 = getResources().getString(R.string.ordrAlertDiaMenu3java);//MI3.getText().toString();

        //inicio del mensaje del alert ialoge
         String message1 = getResources().getString(R.string.ordrAlertDiaCantidadjava)+tab
                 +getResources().getString(R.string.ordrAlertDiaOrdenjava)+tab
                 +getResources().getString(R.string.ordrAlertDiaImportejava)+"\n"+
                           "__________________________________\n\n";

         //los importes de las ordenes
         float orden1 = papa*40;
         float orden2 = carne*40;
         float orden3 = bistec*40;

         //Declaracion de los string de la mitad de el alert dialoge para
         //mostrar las orenes y su importe
         String message2 = papa+" ord."+tab+menuItem1+tab+"$"+orden1+"\n";
         String message3 = carne+" ord."+tab+menuItem2+tab+"$"+orden2+"\n";
         String message4 = bistec+" ord."+tab+menuItem3+tab+"$"+orden3+"\n";

         //importe total
         float total = (papa*40)+(carne*40) + (bistec*40);

         //string para declarar el total final en el alert dialoge
         String message5 = "\n"+tab+tab+tab+tab+tab+tab+"Total: " + total+"\n";

         //creacion e un string de los atos del cliente
         String message6 = (
                 getResources().getString(R.string.ordrStringNombrejava)+" "+getNombreLocal()+"\n"+
                 getResources().getString(R.string.ordrStringTelefonojava)+" "+getTelefonoLocal()+"\n"+
                 getResources().getString(R.string.ordrStringCallejava)+" "+getCalleLocal()+"\n"+
                 getResources().getString(R.string.ordrStringNumerojava)+" "+getNumeroLocal()+"\n"+
                 getResources().getString(R.string.ordrStringColoniajava)+" "+getColoniaLocal()+"\n");



         //se declara un string con el mensaje finala a a mostar en el alert
         String fMassage= message1;

         //verificacion que exista una orden para tacos dorados de papa
         if (papa!=0)
         {
             // se integra la oren de tacos a el mensaje final
             fMassage = fMassage+ message2;
         }//fin del if

         //verificacion que exista una orden para tacos dorados de carne
         if (carne!=0)
         {
             fMassage = fMassage + message3;
         }//fin del if

        //verificacion que exista una orden para tacos de bistec
         if (bistec!=0)
         {
             fMassage = fMassage + message4;
         }//fin del if

         //se incorpora al mensage final importe y datos del cliente
         fMassage = fMassage + message5;
         fMassage = fMassage + message6;

         //se pasa el mensaje de este metodo a la variale statica mensaje final
         setFinalMessage(fMassage);

         //se crea un alert dialoge para mostrar el mensaje final
         AlertDialog.Builder builder = new AlertDialog.Builder(this);
         //titulo del alert
        builder.setTitle(getResources().getString(R.string.ordrAlertDiaConfirmMssgjava));
        //mensaje del alert
        builder.setMessage(fMassage);
        //evita que se cierre hasta escoger
        builder.setCancelable(false);
        //codigo del boton positivo
        builder.setPositiveButton(getResources().getString(R.string.ordrAlertDiaConfirmMssgPosjava), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //realizara el envio del mensaje a un cantacto de whatsapp
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+"+52"+getTelefonoLocal()+"&text="+getFinalMessage()));
                startActivity(intent);
            }//fin del on clik
        });//fin del evento del boton positivo

        //codigo del boton positivo
        builder.setNegativeButton(getResources().getString(R.string.ordrAlertDiaConfirmMssgNegjava), new DialogInterface.OnClickListener() {
            @Override
            //no se realizara nada y permanece en la app
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        //se muestra el alert
        builder.show();
    }//fin del metodo

}//fin de la clase
