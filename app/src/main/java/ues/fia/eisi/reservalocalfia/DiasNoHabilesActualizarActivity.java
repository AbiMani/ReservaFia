package ues.fia.eisi.reservalocalfia;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DiasNoHabilesActualizarActivity extends Activity {

    ControlReserveLocal helper;
    EditText editIdDiasNoHabiles;
    EditText editCodigoCiclo;
    EditText editNomDiasNoHabiles;
    EditText editFechaDesde;
    EditText editFechaHasta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dias_no_habiles_actualizar);
        helper = new ControlReserveLocal(this);
        editIdDiasNoHabiles = (EditText) findViewById(R.id.editiddiasnohabiles);
        editCodigoCiclo = (EditText) findViewById(R.id.editCodciclo);
        editNomDiasNoHabiles = (EditText) findViewById(R.id.editNomDiasNoHabiles);
        editFechaDesde = (EditText) findViewById(R.id.editFechaDesde);
        editFechaHasta = (EditText) findViewById(R.id.editFechaHasta);
    }
    public void actualizarD(View v) {
        DiasNoHabiles diasNoHabiles = new DiasNoHabiles();
        diasNoHabiles.setIdDiasNoHabiles(editIdDiasNoHabiles.getText().toString());
        diasNoHabiles.setCodigoCiclo(editCodigoCiclo.getText().toString());
        diasNoHabiles.setNomDiasNoHabiles(editNomDiasNoHabiles.getText().toString());
        diasNoHabiles.setFechaDesde(editFechaDesde.getText().toString());
        diasNoHabiles.setFechaHasta(editFechaHasta.getText().toString());
        helper.abrir();
        String estado = helper.actualizarD(diasNoHabiles);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {
        editIdDiasNoHabiles.setText("");
        editCodigoCiclo.setText("");
        editNomDiasNoHabiles.setText("");
        editFechaDesde.setText("");
        editFechaHasta.setText("");
    }
}
