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

public class ConsultarCargaAcademicaActivity extends AppCompatActivity {
    ControlReserveLocal db;
    static List<CargaAcademica> listaCargaAcademica;
    static List<String> nombreCargaAcademica;
    EditText cicloTxt;
    ListView listViewCargaAcademica;
    private final String urlLocal = "http://192.168.1.6/ws_consultar_cargaacademica.php";
    private final String urlHostingGratuito = "https://reservalocalfia04.000webhostapp.com/ws_consultar_cargaacademica.php";
    private String urlPublicoUES = "https://eisi.fia.ues.edu.sv/eisi04/BV12004/ws_consultar_cargaacademica.php";

    @SuppressLint("NewApi")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_carga_academica);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        db = new ControlReserveLocal(this);
        listaCargaAcademica = new ArrayList<CargaAcademica>();
        nombreCargaAcademica = new ArrayList<String>();
        cicloTxt = (EditText) findViewById(R.id.editText_ciclo);
        listViewCargaAcademica = (ListView) findViewById(R.id.listView1);
    }

    public void servicioPHP(View v) {
        String ciclo = cicloTxt.getText().toString();
        String url = "";
        switch (v.getId()) {
            case R.id.button1:
                url = urlLocal + "?codigociclo=" + ciclo ;
                break;
            case R.id.button2:
                url = urlHostingGratuito +"?codigociclo=" + ciclo ;
                break;
            case R.id.button4:
                url = urlPublicoUES + "?codigociclo=" + ciclo ;
                break;
        }
        String cargaacademicaExternas = ControladorServicio.obtenerRespuestaPeticion(url, this);
        try {
            listaCargaAcademica.addAll(ControladorServicio.obtenerCargaAcademicaExterno(cargaacademicaExternas, this));
            actualizarListView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void guardar(View v) {
        db.abrir();
        for (int i = 0; i < listaCargaAcademica.size(); i++) {
            Log.v("guardar", db.insertar(listaCargaAcademica.get(i)));
        }
        db.cerrar();
        Toast.makeText(this, "Guardado con exito", Toast.LENGTH_LONG).show();
        listaCargaAcademica.removeAll(listaCargaAcademica);
        actualizarListView();
    }

    private void actualizarListView() {
        String dato = "";
        nombreCargaAcademica.clear();
        for (int i = 0; i < listaCargaAcademica.size(); i++) {
            dato= "Asignatura: " + listaCargaAcademica.get(i).getcodigoAsignatura() + "  Carnet Docente: " + listaCargaAcademica.get(i).getcarnetDocente()+ "  Rol Docente: " + listaCargaAcademica.get(i).getidRolDocente();
            nombreCargaAcademica.add(dato);
        }
        eliminarElementosDuplicados();
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombreCargaAcademica);
        listViewCargaAcademica.setAdapter(adaptador);
    }

    private void eliminarElementosDuplicados() {
        HashSet<CargaAcademica> conjuntoCargaAca = new HashSet<CargaAcademica>();
        conjuntoCargaAca.addAll(listaCargaAcademica);
        listaCargaAcademica.clear();
        listaCargaAcademica.addAll(conjuntoCargaAca);
        HashSet<String> conjuntoNombre = new HashSet<String>();
        conjuntoNombre.addAll(nombreCargaAcademica);
        nombreCargaAcademica.clear();
        nombreCargaAcademica.addAll(conjuntoNombre);
    }
}
