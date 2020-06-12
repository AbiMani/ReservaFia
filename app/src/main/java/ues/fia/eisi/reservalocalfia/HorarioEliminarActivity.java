package ues.fia.eisi.reservalocalfia;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class HorarioEliminarActivity extends Activity {
    ControlReserveLocal helper;
    EditText editIdHorario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario_eliminar);
        helper = new ControlReserveLocal(this);
        editIdHorario = (EditText) findViewById(R.id.editIdHorario);
    }
    public  void eliminarHorario(View v){
        String regEliminadas;
        Horario horario = new Horario();
        horario.setidHorario(Integer.valueOf(editIdHorario.getText().toString()));
        helper.abrir();
        regEliminadas= helper.eliminar(horario);
        helper.cerrar();
        Toast.makeText(this, regEliminadas,Toast.LENGTH_SHORT).show();
    }
}
