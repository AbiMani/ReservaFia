package ues.fia.eisi.reservalocalfia;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UsuarioEliminarActivity extends Activity {

    EditText editUsername;
    ControlReserveLocal controlhelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_eliminar);
        controlhelper=new ControlReserveLocal (this);
        editUsername=(EditText)findViewById(R.id.editUsername);
    }

    public void eliminarUsuario(View v){
        String regEliminadas;
        Usuario u=new Usuario();
        u.setUsername(editUsername.getText().toString());
        controlhelper.abrir();
        regEliminadas=controlhelper.eliminar(u);
        controlhelper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
}
