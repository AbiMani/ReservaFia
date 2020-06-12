package ues.fia.eisi.reservalocalfia;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class HorarioActualizarActivity extends Activity {
    ControlReserveLocal helper;
    EditText editIdHorario;

    Spinner spinnerInicio, spinnerDia;
    Spinner spinnerFin;
    String selecInicio;
    String selecFin;
    String[] items;
    Dia dia = new Dia();
    ArrayList<Dia> diasList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario_actualizar);
        helper = new ControlReserveLocal(this);
        editIdHorario = (EditText) findViewById(R.id.editIdHorario);
        spinnerDia = (Spinner) findViewById(R.id.spinnerDia);
        spinnerInicio = (Spinner) findViewById(R.id.spinnerInicio);
        spinnerFin = (Spinner) findViewById(R.id.spinnerFin);
        diasList = helper.listaDia();
        items = new String[diasList.size()];
        for (int i=0;i<diasList.size();i++){
            dia = diasList.get(i);
            items[i] = String.valueOf(dia.getIdDia());
        }
        ArrayAdapter<String> adap=new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinnerDia.setAdapter(adap);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.inicio,android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.fin,android.R.layout.simple_spinner_item);
        spinnerInicio.setAdapter(adapter);
        spinnerFin.setAdapter(adapter1);

        spinnerInicio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selecInicio = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerFin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selecFin = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void actualizarHorario(View v){
        Horario horario = new Horario();
        horario.setidHorario(Integer.parseInt(editIdHorario.getText().toString()));
        horario.setidDia(spinnerDia.getSelectedItem().toString());
        horario.sethoraInicio(selecInicio);
        horario.sethoraFin(selecFin);
        helper.abrir();
        String estado =helper.actualizar(horario);
        helper.cerrar();
        Toast.makeText(this,estado,Toast.LENGTH_SHORT).show();

    }
    public void  limpiarTexto(View v){
        editIdHorario.setText("");
    }

}
