package ues.fia.eisi.reservalocalfia;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.ArrayList;

public class DocenteInsertarActivity extends Activity {

    ControlReserveLocal helper;
    EditText editCarnetDocente;
    EditText editNombreDocente;
    EditText editApellido;
    EditText editRol;
    EditText editNomEscuela;
    Spinner spRol;
    ArrayList<String> listaSpinnerRol;
    ArrayList<RolDocente> listaSpinnerRolObjetos;
    ControlReserveLocal conn;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docente_insertar);
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

    public void insertarDocente(View v) {
        if(editCarnetDocente.getText().toString().isEmpty()) {
            Toast.makeText(this, "Error Campos vacios no se puede ingresar registro",Toast.LENGTH_SHORT).show();
        }else{

            String carnetDocente=editCarnetDocente.getText().toString();
            String nombreDocente=editNombreDocente.getText().toString();
            String apellido=editApellido.getText().toString();
            String selRol=spRol.getSelectedItem().toString();
            //String rol=editRol.getText().toString();
            String nomEscuela=editNomEscuela.getText().toString();
            String regInsertados;
            Docente docente=new Docente();
            docente.setCarnetDocente(carnetDocente);
            docente.setNombreDocente(nombreDocente);
            docente.setApellido(apellido);
            docente.setRol(selRol);
            //docente.setRol(rol);
            docente.setNomEscuela(nomEscuela);
            helper.abrir();
            regInsertados=helper.insertar(docente);
            helper.cerrar();
            Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();

        }
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
