package ues.fia.eisi.reservalocalfia;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UsuarioInsertarActivity extends Activity {

    ControlReserveLocal helper;

    EditText editUsername;
    EditText editPassword;
    EditText editNombreUsuario;
    EditText editApellidoUsuario;
    EditText editCorreo;
    EditText editCarnetDocente;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_insertar);
        helper = new ControlReserveLocal(this);

        editUsername = (EditText) findViewById(R.id.editUsername);
        editPassword = (EditText) findViewById(R.id.editPassword);
        editNombreUsuario = (EditText) findViewById(R.id.editNombreUsuario);
        editApellidoUsuario = (EditText) findViewById(R.id.editApellidoUsuario);
        editCorreo = (EditText) findViewById(R.id.editCorreo);
        editCarnetDocente = (EditText) findViewById(R.id.editCarnetDocente);
    }
    public void insertarUsuario(View v) {

        if(editUsername.getText().toString().isEmpty()){
            Toast.makeText(this, "Error Campos vacios no se puede ingresar registro",Toast.LENGTH_SHORT).show();
        }else if(editCarnetDocente.getText().toString().isEmpty()) {
            String username=editUsername.getText().toString();
            String password=editPassword.getText().toString();
            String nombres=editNombreUsuario.getText().toString();
            String apellidos=editApellidoUsuario.getText().toString();
            String correo=editCorreo.getText().toString();
            String carnetDocente=null;
            String is_admin="No";
            String is_docente="No";
            String regInsertados;
            Usuario u = new Usuario();
            u.setUsername(username);
            u.setPassword(password);
            u.setIs_admin(is_admin);
            u.setIs_docente(is_docente);
            u.setNombres(nombres);
            u.setApellidos(apellidos);
            u.setCorreo(correo);
            u.setCarnetDocente(carnetDocente);
            helper.abrir();
            regInsertados=helper.insertar(u);
            helper.cerrar();
            Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
        } else {
            String username=editUsername.getText().toString();
            String password=editPassword.getText().toString();
            String nombres=editNombreUsuario.getText().toString();
            String apellidos=editApellidoUsuario.getText().toString();
            String correo=editCorreo.getText().toString();
            String carnetDocente=editCarnetDocente.getText().toString();
            String is_admin="No";
            String is_docente="Si";
            String regInsertados;
            Usuario usuario=new Usuario();
            usuario.setUsername(username);
            usuario.setPassword(password);
            usuario.setIs_admin(is_admin);
            usuario.setIs_docente(is_docente);
            usuario.setNombres(nombres);
            usuario.setApellidos(apellidos);
            usuario.setCorreo(correo);
            usuario.setCarnetDocente(carnetDocente);
            helper.abrir();
            regInsertados=helper.insertar(usuario);
            helper.cerrar();
            Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
        }
    }

    public void limpiarTexto(View v) {

        editUsername.setText("");
        editPassword.setText("");
        editNombreUsuario.setText("");
        editApellidoUsuario.setText("");
        editCorreo.setText("");
        editCarnetDocente.setText("");
    }
}