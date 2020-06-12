package ues.fia.eisi.reservalocalfia;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TipoLocalEliminarActivity extends Activity {

    EditText editNomTipoLocal;
    ControlReserveLocal controlhelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_local_eliminar);

        controlhelper=new ControlReserveLocal (this);
        editNomTipoLocal=(EditText)findViewById(R.id.editNomTipoLocal);
    }

    public void eliminarTipoLocal(View v){
        String regEliminadas;
        TipoLocal local =new TipoLocal();
        local.setNomTipoLocal(editNomTipoLocal.getText().toString());
        controlhelper.abrir();
        regEliminadas=controlhelper.eliminar(local);
        controlhelper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
}