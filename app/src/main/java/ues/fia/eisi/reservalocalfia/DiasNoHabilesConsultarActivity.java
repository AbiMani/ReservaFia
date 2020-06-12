package ues.fia.eisi.reservalocalfia;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DiasNoHabilesConsultarActivity extends Activity {

    ControlReserveLocal helper;
    EditText editIdDiasNoHabiles;
    EditText editCodigoCiclo;
    EditText editNomDiasNoHabiles;
    EditText editFechaDesde;
    EditText editFechaHasta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dias_no_habiles_consultar);

        helper = new ControlReserveLocal(this);
        editIdDiasNoHabiles = (EditText) findViewById(R.id.editiddiasnohabiles);
        editCodigoCiclo = (EditText) findViewById(R.id.editCodciclo);
        editNomDiasNoHabiles = (EditText) findViewById(R.id.editNomDiasNoHabiles);
        editFechaDesde = (EditText) findViewById(R.id.editFechaDesde);
        editFechaHasta = (EditText) findViewById(R.id.editFechaHasta);
    }
    public void consultarDiasNoHabiles(View v) {
        helper.abrir();
        DiasNoHabiles diasNoHabiles = helper.consultarD(editIdDiasNoHabiles.getText().toString());
        helper.cerrar();
        if(diasNoHabiles == null)
            Toast.makeText(this, "Dia no registrado", Toast.LENGTH_LONG).show();
        else{
            editCodigoCiclo.setText(String.valueOf(diasNoHabiles.getCodigoCiclo()));
            editNomDiasNoHabiles.setText(String.valueOf(diasNoHabiles.getNomDiasNoHabiles()));
            editFechaDesde.setText(String.valueOf(diasNoHabiles.getFechaDesde()));
            editFechaHasta.setText(String.valueOf(diasNoHabiles.getFechaHasta()));
        }
    }
    public void limpiarTexto(View v) {
        editIdDiasNoHabiles.setText("");
        editCodigoCiclo.setText("");
        editNomDiasNoHabiles.setText("");
        editFechaDesde.setText("");
        editFechaHasta.setText("");
    }
}
