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

public class EscuelaConsultarActivity extends Activity {
    ControlReserveLocal helper;
    EditText editCodigoEscuela;
    EditText editnombreEscuela;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escuela_consultar);
        helper = new ControlReserveLocal(this);
        editCodigoEscuela=(EditText) findViewById(R.id.codigoEscuelaCons);
        editnombreEscuela = (EditText) findViewById(R.id.nombreEscuelaCons);

    }
    public void consultarEscuela (View v){
        String codigoEsc = editCodigoEscuela.getText().toString();
        String nomEscuela=editnombreEscuela.getText().toString();
        if (codigoEsc.equals(""))
        {
            Toast.makeText(this, "Debe completar los campos", Toast.LENGTH_SHORT).show();}
        else {
            helper.abrir();
            Escuela escuela = helper.consultarEscuela(codigoEsc);
            helper.cerrar();
            if (escuela == null) {
                Toast.makeText(this, "Escuela no registrado", Toast.LENGTH_LONG).show();
                editCodigoEscuela.setText(" ");
            } else {
                editnombreEscuela.setText(escuela.getNomEscuela());
            }
        }
    }
    public void limpiarTexto(View v){
        editCodigoEscuela.setText("");
        editnombreEscuela.setText("");
    }
}
