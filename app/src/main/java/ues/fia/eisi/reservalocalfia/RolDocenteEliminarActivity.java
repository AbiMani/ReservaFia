package ues.fia.eisi.reservalocalfia;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RolDocenteEliminarActivity extends Activity {

    EditText editNomRolDocente;
    ControlReserveLocal controlhelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rol_docente_eliminar);
        controlhelper=new ControlReserveLocal (this);
        editNomRolDocente=(EditText)findViewById(R.id.editNomRolDocente);
    }

    public void eliminarRolDocente(View v){
        String regEliminadas;
        RolDocente rol =new RolDocente();
        rol.setNomRolDocente(editNomRolDocente.getText().toString());
        controlhelper.abrir();
        regEliminadas=controlhelper.eliminar(rol);
        controlhelper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
}