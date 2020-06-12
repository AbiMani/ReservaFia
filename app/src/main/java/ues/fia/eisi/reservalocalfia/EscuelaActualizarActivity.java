package ues.fia.eisi.reservalocalfia;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class EscuelaActualizarActivity extends Activity {

    ControlReserveLocal helper;
    EditText editnombreEscuela;
    EditText editcodigo;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escuela_actualizar);
        helper = new ControlReserveLocal(this);

        editcodigo=(EditText) findViewById(R.id.codigoEscuela);
        editnombreEscuela = (EditText) findViewById(R.id.editNombreEscuela);
    }

    public void actualizarEscuela(View v) {
        Escuela escuela=new Escuela();

        String escuela_id=editcodigo.getText().toString();
        String escuela_nombre=editnombreEscuela.getText().toString();
        if (escuela_id.equals("")|| escuela_nombre.equals("") )
        {
            Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show();}
        else {
            escuela.setCodigoEscuela(escuela_id);
            escuela.setNomEscuela(escuela_nombre);

            helper.abrir();
            String estado = helper.actualizar(escuela);
            helper.cerrar();

            Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
        }
    }

    public void limpiarTexto(View v) {
        editcodigo.setText("");
        editnombreEscuela.setText("");
    }
}
