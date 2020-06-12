package ues.fia.eisi.reservalocalfia;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DiasNoHabilesInsertarActivity extends Activity {

    ControlReserveLocal helper;
    EditText editIdDiaNoHabiles;
    EditText editCodigoCiclo;
    EditText editNomDiasNoHabiles;
    EditText editFechaDesde;
    EditText editFechaHasta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dias_no_habiles_insertar);

        helper = new ControlReserveLocal(this);
        editIdDiaNoHabiles = (EditText) findViewById(R.id.editiddiasnohabiles);
        editCodigoCiclo = (EditText) findViewById(R.id.editCodciclo);
        editNomDiasNoHabiles = (EditText) findViewById(R.id.editNomDiasNoHabiles);
        editFechaDesde = (EditText) findViewById(R.id.editFechaDesde);
        editFechaHasta = (EditText) findViewById(R.id.editFechaHasta);
    }
    public void insertarDiasNoHabiles(View v) {
        String iddiasnohabiles = editIdDiaNoHabiles.getText().toString();
        String codigociclo = editCodigoCiclo.getText().toString();
        String nomdiasnohabiles = editNomDiasNoHabiles.getText().toString();
        String fechadesde = editFechaDesde.getText().toString();
        String fechahasta = editFechaHasta.getText().toString();
        String regInsertados;
        DiasNoHabiles diasNoHabiles= new DiasNoHabiles();
        diasNoHabiles.setIdDiasNoHabiles(iddiasnohabiles);
        diasNoHabiles.setCodigoCiclo(codigociclo);
        diasNoHabiles.setNomDiasNoHabiles(nomdiasnohabiles);
        diasNoHabiles.setFechaDesde(fechadesde);
        diasNoHabiles.setFechaHasta(fechahasta);
        helper.abrir();
        regInsertados=helper.insertar(diasNoHabiles);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {
        editIdDiaNoHabiles.setText("");
        editCodigoCiclo.setText("");
        editNomDiasNoHabiles.setText("");
        editFechaDesde.setText("");
        editFechaHasta.setText("");
    }
}
