package ues.fia.eisi.reservalocalfia;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DocenteConsultarActivity extends Activity {

    ControlReserveLocal helper;
    EditText editCarnetDocente;
    EditText editNombreDocente;
    EditText editApellido;
    EditText editRol;
    EditText editNomEscuela;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docente_consultar);
        helper = new ControlReserveLocal(this);

        editCarnetDocente = (EditText) findViewById(R.id.editCarnetDocente);
        editNombreDocente = (EditText) findViewById(R.id.editNombreDocente);
        editApellido = (EditText) findViewById(R.id.editApellido);
        editRol = (EditText) findViewById(R.id.editRol);
        editNomEscuela = (EditText) findViewById(R.id.editNomEscuela);

    }

    public void consultarDocente(View v) {

        helper.abrir();
        Docente docente = helper.consultarDocente(editCarnetDocente.getText().toString());

        helper.cerrar();
        if(docente == null)
            Toast.makeText(this, "Docente con carnet " + editCarnetDocente.getText().toString() +
                    " no encontrado", Toast.LENGTH_LONG).show();
        else{
            editNombreDocente.setText(docente.getNombreDocente());
            editApellido.setText(docente.getApellido());
            editRol.setText(docente.getRol());
            editNomEscuela.setText(docente.getNomEscuela());
        }
    }

    public void limpiarTexto(View v){
        editCarnetDocente.setText("");
        editNombreDocente.setText("");
        editApellido.setText("");
        editRol.setText("");
        editNomEscuela.setText("");
    }
}
