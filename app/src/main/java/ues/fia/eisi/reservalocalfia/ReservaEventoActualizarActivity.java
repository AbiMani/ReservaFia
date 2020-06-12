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

public class ReservaEventoActualizarActivity extends Activity {
    ControlReserveLocal helper;
    EditText editCodigoEscuela;
    EditText editnombreEvento;
    EditText editCapacidad;
    EditText editFechaEvento;
    Spinner spinnerReserva, spinnerTipoevento ;
    ArrayList<ReservaEvento> list;
    ArrayList<TipoEvento> listTipoEventos;
    ArrayList<String> itemsTE=new ArrayList<String>();
    ArrayList<Integer> itemsRE=new ArrayList<Integer>();

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva_evento_actualizar);
        helper = new ControlReserveLocal(this);
        list=helper.consultarReservas();
        listTipoEventos=helper.ListTipoEventos();
        itemsRE.add(0);
        itemsTE.add("Seleccione...");
        //OBTENER LISTA DE RESERVAS DE EVENTOS
        for (int i=0; i<list.size();i++){
            itemsRE.add(list.get(i).getIdReservaEvento());
        }
        spinnerReserva=(Spinner) findViewById(R.id.spinnerreservaeventoAct);
        ArrayAdapter<Integer> adapter=new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, itemsRE);
        spinnerReserva.setAdapter(adapter);
        //OBTENER LISTA DE TIPOS DE EVENTOS
        for (int i=0; i<listTipoEventos.size();i++){
            itemsTE.add(listTipoEventos.get(i).getIdTipoEvento());
        }
        spinnerTipoevento=(Spinner) findViewById(R.id.spinnerTipoEventoAct);
        ArrayAdapter<String> adapterTE=new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, itemsTE);
        spinnerTipoevento.setAdapter(adapterTE);
        editnombreEvento = (EditText) findViewById(R.id.editNombreEvento);
        editCapacidad=(EditText) findViewById(R.id.editCapacidad);
        editFechaEvento=(EditText) findViewById(R.id.editFecha);
        editCodigoEscuela=(EditText) findViewById(R.id.editCodigoEscuela);
    }

    public void actualizarReservaEvento(View v) {
        ReservaEvento reservaEvento=new ReservaEvento();

        String reserva_id=(spinnerReserva.getSelectedItem().toString());
        String tipoEvento_id=spinnerTipoevento.getSelectedItem().toString();

        if (editCodigoEscuela.getText().toString().equals("") || editFechaEvento.getText().toString().equals("") ||spinnerReserva.getSelectedItem().toString().equals(0) || spinnerTipoevento.getSelectedItem().toString().equals("Seleccione...") ){
            Toast.makeText(this, "Debe rellenar todos los campos", Toast.LENGTH_SHORT).show();
        }
        else {
            reservaEvento.setIdReservaEvento(Integer.valueOf(reserva_id));
            reservaEvento.setCodigoEscuela(editCodigoEscuela.getText().toString());
            reservaEvento.setIdTipoEvento(tipoEvento_id);
            reservaEvento.setNombreEvento(editnombreEvento.getText().toString());
            reservaEvento.setCapacidadTotalEvento(Integer.valueOf(editCapacidad.getText().toString()));
            reservaEvento.setFechaReservaEvento(editFechaEvento.getText().toString());

            helper.abrir();
            String estado = helper.actualizar(reservaEvento);
            helper.cerrar();

            Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
        }

    }

    public void limpiarTexto(View v) {
        spinnerReserva.setSelection(0);
        spinnerTipoevento.setSelection(0);
        editnombreEvento.setText("");
        editCapacidad.setText("");
        editCodigoEscuela.setText("");
        editFechaEvento.setText("");
    }

}
