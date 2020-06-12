package ues.fia.eisi.reservalocalfia;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DocenteEliminarActivity extends Activity {

    EditText editCarnetDocente;
    ControlReserveLocal controlhelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docente_eliminar);
        controlhelper=new ControlReserveLocal (this);
        editCarnetDocente=(EditText)findViewById(R.id.editCarnetDocente);
    }

    public void eliminarDocente(View v){
        String regEliminadas;
        Docente docente=new Docente();
        docente.setCarnetDocente(editCarnetDocente.getText().toString());
        controlhelper.abrir();
        regEliminadas=controlhelper.eliminar(docente);
        controlhelper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
}
