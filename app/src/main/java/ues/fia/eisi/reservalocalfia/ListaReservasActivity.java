package ues.fia.eisi.reservalocalfia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
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
    int identificador;
    //audio
    MediaPlayer Media;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_reservas);
        helper=new ControlReserveLocal(this);
        btnExportar = findViewById(R.id.btnExportar);
        //audio
        Media=MediaPlayer.create(getApplicationContext(), R.raw.tono);

        listaReservas = new ArrayList<ReservaEvento>();
        nombreReservas = new ArrayList<String>();


        listReservas=(ListView) findViewById(R.id.listReservas);

        listaReservas.addAll(helper.consultarReservas());
        actualizarListView();
        listReservas.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent ints=new Intent(view.getContext(), ReservaEventoConsultarActivity.class);
                        for (int i = 0; i < listaReservas.size(); i++) {
                            identificador =listaReservas.get(i).getIdReservaEvento();
                            //ENVIANDO IDENTIFICADOR A ACTIVITY CONSULTAR RESERVA
                            Bundle bundle = new Bundle();
                            bundle.putInt("evento",identificador);
                            ints.putExtras(bundle);
                        }
                        startActivity(ints);
                    }
                }
        );
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
            Media.start();

        } catch (Exception e) { }

    }

}
