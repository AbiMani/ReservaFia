package ues.fia.eisi.reservalocalfia;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RolDocenteInsertarActivity extends Activity {

    ControlReserveLocal helper;
    EditText editNomRolDocente;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rol_docente_insertar);
        helper = new ControlReserveLocal(this);
        editNomRolDocente = (EditText) findViewById(R.id.editNomRolDocente);
    }

    public void insertarRolDocente(View v) {
        if(editNomRolDocente.getText().toString().isEmpty()) {
            Toast.makeText(this, "Error Campos vacios no se puede ingresar registro",Toast.LENGTH_SHORT).show();
        }else{
            String nomRolDocente=editNomRolDocente.getText().toString();
            String regInsertados;
            RolDocente rol=new RolDocente();
            rol.setNomRolDocente(nomRolDocente);
            helper.abrir();
            regInsertados=helper.insertar(rol);
            helper.cerrar();
            Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
        }
    }

    public void limpiarTexto(View v) {

        editNomRolDocente.setText("");
    }
}