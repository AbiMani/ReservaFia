package ues.fia.eisi.reservalocalfia;


import android.app.ListActivity;
import android.content.Intent;
//import androidx.support.v7.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity {

    String[] menu={"Asignatura","Carga Academica","Horario","Reserva de Evento","Detalle de reserva","Tipo de Evento","Tabla Local",
            "Tabla Encargado","Tabla Detalle Grupo Reserva",
            "Gestion Docente","Gestion Rol de Docentes","Gestion del tipo de local","Tabla Ciclo","Tabla Grupo","Tabla Dias No Habiles","Gestion Escuela","Servicios Web","Gestion de Usuarios","LLenar Base de Datos"};
    String[] activities={"AsignaturaMenuActivity","CargaAcademicaMenuActivity","HorarioMenuActivity", "ReservaEventoMenuActivity",
            "DetalleReservaMenuActivity","TipoEventoMenuActivity","LocalMenuActivity","EncargadoMenuActivity","DetalleGrupoReservaMenuActivity",
            "DocenteMenuActivity", "RolDocenteMenuActivity","TipoLocalMenuActivity","CicloMenuActivity","GrupoMenuActivity","DiasNoHabilesMenuActivity","EscuelaMenuActivity","ServiciosMenuActivity","UsuariosMenuActivity"};
    ControlReserveLocal BDhelper;
    ControlReserveLocal conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menu));
        //BDhelper= new ControlReserveLocal(this);

        //INSTANCIACION de variables
        conn = new ControlReserveLocal(this);

        //REDIRRECION de activity
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

        conn.abrir();
        conn.llenarBD();
        conn.cerrar();

    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id){
        super.onListItemClick(l, v, position, id);
        if(position!=18) {
            String nombreValue = activities[position];
            try {
                Class<?> clase = Class.forName("ues.fia.eisi.reservalocalfia." + nombreValue);
                Intent inte = new Intent(this, clase);
                this.startActivity(inte);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else{
             /*BDhelper.abrir();
             String tost =BDhelper.llenarBD();
             BDhelper.cerrar();
             Toast.makeText(this,tost, Toast.LENGTH_SHORT).show();*/
        }
    }
}

