package ues.fia.eisi.reservalocalfia;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class CicloActualizarActivity extends Activity {
    ControlReserveLocal helper;
    EditText editCodigoCiclo;
    EditText editFechaInicio;
    EditText editFecchaFin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciclo_actualizar);
        helper = new ControlReserveLocal(this);
        editCodigoCiclo = (EditText) findViewById(R.id.editCodciclo);
        editFechaInicio = (EditText) findViewById(R.id.editFechainicio);
        editFecchaFin = (EditText) findViewById(R.id.editFechafin);
    }
    public void actualizarCiclo(View v) {
        Ciclo ciclo = new Ciclo();
        ciclo.setCodigoCiclo(editCodigoCiclo.getText().toString());
        ciclo.setFechaInicio(editFechaInicio.getText().toString());
        ciclo.setFechaFin(editFecchaFin.getText().toString());
        helper.abrir();
        String estado = helper.actualizarC(ciclo);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {
        editCodigoCiclo.setText("");
        editFechaInicio.setText("");
        editFecchaFin.setText("");
    }
}
