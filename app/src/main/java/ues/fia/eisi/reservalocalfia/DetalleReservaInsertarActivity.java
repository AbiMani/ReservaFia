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

public class DetalleReservaInsertarActivity extends Activity {
    EditText editidHorario;
    Spinner spinnerReserva ;

    EditText editcodigoLocal;
    ControlReserveLocal helper;

    ArrayList<ReservaEvento> list;
    ArrayList<Integer> items= new ArrayList<Integer>();
    ReservaEvento reservaEvento=new ReservaEvento();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_reserva_insertar);

        helper = new ControlReserveLocal(this);
        list=helper.consultarReservas();
        items.add(0);
        for (int i=0; i<list.size();i++){
            items.add(list.get(i).getIdReservaEvento());
        }
        spinnerReserva=(Spinner) findViewById(R.id.spinnerdetalleInsert);
        ArrayAdapter<Integer> adapter=new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinnerReserva.setAdapter(adapter);
        editidHorario = (EditText) findViewById(R.id.editidHorario);
        editcodigoLocal = (EditText) findViewById(R.id.editCodigoLocal);
    }
    public void insertarDetalleReserva(View v) {

        String regInsertados;
        String idhorario=editidHorario.getText().toString();
        String reserva_id=(spinnerReserva.getSelectedItem().toString());
        String codigoLocal=editcodigoLocal.getText().toString();

        DetalleReservaEvento detalleReservaEvento= new DetalleReservaEvento();
        if (editidHorario.getText().toString().equals("") || spinnerReserva.getSelectedItem().toString().equals(0) ||editcodigoLocal.getText().toString().equals("") ){
            Toast.makeText(this, "Debe rellenar todos los campos", Toast.LENGTH_LONG).show();
        }
        else {
            detalleReservaEvento.setIdHorario(Integer.valueOf(idhorario));
            detalleReservaEvento.setIdReservaEvento(Integer.valueOf(reserva_id));
            detalleReservaEvento.setCodigoLocal(codigoLocal);
            helper.abrir();
            regInsertados = helper.insertar(detalleReservaEvento);
            helper.cerrar();
            Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
        }
    }

    public void limpiarTexto(View v) {
        spinnerReserva.setSelection(0);
        editidHorario.setText("");
        editcodigoLocal.setText("");
    }
}
