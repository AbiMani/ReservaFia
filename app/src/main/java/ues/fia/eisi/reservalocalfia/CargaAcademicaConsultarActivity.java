package ues.fia.eisi.reservalocalfia;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CargaAcademicaConsultarActivity extends Activity {
    ControlReserveLocal helper;
    EditText editCarnetDocente;
    EditText editCodigoAsignatura;
    EditText editCodigoCiclo;
    EditText editIdRolDocente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carga_academica_consultar);
        helper = new ControlReserveLocal(this);
        editCodigoAsignatura = (EditText) findViewById(R.id.editCodigoAsignatura);
        editCarnetDocente = (EditText) findViewById(R.id.editCarnetDocente);
        editCodigoCiclo = (EditText) findViewById(R.id.editCodigoCiclo);
        editIdRolDocente = (EditText) findViewById(R.id.editIdRolDocente);
    }
    public void consultarCargaAcademica(View v) {
        helper.abrir();
        CargaAcademica cargaAcademica= helper.consultarCargaAcademica(editCarnetDocente.getText().toString(), editCodigoAsignatura.getText().toString(), editCodigoCiclo.getText().toString());
        helper.cerrar();
        if(cargaAcademica== null)
            Toast.makeText(this, "Carga Academica no registrada", Toast.LENGTH_LONG).show();
        else{
            editIdRolDocente.setText(String.valueOf(cargaAcademica.getidRolDocente()));
        }
    }
    public void limpiarTexto(View v) {
        editCodigoAsignatura.setText("");
        editCarnetDocente.setText("");
        editCodigoCiclo.setText("");
        editIdRolDocente.setText("");
    }
}
