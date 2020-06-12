package ues.fia.eisi.reservalocalfia;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class GrupoInsertarActivity extends Activity {

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
        setContentView(R.layout.activity_grupo_insertar);
        helper = new ControlReserveLocal(this);
        editIdGrupo = (EditText) findViewById(R.id.editIdGrupo);
        editIdRolDocente = (EditText) findViewById(R.id.editIdRolDocente);
        editCodigoAsignatura = (EditText) findViewById(R.id.editCodigoAsignatura);
        editCodigoCiclo = (EditText) findViewById(R.id.editCodciclo);
        editCarnetDocente = (EditText) findViewById(R.id.editCarnetDocente);
        editNumMaximoEstudiantes = (EditText) findViewById(R.id.editNumMaxEstudiantes);
    }
    public void insertarGrupo(View v) {
        String regInsertados;
        String idgrupo=editIdGrupo.getText().toString();
        Integer idroldocente = Integer.valueOf(editIdRolDocente.getText().toString());
        String codigoasignatura = editCodigoAsignatura.getText().toString();
        String codigociclo = editCodigoCiclo.getText().toString();
        String carnetdocente=editCarnetDocente.getText().toString();
        Integer nummaxestudiantes =Integer.valueOf(editNumMaximoEstudiantes.getText().toString());
        Grupo grupo= new Grupo();
        grupo.setIdGrupo(idgrupo);
        grupo.setIdRolDocente(idroldocente);
        grupo.setCodigoAsignatura(codigoasignatura);
        grupo.setCodigoCiclo(codigociclo);
        grupo.setCarnetDocente(carnetdocente);
        grupo.setNumMaximoEstudiantes(nummaxestudiantes);
        helper.abrir();
        regInsertados=helper.insertar(grupo);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
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
