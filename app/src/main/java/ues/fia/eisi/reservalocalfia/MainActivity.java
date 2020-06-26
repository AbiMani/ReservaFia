package ues.fia.eisi.reservalocalfia;


import android.app.ListActivity;
import android.content.Intent;
//import androidx.support.v7.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ControlReserveLocal BDhelper;
    ControlReserveLocal conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BDhelper= new ControlReserveLocal(this);

        //INSTANCIACION de variables
        conn = new ControlReserveLocal(this);

        //REDIRRECION de activity
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

        conn.abrir();
        conn.llenarBD();
        conn.cerrar();

    }

}

