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

public class DetalleReservaEliminarActivity extends Activity {
    EditText editidHorario;
    Spinner spinner;
    EditText editcodigoLocal;
    ControlReserveLocal controlhelper;

    ArrayList<ReservaEvento> list;
    ArrayList<Integer> items=new ArrayList<Integer>();
    ReservaEvento reservaEvento=new ReservaEvento();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_reserva_eliminar);
        controlhelper=new ControlReserveLocal(this);
        list=controlhelper.consultarReservas();
        items.add(0);
        for (int i=0; i<list.size();i++){
            items.add(list.get(i).getIdReservaEvento());
        }
        spinner=(Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<Integer> adapter=new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinner.setAdapter(adapter);
        editidHorario=(EditText)findViewById(R.id.editidHorario);
        editcodigoLocal=(EditText)findViewById(R.id.editCodigoLocal);
    }

    public void eliminarDetalleReserva(View v){
        String regEliminadas;
        int reserva_id=Integer.valueOf(spinner.getSelectedItem().toString());
        DetalleReservaEvento detalleReservaEvento=new DetalleReservaEvento();

        if (spinner.getSelectedItem().toString().equals(0) || editidHorario.getText().toString().equals("") || editcodigoLocal.getText().toString().equals("")  )
        {
            Toast.makeText(this, "Debe seleccionar una reserva de evento", Toast.LENGTH_SHORT ).show();}
        else {
            detalleReservaEvento.setIdHorario(Integer.valueOf(editidHorario.getText().toString()));
            detalleReservaEvento.setIdReservaEvento(reserva_id);
            detalleReservaEvento.setCodigoLocal(editcodigoLocal.getText().toString());
            controlhelper.abrir();
            regEliminadas = controlhelper.eliminar(detalleReservaEvento);
            controlhelper.cerrar();
            Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
        }
    }
    public void limpiarTexto(View v) {
        spinner.setSelection(0);
        editidHorario.setText("");
        editcodigoLocal.setText("");
    }


}
