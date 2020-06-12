package ues.fia.eisi.reservalocalfia;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DiasNoHabilesEliminarActivity extends Activity {

    EditText editIdDiasNoHabiles;
    ControlReserveLocal controlhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dias_no_habiles_eliminar);
        controlhelper=new ControlReserveLocal(this);
        editIdDiasNoHabiles=(EditText)findViewById(R.id.editiddiasnohabiles);
    }
    public void eliminarDiasNoHabiles(View v){
        String regEliminados;
        DiasNoHabiles diasNoHabiles=new DiasNoHabiles();
        diasNoHabiles.setIdDiasNoHabiles(editIdDiasNoHabiles.getText().toString());
        controlhelper.abrir();
        regEliminados=controlhelper.eliminarD(diasNoHabiles);
        controlhelper.cerrar();
        Toast.makeText(this, regEliminados, Toast.LENGTH_SHORT).show();
    }
    public void limpiar(View v){
        editIdDiasNoHabiles.setText("");
    }
}
