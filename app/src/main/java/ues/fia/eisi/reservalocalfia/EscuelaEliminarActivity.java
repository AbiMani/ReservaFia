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

public class EscuelaEliminarActivity extends Activity {
    EditText editcodigoEscuela;
    EditText editnombreEscuela;
    ControlReserveLocal controlhelper;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escuela_eliminar);
        controlhelper=new ControlReserveLocal(this);

        editcodigoEscuela=(EditText) findViewById(R.id.codigoEscuelaEliminar);
    }

    public void eliminarTipoEvento(View v){
        String regEliminadas;
        Escuela escuela=new Escuela();
        String escuela_cod=editcodigoEscuela.getText().toString();
        if (escuela_cod.equals(""))
        {
            Toast.makeText(this, "Debe seleccionar codigo", Toast.LENGTH_SHORT).show();}
        else {
            escuela.setCodigoEscuela(escuela_cod);
            controlhelper.abrir();
            regEliminadas = controlhelper.eliminar(escuela);
            controlhelper.cerrar();
            Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
        }
    }

    public void limpiarTexto(View v) {
      editcodigoEscuela.setText("");
    }
}
