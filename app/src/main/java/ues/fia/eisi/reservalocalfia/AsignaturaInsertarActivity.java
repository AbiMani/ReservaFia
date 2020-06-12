package ues.fia.eisi.reservalocalfia;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class AsignaturaInsertarActivity extends Activity {
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
        setContentView(R.layout.activity_asignatura_insertar);
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
    public void insertarAsignatura(View v) {
        String codigoAsignatura=editCodigoAsignatura.getText().toString();
        String codigoLocal=editCodigoLocal.getText().toString();
        String codigoEscuela=editCodigoEscuela.getText().toString();
        String nomAsignatura=editNomAsignatura.getText().toString();
        String idPrioridad= spinnerPrioridad.getSelectedItem().toString();
        String regInsertados;
        Asignatura asignatura=new Asignatura();
        asignatura.setCodigoAsignatura(codigoAsignatura);
        asignatura.setCodigoLocal(codigoLocal);
        asignatura.setCodigoEscuela(codigoEscuela);
        asignatura.setNomAsignatura(nomAsignatura);
        asignatura.setIdPrioridad(idPrioridad);
        helper.abrir();
        regInsertados=helper.insertar(asignatura);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {
        editCodigoAsignatura.setText("");
        editCodigoLocal.setText("");
        editCodigoEscuela.setText("");
        editNomAsignatura.setText("");
    }
}
