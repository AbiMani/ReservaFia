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

public class ReservaEventoEliminarActivity extends Activity {
    EditText editCodigoEscuela;
    Spinner spinnerReserva, spinnerTipoevento ;
    ArrayList<ReservaEvento> list;
    ArrayList<TipoEvento> listTipoEventos;
    ArrayList<String> itemsTE=new ArrayList<String>();
    ArrayList<Integer> itemsRE=new ArrayList<Integer>();
    ControlReserveLocal controlhelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva_evento_eliminar);
        controlhelper=new ControlReserveLocal(this);
        list=controlhelper.consultarReservas();
        listTipoEventos=controlhelper.ListTipoEventos();
        itemsRE.add(0);
        itemsTE.add("Seleccione...");
        for (int i=0; i<list.size();i++){
            itemsRE.add(list.get(i).getIdReservaEvento());
        }
        spinnerReserva=(Spinner) findViewById(R.id.spinnerReservaEventoEliminar);
        ArrayAdapter<Integer> adapter=new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, itemsRE);
        spinnerReserva.setAdapter(adapter);
        for (int i=0; i<listTipoEventos.size();i++){
            itemsTE.add(listTipoEventos.get(i).getIdTipoEvento());
        }
        spinnerTipoevento=(Spinner) findViewById(R.id.spinnerTipoEventoEliminar);
        ArrayAdapter<String> adapterTE=new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, itemsTE);
        spinnerTipoevento.setAdapter(adapterTE);
        editCodigoEscuela=(EditText) findViewById(R.id.editCodigoEscuela);

    }

    public void eliminarReservaEvento(View v){
        String regEliminadas;
        ReservaEvento reservaEvento=new ReservaEvento();
        String reserva_id=(spinnerReserva.getSelectedItem().toString());
        String tipoEvento_id=spinnerTipoevento.getSelectedItem().toString();
        String codigoEscuela=editCodigoEscuela.getText().toString();

        if (codigoEscuela.equals("") || reserva_id.equals(0) || tipoEvento_id.equals("Seleccione...")){
            Toast.makeText(this, "Debe completar todos los campos.", Toast.LENGTH_SHORT).show();
        }
        else {
            reservaEvento.setIdReservaEvento(Integer.valueOf(reserva_id));
            reservaEvento.setCodigoEscuela(codigoEscuela);
            reservaEvento.setIdTipoEvento(tipoEvento_id);
            controlhelper.abrir();
            regEliminadas = controlhelper.eliminar(reservaEvento);
            controlhelper.cerrar();
            Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
        }
    }

    public void limpiarTexto(View v) {
        spinnerReserva.setSelection(0);
        spinnerTipoevento.setSelection(0);
        editCodigoEscuela.setText("");
    }
}
