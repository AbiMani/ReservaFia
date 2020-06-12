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

public class DetalleReservaActualizarActivity extends Activity {
    ControlReserveLocal helper;
    EditText editidHorario;
    Spinner idReservaEvento;
    EditText editcodigoLocal;
    ArrayList<ReservaEvento> list;
    ArrayList<Integer> items= new ArrayList<Integer>();
    ReservaEvento reservaEvento =new ReservaEvento();


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_reserva_actualizar);
        helper = new ControlReserveLocal(this);

        list=helper.consultarReservas();
        items.add(0);
        for (int i=0;i<list.size();i++){
            items.add(list.get(i).getIdReservaEvento());
        }
        idReservaEvento=(Spinner) findViewById(R.id.spinnerREv);
        ArrayAdapter<Integer> adapter=new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        idReservaEvento.setAdapter(adapter);
        editidHorario= (EditText) findViewById(R.id.editidHorario);
        editcodigoLocal = (EditText) findViewById(R.id.editcodigoLocal);
    }

    public void actualizarDetalleReserva(View v) {
        DetalleReservaEvento detalleReservaEvento = new DetalleReservaEvento();

        if (idReservaEvento.getSelectedItem().toString().equals(0) || editidHorario.getText().toString().equals("")|| editcodigoLocal.getText().toString().equals("")){
            Toast.makeText(this, "Debe rellenar todos los campos", Toast.LENGTH_LONG).show();
        }
        else {
            detalleReservaEvento.setIdHorario(Integer.valueOf(editidHorario.getText().toString()));
            detalleReservaEvento.setIdReservaEvento(Integer.valueOf(idReservaEvento.getSelectedItem().toString()));
            detalleReservaEvento.setCodigoLocal(editcodigoLocal.getText().toString());
            helper.abrir();
            String estado = helper.actualizar(detalleReservaEvento);
            helper.cerrar();

            Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();

        }
    }

    public void limpiarTexto(View v) {
        idReservaEvento.setSelection(0);
        editidHorario.setText("");
        editcodigoLocal.setText("");
    }
}
