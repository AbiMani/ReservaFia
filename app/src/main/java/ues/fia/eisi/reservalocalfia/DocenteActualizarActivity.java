package ues.fia.eisi.reservalocalfia;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class DocenteActualizarActivity extends Activity {

    ControlReserveLocal helper;
    EditText editCarnetDocente;
    EditText editNombreDocente;
    EditText editApellido;
    //EditText editRol;
    EditText editNomEscuela;
    Spinner spRol;
    ArrayList<String> listaSpinnerRol;
    ArrayList<RolDocente> listaSpinnerRolObjetos;
    ControlReserveLocal conn;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docente_actualizar);
        helper = new ControlReserveLocal(this);

        editCarnetDocente = (EditText) findViewById(R.id.editCarnetDocente);
        editNombreDocente = (EditText) findViewById(R.id.editNombreDocente);
        editApellido = (EditText) findViewById(R.id.editApellido);
        //editRol = (EditText) findViewById(R.id.editRol);
        spRol=(Spinner) findViewById(R.id.spinner_docente_rol_insertar);
        editNomEscuela = (EditText) findViewById(R.id.editNomEscuela);

        conn = new ControlReserveLocal(this);
        listaSpinnerRolObjetos=conn.consultarTodosRol();
        obtenerRol();

        ArrayAdapter<CharSequence> adapterRol = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listaSpinnerRol);
        spRol.setAdapter(adapterRol);

    }

    public void actualizarDocente(View v) {

        Docente variable = new Docente();
        variable.setCarnetDocente(editCarnetDocente.getText().toString());
        variable.setNombreDocente(editNombreDocente.getText().toString());
        variable.setApellido(editApellido.getText().toString());
        //variable.setRol(editRol.getText().toString());
        String selRol=spRol.getSelectedItem().toString();
        variable.setRol(selRol);
        variable.setNomEscuela(editNomEscuela.getText().toString());

        helper.abrir();
        String estado = helper.actualizar(variable);
        helper.cerrar();

        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();

    }

    private void obtenerRol() {
        listaSpinnerRol = new ArrayList<String>();
        listaSpinnerRol.add(" -- Seleccione -- ");
        for (int i=0;i<listaSpinnerRolObjetos.size();i++){
            listaSpinnerRol.add(listaSpinnerRolObjetos.get(i).getNomRolDocente());
        }
    }

    public void limpiarTexto(View v) {

        editCarnetDocente.setText("");
        editNombreDocente.setText("");
        editApellido.setText("");
        //editRol.setText("");
        editNomEscuela.setText("");
    }
}


