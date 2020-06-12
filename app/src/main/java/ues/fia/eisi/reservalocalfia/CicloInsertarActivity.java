package ues.fia.eisi.reservalocalfia;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CicloInsertarActivity extends Activity {
    ControlReserveLocal helper;
    EditText editCodciclo;
    EditText editFechainicio;
    EditText editFechafin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciclo_insertar);
        helper = new ControlReserveLocal(this);
        editCodciclo = (EditText) findViewById(R.id.editCodciclo);
        editFechainicio = (EditText) findViewById(R.id.editFechainicio);
        editFechafin = (EditText) findViewById(R.id.editFechafin);
    }

    public void insertarCiclo(View v) {
        String codciclo = editCodciclo.getText().toString();
        String fechainicio = editFechainicio.getText().toString();
        String fechafin = editFechafin.getText().toString();
        String regInsertados;
        Ciclo ciclo = new Ciclo();
        ciclo.setCodigoCiclo(codciclo);
        ciclo.setFechaInicio(fechainicio);
        ciclo.setFechaFin(fechafin);
        helper.abrir();
        regInsertados = helper.insertar(ciclo);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v) {
        editCodciclo.setText("");
        editFechainicio.setText("");
        editFechafin.setText("");
    }
}
