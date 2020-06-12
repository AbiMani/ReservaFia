package ues.fia.eisi.reservalocalfia;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TipoLocalMenuActivity extends ListActivity  {   //cambiar activity correcto

    //cambiar por los String correcto
    String[] menu={"Insertar Registro","Eliminar Registro","Consultar Registro", "Actualizar Registro"};
    //ingresar la tabla AlumnoInsertarActivity
    String[] activities={"TipoLocalInsertarActivity","TipoLocalEliminarActivity",
            "TipoLocalConsultarActivity", "TipoLocalActualizarActivity"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(this,  android.R.layout.simple_list_item_1, menu));

        ListView listView = getListView();
        listView.setBackgroundColor(Color.rgb(0, 0, 255));
        //listView.setBackgroundColor(Color.rgb(0, 128, 64)); //para verde

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, menu);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l,View v,int position,long id){
        super.onListItemClick(l, v, position, id);

        String nombreValue=activities[position];

        l.getChildAt(position).setBackgroundColor(Color.rgb(128, 128, 255));
        //l.getChildAt(position).setBackgroundColor(Color.rgb(0, 128, 64)); //para verde

        try{
            Class<?> clase=Class.forName("ues.fia.eisi.reservalocalfia."+nombreValue); //cambiar por el paquete
            Intent inte = new Intent(this,clase);
            this.startActivity(inte);
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}


