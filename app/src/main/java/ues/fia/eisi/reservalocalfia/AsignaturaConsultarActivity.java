package ues.fia.eisi.reservalocalfia;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AsignaturaConsultarActivity extends Activity {
    ControlReserveLocal helper;
    EditText editCodigoAsignatura;
    EditText editCodigoLocal;
    EditText editCodigoEscuela;
    EditText editNomAsignatura;
    EditText editIdPrioridad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignatura_consultar);
        helper = new ControlReserveLocal(this);

        editCodigoAsignatura = (EditText) findViewById(R.id.editCodigoAsignatura);
        editCodigoLocal= (EditText)findViewById(R.id.editCodigoLocal);
        editCodigoEscuela= (EditText)findViewById(R.id.editCodigoEscuela);
        editNomAsignatura = (EditText) findViewById(R.id.editNomAsignatura);
        editIdPrioridad = (EditText) findViewById(R.id.editIdPrioridad);
    }

    public void consultarAsignatura(View v){
        helper.abrir();
        Asignatura asignatura = helper.consultarAsignatura(editCodigoAsignatura.getText().toString());

        helper.cerrar();
        if(asignatura == null)
            Toast.makeText(this, "Asignatura con carnet "+ editCodigoAsignatura.getText().toString() + "no encontrado", Toast.LENGTH_LONG).show();
        else{
            editCodigoLocal.setText(asignatura.getCodigoLocal());
            editCodigoEscuela.setText(asignatura.getCodigoEscuela());
            editNomAsignatura.setText(asignatura.getNomAsignatura());
            editIdPrioridad.setText(String.valueOf(asignatura.getIdPrioridad()));
        }
    }

    public void limpiarTexto(View v){
        editCodigoAsignatura.setText("");
        editCodigoLocal.setText("");
        editCodigoEscuela.setText("");
        editNomAsignatura.setText("");
        editIdPrioridad.setText("");
    }
}

