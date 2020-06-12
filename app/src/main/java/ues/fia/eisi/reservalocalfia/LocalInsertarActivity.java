package ues.fia.eisi.reservalocalfia;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LocalInsertarActivity extends Activity {
    ControlReserveLocal helper;
    EditText editCodigoLocal;
    EditText editIdEncargadoLocal;
    EditText editIdTipoLocal;
    EditText editUbicacionLocal;
    EditText editCapacidadLocal;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_insertar);
        helper = new ControlReserveLocal(this);
        editCodigoLocal = (EditText) findViewById(R.id.editCodigoLocal);
        editIdEncargadoLocal = (EditText) findViewById(R.id.editIdEncargadoLocal);
        editIdTipoLocal = (EditText) findViewById(R.id.editIdTipoLocal);
        editCapacidadLocal = (EditText) findViewById(R.id.editCapacidadLocal);
       editUbicacionLocal = (EditText) findViewById(R.id.editUbicacionLocal);
    }
    public void insertarLocal(View v) {
        String codigoLocal=editCodigoLocal.getText().toString();
        String IdEncargadoLocal=editIdEncargadoLocal.getText().toString();
        String IdTipoLocal=editIdTipoLocal.getText().toString();
        String ubicacionLocal=editUbicacionLocal.getText().toString();
        Float capacidadLocal=Float.valueOf(editCapacidadLocal.getText().toString());
        String regInsertados;
        Local local=new Local();
        local.setCodigoLocal(codigoLocal);
        local.setIdEncargadoLocal(IdEncargadoLocal);
        local.setIdTipoLocal(IdTipoLocal);
        local.setCapacidadLocal(capacidadLocal);
        local.setUbicacionLocal(ubicacionLocal);
        helper.abrir();
        regInsertados=helper.insertar(local);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {
        editCodigoLocal.setText("");
        editIdEncargadoLocal.setText("");
        editIdTipoLocal.setText("");
        editUbicacionLocal.setText("");
        editCapacidadLocal.setText("");
    }
}
