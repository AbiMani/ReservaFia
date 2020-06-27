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

        listReservas=(ListView) findViewById(R.id.listReservas);
        listaReservas.addAll(helper.consultarReservas());
        actualizarListView();
        listReservas.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent ints=new Intent(view.getContext(), ReservaEventoConsultarActivity.class);
                        startActivity(ints);
                    }
                }
        );
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

}
