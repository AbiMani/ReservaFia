package ues.fia.eisi.reservalocalfia;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TipoLocalActualizarActivity extends Activity {

    ControlReserveLocal helper;
    EditText editIdTipoLocal;
    EditText editNomTipoLocal;
    EditText editNombreLocalNuevo;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_local_actualizar);
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

    public void actualizarTipoLocal(View v) {

        if(editNombreLocalNuevo.getText().toString().isEmpty()) {
            Toast.makeText(this, "Error Campos vacios no se puede actualizar",Toast.LENGTH_SHORT).show();
        }else{
            TipoLocal tipoLocal = new TipoLocal();
            tipoLocal.setIdTipoLocal(Integer.valueOf(editIdTipoLocal.getText().toString()));
            tipoLocal.setNomTipoLocal(editNombreLocalNuevo.getText().toString());

            helper.abrir();
            String estado = helper.actualizar(tipoLocal);
            helper.cerrar();

            Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
        }

    }

    public void limpiarTexto(View v) {

        editNomTipoLocal.setText("");
        editIdTipoLocal.setText("");
        editNombreLocalNuevo.setText("");

    }
}