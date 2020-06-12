package ues.fia.eisi.reservalocalfia;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EscuelaInsertarActivity extends Activity {
    EditText editCodigoEscuela;
    EditText editNombreEsc;
    ControlReserveLocal helper;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escuela_insertar);

        helper = new ControlReserveLocal(this);
        editCodigoEscuela=(EditText) findViewById(R.id.editCodigoEscuela);
        editNombreEsc = (EditText) findViewById(R.id.editNombreEscuela);
    }
    public void insertarEscuela(View v) {

        String regInsertados;
        String codigoEsc=editCodigoEscuela.getText().toString();
        String nombreEsc=editNombreEsc.getText().toString();

        Escuela escuela= new Escuela();
        if (codigoEsc.equals("") || nombreEsc.equals(""))
        {
            Toast.makeText(this, "Debe completar todos los campos", Toast.LENGTH_SHORT).show();}
        else {
            escuela.setCodigoEscuela((codigoEsc));
            escuela.setNomEscuela(nombreEsc);
            helper.abrir();
            regInsertados = helper.insertar(escuela);
            helper.cerrar();
            Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
        }
    }


    public void limpiarTexto(View v) {
        editCodigoEscuela.setText("");
        editNombreEsc.setText("");
    }
}
