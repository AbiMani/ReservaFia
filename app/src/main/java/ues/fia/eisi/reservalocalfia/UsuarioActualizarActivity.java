package ues.fia.eisi.reservalocalfia;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UsuarioActualizarActivity extends Activity {

    ControlReserveLocal helper;

    EditText editUsername;
    EditText editPassword;
    EditText editNombreUsuario;
    EditText editApellidoUsuario;
    EditText editCorreo;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_actualizar);
        helper = new ControlReserveLocal(this);

        editUsername = (EditText) findViewById(R.id.editUsername);
        editPassword = (EditText) findViewById(R.id.editPassword);
        editNombreUsuario = (EditText) findViewById(R.id.editNombreUsuario);
        editApellidoUsuario = (EditText) findViewById(R.id.editApellidoUsuario);
        editCorreo = (EditText) findViewById(R.id.editCorreo);

    }
    public void ActualizarUsuario(View v) {
        Usuario u = new Usuario();
        u.setUsername(editUsername.getText().toString());
        u.setPassword(editPassword.getText().toString());
        u.setNombres(editNombreUsuario.getText().toString());
        u.setApellidos(editApellidoUsuario.getText().toString());
        u.setCorreo(editCorreo.getText().toString());

        helper.abrir();
        String estado = helper.actualizar(u);
        helper.cerrar();

        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v) {

        editUsername.setText("");
        editPassword.setText("");
        editNombreUsuario.setText("");
        editApellidoUsuario.setText("");
        editCorreo.setText("");
    }
}