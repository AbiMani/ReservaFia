package ues.fia.eisi.reservalocalfia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListaReservasActivity extends AppCompatActivity {
    static List<ReservaEvento> listaReservas;
    static List<String> nombreReservas;
    ListView listReservas;
    ControlReserveLocal helper;
    int identificador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_reservas);
        helper=new ControlReserveLocal(this);

        listaReservas = new ArrayList<ReservaEvento>();
        nombreReservas = new ArrayList<String>();

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
    }

    private void actualizarListView() {
        String dato = "";
        nombreReservas.clear();
        for (int i = 0; i < listaReservas.size(); i++) {
            dato = "Evento: "+listaReservas.get(i).getNombreTipoEvento()+"\nFecha: "+listaReservas.get(i).getFechaReservaEvento()+"\nLocal: " + listaReservas.get(i).getLocal()+
                    "\nDescripcion: " + listaReservas.get(i).getNombreEvento() + "\nEstado : "+listaReservas.get(i).getEstado();

            nombreReservas.add(dato);
        }
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombreReservas);
        listReservas.setAdapter(adaptador);
    }

    private void exportar(View v){

    }
}
