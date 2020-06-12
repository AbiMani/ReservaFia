package ues.fia.eisi.reservalocalfia;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class GrupoConsultarActivity extends Activity {

    ControlReserveLocal helper;
    EditText editIdGrupo;
    EditText editIdRolDocente;
    EditText editCodigoAsignatura;
    EditText editCodigoCiclo;
    EditText editCarnetDocente;
    EditText editNumMaximoEstudiantes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupo_consultar);
        helper = new ControlReserveLocal(this);
        editIdGrupo = (EditText) findViewById(R.id.editIdGrupo);
        editIdRolDocente = (EditText) findViewById(R.id.editIdRolDocente);
        editCodigoAsignatura = (EditText) findViewById(R.id.editCodigoAsignatura);
        editCodigoCiclo = (EditText) findViewById(R.id.editCodciclo);
        editCarnetDocente = (EditText) findViewById(R.id.editCarnetDocente);
        editNumMaximoEstudiantes = (EditText) findViewById(R.id.editNumMaxEstudiantes);
    }
    public void consultarGrupo(View v) {
        helper.abrir();
        Grupo grupo = helper.consultarGrupo(editIdGrupo.getText().toString(),String.valueOf(editIdRolDocente.getText().toString()),
                editCodigoAsignatura.getText().toString(),editCodigoCiclo.getText().toString(),editCarnetDocente.getText().toString());
        helper.cerrar();
        if(grupo == null)
            Toast.makeText(this, "Grupo no registrado", Toast.LENGTH_LONG).show();
        else{
            editNumMaximoEstudiantes.setText(String.valueOf(grupo.getNumMaximoEstudiantes()));
        }
    }
    public void limpiarTexto(View v) {
        editIdGrupo.setText("");
        editIdRolDocente.setText("");
        editCodigoAsignatura.setText("");
        editCodigoCiclo.setText("");
        editCarnetDocente.setText("");
        editNumMaximoEstudiantes.setText("");
    }
}
