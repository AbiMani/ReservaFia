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

public class DetalleReservaConsultarActivity extends Activity {
    ControlReserveLocal helper;
    EditText editidHorario;
    EditText dia, horaI, horaF;
    EditText editcodigoLocal;
    EditText nomEvento , capacidadEvento, fechaEv;

    Spinner spinner;
    ArrayList<ReservaEvento> list;
    ArrayList<Integer> items= new ArrayList<Integer>();
    ReservaEvento reservaEvento =new ReservaEvento();


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_reserva_consultar);

        helper = new ControlReserveLocal(this);

        list=helper.consultarReservas();
        //items = new String[list.size()];
        items.add(0);
        for (int i=0;i<list.size();i++){
            items.add(list.get(i).getIdReservaEvento());
        }
        spinner=(Spinner) findViewById(R.id.spinnerreservaevento);
        ArrayAdapter<Integer> adapter=new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinner.setAdapter(adapter);
        editidHorario= (EditText) findViewById(R.id.idHorario);
        dia=(EditText) findViewById(R.id.idDia);
        horaI=(EditText) findViewById(R.id.idHInicio) ;
        horaF=(EditText) findViewById(R.id.HFin);
        editcodigoLocal = (EditText) findViewById(R.id.codigoLocal);
        nomEvento=(EditText) findViewById(R.id.editNombreEv);
        capacidadEvento=(EditText) findViewById(R.id.editCapaEvento);
        fechaEv=(EditText) findViewById(R.id.editFechaEv);
    }

    public void consultarDetalle(View v) {

        if (spinner.getSelectedItem().toString().equals("0") ||editidHorario.getText().toString().equals("") || editcodigoLocal.getText().toString().equals("") )
        {
            Toast.makeText(this, "Debe completar los campos", Toast.LENGTH_LONG).show();
        } else {
            helper.abrir();
            DetalleReservaEvento detalleReservaEvento = helper.consultarDetalle(Integer.valueOf(editidHorario.getText().toString()), Integer.valueOf(spinner.getSelectedItem().toString()), editcodigoLocal.getText().toString());
            ReservaEvento reservaEvento=helper.consultarReserva(Integer.valueOf(spinner.getSelectedItem().toString()));
            Horario horario=helper.consultarHorario((editidHorario.getText().toString()));
            helper.cerrar();
            if (detalleReservaEvento == null) {
                Toast.makeText(this, "Detalles no registrados", Toast.LENGTH_SHORT).show();
            } else {
                dia.setText((horario.getidDia()));
                horaI.setText(horario.gethoraInicio());
                horaF.setText(horario.gethoraFin());
                nomEvento.setText(reservaEvento.getNombreEvento());
                capacidadEvento.setText(String.valueOf(reservaEvento.getCapacidadTotalEvento()));
                fechaEv.setText(reservaEvento.getFechaReservaEvento());
            }
        }
    }

    public void limpiarTexto(View v) {
        spinner.setSelection(0);
        dia.setText("");
        horaF.setText("");
        horaI.setText("");
        editcodigoLocal.setText("");
        editidHorario.setText("");
        nomEvento.setText("");
        capacidadEvento.setText("");
        fechaEv.setText("");
    }

}
