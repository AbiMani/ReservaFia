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

public class ReservaEventoConsultarActivity extends Activity {
    ControlReserveLocal helper;
    EditText editCodigoEscuela;
    EditText editnombreEvento;
    EditText editCapacidad;
    EditText editFechaEvento;
    EditText editidTipoEvento;
    Spinner spinnerReserva;
    ArrayList<ReservaEvento> list;
    ArrayList<TipoEvento> listTipoEventos;
    ArrayList<Integer> itemsRE=new ArrayList<Integer>();
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva_evento_consultar);
        helper = new ControlReserveLocal(this);
        list=helper.consultarReservas();

        itemsRE.add(0);
        for (int i=0; i<list.size();i++){
            itemsRE.add(list.get(i).getIdReservaEvento());
        }
        spinnerReserva=(Spinner) findViewById(R.id.spinnerreservaeventoConsultar);
        ArrayAdapter<Integer> adapter=new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, itemsRE);
        spinnerReserva.setAdapter(adapter);
        listTipoEventos=helper.ListTipoEventos();
        editidTipoEvento=(EditText) findViewById(R.id.tipoEvento);
        editCodigoEscuela=(EditText) findViewById(R.id.editCodigoEscuela);
        editnombreEvento = (EditText) findViewById(R.id.editNombreEvento);
        editCapacidad=(EditText) findViewById(R.id.editCapacidad);
        editFechaEvento=(EditText) findViewById(R.id.editFecha);

    }

    public void consultarReservaEvento(View v) {

        helper.abrir();
        String reserva_id=(spinnerReserva.getSelectedItem().toString());
        ReservaEvento reservaEvento=helper.consultarReserva(Integer.valueOf(reserva_id));
        helper.cerrar();
        if(spinnerReserva.getSelectedItem().toString().equals(0))
        {
            Toast.makeText(this, "Debe agregar una seleccion.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            if (reservaEvento == null){
                Toast.makeText(this, "Reserva no registrada", Toast.LENGTH_LONG).show();
            }
            else {
                editidTipoEvento.setText(reservaEvento.getIdTipoEvento());
                editCodigoEscuela.setText(reservaEvento.getCodigoEscuela());
                editnombreEvento.setText(reservaEvento.getNombreEvento());
                editCapacidad.setText(String.valueOf(reservaEvento.getCapacidadTotalEvento()));
                editFechaEvento.setText(reservaEvento.getFechaReservaEvento());
            }
        }
    }

    public void limpiarTexto(View v) {
        spinnerReserva.setSelection(0);
        editidTipoEvento.setText("");
        editnombreEvento.setText("");
        editCapacidad.setText("");
        editCodigoEscuela.setText("");
        editFechaEvento.setText("");
    }
}
