package ues.fia.eisi.reservalocalfia;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class ReservaEventoInsertarActivity extends Activity {

    Spinner comboTiposEventos;
    EditText editCodigoEscuela;
    EditText editnombreEvento;
    EditText editCapacidad;
    EditText editFechaEvento;
    ControlReserveLocal helper;
    ArrayList<String> items = new ArrayList<>();
    ArrayList<TipoEvento> list;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva_evento_insertar);

        helper = new ControlReserveLocal(this);
        list = helper.ListTipoEventos();
        //ArrayList<String> tipoEv= new ArrayList<>();
        items.add("Seleccione...");
        for (int i=0;i<list.size();i++){
            // tipoEvento = list.get(i);
            //items[i] = String.valueOf(tipoEvento.getIdTipoEvento());
            items.add(list.get(i).getIdTipoEvento());
        }
        editCodigoEscuela=(EditText) findViewById(R.id.editCodigoEscuela);
        comboTiposEventos= (Spinner) findViewById(R.id.comboTipoEventosInsertar);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        comboTiposEventos.setAdapter(adapter);
        editnombreEvento = (EditText) findViewById(R.id.editNombreEvento);
        editCapacidad=(EditText) findViewById(R.id.editCapacidad);
        editFechaEvento=(EditText) findViewById(R.id.editFecha);

    }
    public void insertarReservaEvento(View v) {

        String regInsertados;
        String codigoEscuela=editCodigoEscuela.getText().toString();
        String tipo_eventoId=comboTiposEventos.getSelectedItem().toString();
        String nombreEvento=editnombreEvento.getText().toString();
        String capacidadEvento=(editCapacidad.getText().toString());
        String fechaeEvento=editFechaEvento.getText().toString();

        ReservaEvento reservaEvento= new ReservaEvento();
        if (codigoEscuela.equals("") || fechaeEvento.equals("") || tipo_eventoId.equals("Seleccione...") || editCapacidad.getText().toString().equals("")){
            Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT ).show();
        }
        else {
            reservaEvento.setCodigoEscuela(codigoEscuela);
            reservaEvento.setIdTipoEvento((tipo_eventoId));
            reservaEvento.setNombreEvento(nombreEvento);
            reservaEvento.setCapacidadTotalEvento(Integer.valueOf(capacidadEvento));
            reservaEvento.setFechaReservaEvento(fechaeEvento);

            helper.abrir();
            regInsertados = helper.insertar(reservaEvento);
            helper.cerrar();
            Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
        }
    }

    public void limpiarTexto(View v) {
        comboTiposEventos.setSelection(0);
        editCapacidad.setText("");
        editCodigoEscuela.setText("");
        editFechaEvento.setText("");
        editnombreEvento.setText("");
    }
}
