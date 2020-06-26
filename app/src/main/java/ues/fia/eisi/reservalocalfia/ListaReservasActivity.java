package ues.fia.eisi.reservalocalfia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListaReservasActivity extends AppCompatActivity {
    static List<ReservaEvento> listaReservas;
    static List<String> nombreReservas;
    ListView listReservas;
    ControlReserveLocal helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_reservas);
        helper=new ControlReserveLocal(this);

        listaReservas = new ArrayList<ReservaEvento>();
        nombreReservas = new ArrayList<String>();

        listReservas=(ListView) findViewById(R.id.listReservas);
       // helper.consultarReserva();
        //ArrayAdapter<CharSequence> adapterLista1=new ArrayAdapter(this,android.R.layout.simple_list_item_1, helper.lstReservas);
        //listReservas.setAdapter(adapterLista1);
        listaReservas.addAll(helper.consultarReservas());
        actualizarListView();
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
}
