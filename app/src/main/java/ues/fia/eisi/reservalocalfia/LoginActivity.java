package ues.fia.eisi.reservalocalfia;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Bundle;
import java.lang.Integer;

public class LoginActivity extends AppCompatActivity {

    Button btn_login;
    EditText username;
    EditText password;
    TextView error;
    ControlReserveLocal helper;
    boolean Logged_in = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        helper = new ControlReserveLocal(this);
        username = (EditText) findViewById(R.id.input_username);
        password = (EditText) findViewById(R.id.input_password);
        error = findViewById(R.id.text_error_login);

        btn_login = (Button) findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Docente docente = new Docente();
                Usuario usuario = new Usuario();
                usuario.setUsername(username.getText().toString());
                usuario.setPassword(password.getText().toString());
                Integer valor = 1;

                Logged_in = helper.verificarUser(usuario);

                if (Logged_in) {
                    error.setVisibility(View.GONE);

                    usuario = helper.verificarAdmin(usuario);

                    if(usuario.getIs_admin().equals("Si")){
                        Intent intent = new Intent(getApplicationContext(), AdministradorMenuActivity.class);
                        startActivity(intent);
                    }else if(usuario.getIs_docente().equals("No")){
                        Intent intent = new Intent(getApplicationContext(), UsuarioExternoActivity.class);
                        startActivity(intent);
                    } else{

                        usuario = helper.verificarCarnet(usuario);
                        docente = helper.verificarUsuarios(usuario);

                        if (docente.getRol().equals("Administrador")) {
                            Intent intent = new Intent(getApplicationContext(), AdministradorMenuActivity.class);
                            startActivity(intent);
                        } else if (docente.getRol().equals("Docente")) {
                            Intent intent = new Intent(getApplicationContext(), DocenteNuevoMenuActivity.class);
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                        }
                    }
                } else {
                    error.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
