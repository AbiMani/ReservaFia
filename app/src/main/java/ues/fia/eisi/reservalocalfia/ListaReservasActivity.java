package ues.fia.eisi.reservalocalfia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class ListaReservasActivity extends AppCompatActivity {
    static List<ReservaEvento> listaReservas;
    static List<String> nombreReservas;
    ListView listReservas;
    ControlReserveLocal helper;
    Button btnExportar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_reservas);
        helper=new ControlReserveLocal(this);
        btnExportar = findViewById(R.id.btnExportar);

        listaReservas = new ArrayList<ReservaEvento>();
        nombreReservas = new ArrayList<String>();

        listReservas=(ListView) findViewById(R.id.listReservas);

        listaReservas.addAll(helper.consultarReservas());
        actualizarListView();
        pedirPermisos();
        btnExportar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exportar();
            }
        });
    }

    private void actualizarListView() {
        String dato = "";
        nombreReservas.clear();
        for (int i = 0; i < listaReservas.size(); i++) {
            dato ="Identificador: " +listaReservas.get(i).getIdReservaEvento()+ "\nEvento: "+listaReservas.get(i).getNombreTipoEvento()+"\nFecha: "+listaReservas.get(i).getFechaReservaEvento()+"\nLocal: " + listaReservas.get(i).getLocal()+
                    "\nEstado : "+listaReservas.get(i).getEstado();

            nombreReservas.add(dato);
        }
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombreReservas);
        listReservas.setAdapter(adaptador);
    }

    public void pedirPermisos() {
        // PERMISOS PARA ANDROID 6 O SUPERIOR
        if(ContextCompat.checkSelfPermission(ListaReservasActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(
                    ListaReservasActivity.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    0
            );

        }
    }
    private void exportar(){
        File carpeta = new File(Environment.getExternalStorageDirectory() + "/ExportarSQLiteCSV");
        String archivoAgenda = carpeta.toString() + "/" + "ReservaPendientes.csv";

        boolean isCreate = false;
        if(!carpeta.exists()) {
            isCreate = carpeta.mkdir();
        }

        try {
            FileWriter fileWriter = new FileWriter(archivoAgenda);

            if(listaReservas != null && listaReservas.size() != 0) {


                for(int i = 0; i < listaReservas.size(); i++){
                    fileWriter.append(String.valueOf(listaReservas.get(i).getIdReservaEvento()));
                    fileWriter.append(",");
                    fileWriter.append(listaReservas.get(i).getCodigoEscuela());
                    fileWriter.append(",");
                    fileWriter.append(listaReservas.get(i).getNombreTipoEvento());
                    fileWriter.append(",");
                    fileWriter.append(listaReservas.get(i).getFechaReservaEvento());
                    fileWriter.append(",");
                    fileWriter.append(listaReservas.get(i).getLocal());
                    fileWriter.append(",");
                    fileWriter.append(listaReservas.get(i).getEstado());
                    fileWriter.append("\n");


                }

            } else {
                Toast.makeText(ListaReservasActivity.this, "No hay registros.", Toast.LENGTH_LONG).show();
            }


            fileWriter.close();
            Toast.makeText(ListaReservasActivity.this, "SE CREO EL ARCHIVO CSV EXITOSAMENTE", Toast.LENGTH_LONG).show();

        } catch (Exception e) { }

    }

}
