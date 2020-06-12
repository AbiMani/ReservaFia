package ues.fia.eisi.reservalocalfia;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AsignaturaEliminarActivity extends Activity {

    ControlReserveLocal controlhelper;
    EditText editCodigoAsignatura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignatura_eliminar);
        controlhelper = new ControlReserveLocal(this);
        editCodigoAsignatura = (EditText) findViewById(R.id.editCodigoAsignatura);
    }

    public  void eliminarAsignatura(View v){
        String regEliminadas;
        Asignatura asignatura = new Asignatura();
        asignatura.setCodigoAsignatura(editCodigoAsignatura.getText().toString());
        controlhelper.abrir();
        regEliminadas= controlhelper.eliminar(asignatura);
        controlhelper.cerrar();
        Toast.makeText(this, regEliminadas,Toast.LENGTH_SHORT).show();
    }
}

