package ues.fia.eisi.reservalocalfia;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class GrupoEliminarActivity extends Activity {

    ControlReserveLocal helper;
    EditText editIdGrupo, editCodigoAsignatura, editCodigoCiclo, editIdRolDocente,editCarnetDocente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupo_eliminar);
        helper=new ControlReserveLocal(this);
        editIdGrupo=(EditText)findViewById(R.id.editIdGrupo);
        editIdRolDocente=(EditText)findViewById(R.id.editIdRolDocente);
        editCodigoAsignatura=(EditText)findViewById(R.id.editCodigoAsignatura);
        editCodigoCiclo=(EditText)findViewById(R.id.editCodciclo);
        editCarnetDocente=(EditText)findViewById(R.id.editCarnetDocente);
    }
    public void eliminarGrupo(View v){
        String regEliminados;
        Grupo grupo=new Grupo();
        grupo.setIdGrupo(editIdGrupo.getText().toString());
        grupo.setIdRolDocente(Integer.valueOf(editIdRolDocente.getText().toString()));
        grupo.setCodigoAsignatura(editCodigoAsignatura.getText().toString());
        grupo.setCodigoCiclo(editCodigoCiclo.getText().toString());
        grupo.setCarnetDocente(editCarnetDocente.getText().toString());
        helper.abrir();
        regEliminados=helper.eliminarG(grupo);
        helper.cerrar();
        Toast.makeText(this, regEliminados, Toast.LENGTH_SHORT).show();
    }
    public void limpiar(View v){
        editIdGrupo.setText("");
        editIdRolDocente.setText("");
        editCodigoAsignatura.setText("");
        editCodigoCiclo.setText("");
        editCarnetDocente.setText("");
    }
}
