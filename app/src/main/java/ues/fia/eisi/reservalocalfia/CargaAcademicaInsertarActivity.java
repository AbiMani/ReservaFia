package ues.fia.eisi.reservalocalfia;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CargaAcademicaInsertarActivity extends Activity {
    ControlReserveLocal helper;
    EditText editCarnetDocente;
    EditText editCodigoAsignatura;
    EditText editCodigoCiclo;
    EditText editIdRolDocente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carga_academica_insertar);
        helper = new ControlReserveLocal(this);
        editCodigoAsignatura = (EditText) findViewById(R.id.editCodigoAsignatura);
        editCarnetDocente = (EditText) findViewById(R.id.editCarnetDocente);
        editCodigoCiclo = (EditText) findViewById(R.id.editCodigoCiclo);
        editIdRolDocente = (EditText) findViewById(R.id.editIdRolDocente);
    }
    public void insertarCargaAcademica(View v) {
        String codigoAsignatura=editCodigoAsignatura.getText().toString();
        String carnetDocente=editCarnetDocente.getText().toString();
        String codigoCiclo=editCodigoCiclo.getText().toString();
        Integer idRolDocente=Integer.valueOf(editIdRolDocente.getText().toString());
        String regInsertados;
        CargaAcademica cargaAcademica=new CargaAcademica();
        cargaAcademica.setcodigoAsignatura(codigoAsignatura);
        cargaAcademica.setcarnetDocente(carnetDocente);
        cargaAcademica.setcodigoCiclo(codigoCiclo);
        cargaAcademica.setidRolDocente(idRolDocente);
        
        helper.abrir();
        regInsertados=helper.insertar(cargaAcademica);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {
        editCodigoAsignatura.setText("");
        editCarnetDocente.setText("");
        editCodigoCiclo.setText("");
        editIdRolDocente.setText("");
    }
}
