package ues.fia.eisi.reservalocalfia;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ConsultarReservasActivity extends AppCompatActivity {

    ControlReserveLocal db;
    static List<ReservaEvento> listaReservas;
    static List<String> nombreReservas;
    ListView listViewReservas;
    private final String urlHostingGratuito = "https://reservalocalfia04.000webhostapp.com/reservas_query.php";
    private String urlPublicoUES = "https://eisi.fia.ues.edu.sv/eisi25/MQ25001/ws_db_materia_fecha.php";

    @SuppressLint("NewApi")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_reservas);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        db = new ControlReserveLocal(this);
        listaReservas = new ArrayList<ReservaEvento>();
        nombreReservas = new ArrayList<String>();
        listViewReservas = (ListView) findViewById(R.id.lstReservas);
    }

    public void servicioPHP(View v) {
        String url = "";
        switch (v.getId()) {
            case R.id.button1:
                url = urlHostingGratuito + "?";
                break;
            case R.id.button2:
                url = urlPublicoUES+"?";
                break;
        }
        String reservasExternas = ControladorServicio.obtenerRespuestaPeticion(url, this);
        try {
            listaReservas.addAll(ControladorServicio.obtenerReservasExterno(reservasExternas, this));
            actualizarListView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void guardar(View v) {
        db.abrir();
        for (int i = 0; i < listaReservas.size(); i++) {
            Log.v("guardar", db.insertar(listaReservas.get(i)));
        }
        db.cerrar();
        Toast.makeText(this, "Guardado con exito", Toast.LENGTH_LONG).show();
        listaReservas.removeAll(listaReservas);
        actualizarListView();
    }

    private void actualizarListView() {
        String dato = "";
        nombreReservas.clear();
        for (int i = 0; i < listaReservas.size(); i++) {
            dato = listaReservas.get(i).getIdReservaEvento() +"."+ " Codigo escuela: " + listaReservas.get(i).getCodigoEscuela() + " Descripción: " + listaReservas.get(i).getNombreEvento()+ " Capacidad:" + listaReservas.get(i).getCapacidadTotalEvento()
                    + " Fecha: " + listaReservas.get(i).getFechaReservaEvento();
            nombreReservas.add(dato);
        }
        eliminarElementosDuplicados();
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombreReservas);
        listViewReservas.setAdapter(adaptador);
    }

    private void eliminarElementosDuplicados() {
        HashSet<ReservaEvento> conjuntoReservas = new HashSet<ReservaEvento>();
        conjuntoReservas.addAll(listaReservas);
        listaReservas.clear();
        listaReservas.addAll(conjuntoReservas);
        HashSet<String> conjuntoNombre = new HashSet<String>();
        conjuntoNombre.addAll(nombreReservas);
        nombreReservas.clear();
        nombreReservas.addAll(conjuntoNombre);
    }
}
