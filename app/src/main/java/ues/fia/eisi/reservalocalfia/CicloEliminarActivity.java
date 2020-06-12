package ues.fia.eisi.reservalocalfia;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CicloEliminarActivity extends Activity {

    EditText editCodigoCiclo;
    ControlReserveLocal controlhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciclo_eliminar);
        controlhelper=new ControlReserveLocal (this);
        editCodigoCiclo=(EditText)findViewById(R.id.editCodciclo);
    }
    public void eliminarCiclo(View v){
        String regEliminadas;
        Ciclo ciclo=new Ciclo();
        ciclo.setCodigoCiclo(editCodigoCiclo.getText().toString());
        controlhelper.abrir();
        regEliminadas=controlhelper.eliminarCiclo(ciclo);
        controlhelper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
}
