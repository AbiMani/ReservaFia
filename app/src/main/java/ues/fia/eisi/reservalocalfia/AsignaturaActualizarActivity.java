package ues.fia.eisi.reservalocalfia;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class AsignaturaActualizarActivity extends Activity {
    ControlReserveLocal helper;
    EditText editCodigoAsignatura;
    EditText editCodigoLocal;
    EditText editCodigoEscuela;
    EditText editNomAsignatura;
    Spinner spinnerPrioridad;
    String[] items;
    Prioridad prioridad = new Prioridad();
    ArrayList<Prioridad> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignatura_actualizar);
        helper = new ControlReserveLocal(this);
        editCodigoAsignatura = (EditText) findViewById(R.id.editCodigoAsignatura);
        editCodigoLocal = (EditText) findViewById(R.id.editCodigoLocal);
        editCodigoEscuela = (EditText) findViewById(R.id.editCodigoEscuela);
        editNomAsignatura = (EditText) findViewById(R.id.editNomAsignatura);
        spinnerPrioridad = (Spinner) findViewById(R.id.spinnerPrioridad);

        list = helper.listaPrioridad();
        items = new String[list.size()];
        for (int i=0;i<list.size();i++){
            prioridad = list.get(i);
            items[i] = String.valueOf(prioridad.getIdprioridad());
        }

        ArrayAdapter<String> adap=new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinnerPrioridad.setAdapter(adap);
    }

    public void actualizarAsignatura(View v){
        Asignatura asignatura = new Asignatura();
        asignatura.setCodigoAsignatura(editCodigoAsignatura.getText().toString());
        asignatura.setCodigoLocal(editCodigoLocal.getText().toString());
        asignatura.setCodigoEscuela(editCodigoEscuela.getText().toString());
        asignatura.setNomAsignatura(editNomAsignatura.getText().toString());
        asignatura.setIdPrioridad(spinnerPrioridad.getSelectedItem().toString());

        helper.abrir();
        String estado = helper.actualizar(asignatura);
        helper.cerrar();

        Toast.makeText(this, estado,Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v){
        editCodigoAsignatura.setText("");
        editCodigoLocal.setText("");
        editCodigoEscuela.setText("");
        editNomAsignatura.setText("");

    }
}

