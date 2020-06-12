package ues.fia.eisi.reservalocalfia;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TipoLocalInsertarActivity extends Activity {

    ControlReserveLocal helper;
    EditText editNomTipoLocal;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_local_insertar);
        helper = new ControlReserveLocal(this);
        editNomTipoLocal = (EditText) findViewById(R.id.editNomTipoLocal);
    }
    public void insertarTipoLocal(View v) {

        if(editNomTipoLocal.getText().toString().isEmpty()) {
            Toast.makeText(this, "Error Campos vacios no se puede ingresar registro",Toast.LENGTH_SHORT).show();
        }else{
            String nomTipoLocal=editNomTipoLocal.getText().toString();
            String regInsertados;
            TipoLocal local = new TipoLocal();
            local.setNomTipoLocal(nomTipoLocal);

            helper.abrir();
            regInsertados=helper.insertar(local);
            helper.cerrar();

            Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
        }
    }

    public void limpiarTexto(View v) {

        editNomTipoLocal.setText("");
    }
}