package ues.fia.eisi.reservalocalfia;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UsuarioConsultarActivity extends Activity {

    ControlReserveLocal helper;

    EditText editUsername;
    EditText editPassword;
    EditText editNombreUsuario;
    EditText editApellidoUsuario;
    EditText editCorreo;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_consultar);
        helper = new ControlReserveLocal(this);

        editUsername = (EditText) findViewById(R.id.editUsername);
        editPassword = (EditText) findViewById(R.id.editPassword);
        editNombreUsuario = (EditText) findViewById(R.id.editNombreUsuario);
        editApellidoUsuario = (EditText) findViewById(R.id.editApellidoUsuario);
        editCorreo = (EditText) findViewById(R.id.editCorreo);

    }

    public void consultarUsuario(View v) {

        helper.abrir();
        Usuario u = helper.consultarUsuario(editUsername.getText().toString());

        helper.cerrar();
        if(u == null)
            Toast.makeText(this, "Usuario con el username:  " + editUsername.getText().toString() +
                    " no encontrado", Toast.LENGTH_LONG).show();
        else{
            editPassword.setText(u.getPassword());
            editNombreUsuario.setText(u.getNombres());
            editApellidoUsuario.setText(u.getApellidos());
            editCorreo.setText(u.getCorreo());
        }
    }

    public void limpiarTexto(View v){
        editUsername.setText("");
        editPassword.setText("");
        editNombreUsuario.setText("");
        editApellidoUsuario.setText("");
        editCorreo.setText("");
    }
}
