package ues.fia.eisi.reservalocalfia;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class HorarioConsultarActivity extends Activity {
    ControlReserveLocal helper;
    EditText editIdHorario;
    EditText editIdDia;
    EditText editHoraInicio;
    EditText editHoraFin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario_consultar);
        helper = new ControlReserveLocal(this);
        editIdHorario = (EditText) findViewById(R.id.editIdHorario);
        editIdDia = (EditText) findViewById(R.id.editIdDia);
        editHoraInicio = (EditText) findViewById(R.id.editHoraInicio);
        editHoraFin = (EditText) findViewById(R.id.editHoraFin);
    }

    public void consultarHorario(View v){
        helper.abrir();
        Horario horario=helper.consultarHorario(editIdHorario.getText().toString());
        helper.cerrar();
        if(horario == null)
            Toast.makeText(this, "Horario con id "+ editIdHorario.getText().toString() + "no encontrado", Toast.LENGTH_LONG).show();
        else{
            editIdHorario.setText(String.valueOf(horario.getidHorario()));
            editIdDia.setText(horario.getidDia());
            editHoraInicio.setText(horario.gethoraInicio());
            editHoraFin.setText(horario.gethoraFin());
        }
    }
    public void  limpiarTexto(View v){
        editIdHorario.setText("");
        editIdDia.setText("");
        editHoraInicio.setText("");
        editHoraFin.setText("");
    }
}
