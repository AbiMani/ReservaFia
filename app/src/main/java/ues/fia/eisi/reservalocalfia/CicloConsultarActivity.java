package ues.fia.eisi.reservalocalfia;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CicloConsultarActivity extends Activity {

    ControlReserveLocal helper;
    EditText editCodigoCiclo;
    EditText editFechaInicio;
    EditText editFecchaFin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciclo_consultar);
        helper = new ControlReserveLocal(this);
        editCodigoCiclo = (EditText) findViewById(R.id.editCodciclo);
        editFechaInicio = (EditText) findViewById(R.id.editFechainicio);
        editFecchaFin = (EditText) findViewById(R.id.editFechafin);
    }

    public void consultarCiclo(View v) {
        helper.abrir();
        Ciclo ciclo = helper.consultarCiclo(editCodigoCiclo.getText().toString());
        helper.cerrar();
        if(ciclo == null)
            Toast.makeText(this, "Ciclo con codigociclo " + editCodigoCiclo.getText().toString() +
                    " no encontrado", Toast.LENGTH_LONG).show();
        else{
            editFechaInicio.setText(ciclo.getFechaInicio());
            editFecchaFin.setText(ciclo.getFechaFin());
        }
    }
    public void limpiarTexto(View v){
        editCodigoCiclo.setText("");
        editFechaInicio.setText("");
        editFecchaFin.setText("");
    }
}
