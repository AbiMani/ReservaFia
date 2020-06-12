package ues.fia.eisi.reservalocalfia;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TipoLocalConsultarActivity extends Activity {

    ControlReserveLocal helper;
    EditText editIdTipoLocal;
    EditText editNomTipoLocal;
    EditText editNombreLocalNuevo;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_local_consultar);
        helper = new ControlReserveLocal(this);

        editIdTipoLocal = (EditText) findViewById(R.id.editIdTipoLocal);
        editNomTipoLocal = (EditText) findViewById(R.id.editNomTipoLocal);
        editNombreLocalNuevo = (EditText) findViewById(R.id.editNombreLocalNuevo);
    }

    public void consultarTipoLocal(View v) {

        helper.abrir();
        TipoLocal local = helper.consultarTipoLocal(editNomTipoLocal.getText().toString());

        helper.cerrar();
        if(local == null)
            Toast.makeText(this, "Tipo de Local " + editNomTipoLocal.getText().toString() +
                    " no encontrado", Toast.LENGTH_LONG).show();
        else{
            editIdTipoLocal.setText(String.valueOf(local.getIdTipoLocal()));
            editNombreLocalNuevo.setText(local.getNomTipoLocal());
        }
    }

    public void limpiarTexto(View v){
        editNomTipoLocal.setText("");
        editIdTipoLocal.setText("");
        editNombreLocalNuevo.setText("");
    }
}