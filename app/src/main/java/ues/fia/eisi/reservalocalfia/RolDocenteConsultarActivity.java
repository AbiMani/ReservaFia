package ues.fia.eisi.reservalocalfia;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RolDocenteConsultarActivity extends Activity {

    ControlReserveLocal helper;
    EditText editIdRolDocente;
    EditText editNomRolDocente;
    EditText editNombreRolNuevo;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rol_docente_consultar);
        helper = new ControlReserveLocal(this);

        editIdRolDocente = (EditText) findViewById(R.id.editIdRolDocente);
        editNomRolDocente = (EditText) findViewById(R.id.editNomRolDocente);
        editNombreRolNuevo = (EditText) findViewById(R.id.editNombreRolNuevo);
    }

    public void consultarRolDocente(View v) {

        helper.abrir();
        RolDocente rol = helper.consultarRolDocente(editNomRolDocente.getText().toString());

        helper.cerrar();
        if(rol == null)
            Toast.makeText(this, "Rol del docente " + editNomRolDocente.getText().toString() +
                    " no encontrado", Toast.LENGTH_LONG).show();
        else{
            editIdRolDocente.setText(String.valueOf(rol.getIdRolDocente()));
            editNombreRolNuevo.setText(rol.getNomRolDocente());
        }
    }

    public void limpiarTexto(View v){
        editNomRolDocente.setText("");
        editIdRolDocente.setText("");
        editNombreRolNuevo.setText("");
    }
}