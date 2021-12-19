package usa.sesion15.dcjd.Contolador;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import usa.sesion15.dcjd.R;
import usa.sesion15.dcjd.Vista.Fragment_Favoritos;
import usa.sesion15.dcjd.Vista.Fragment_Inicio;
import usa.sesion15.dcjd.Vista.Fragment_Productos;
import usa.sesion15.dcjd.Vista.Fragment_Servicios;
import usa.sesion15.dcjd.Vista.Fragment_Sucursales;


public class MainActivity extends AppCompatActivity {

    Fragment subPantalla1, subPantalla2, subPantalla3, subPantallaInicio, subPantallaF;
    FragmentTransaction intercambio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        subPantalla1 = new Fragment_Productos();
        subPantalla2 = new Fragment_Servicios();
        subPantalla3 = new Fragment_Sucursales();
        subPantallaInicio = new Fragment_Inicio();
        subPantallaF = new Fragment_Favoritos();

        getSupportFragmentManager().beginTransaction().add(R.id.contenedor_fragments, subPantallaInicio).commit();

    }
    //****************************************** MENU DE OPCIONES ***************************************************
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menuopciones, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.productos){
            intercambio = getSupportFragmentManager().beginTransaction();
            intercambio.replace(R.id.contenedor_fragments, subPantalla1).commit();
            Toast.makeText(getApplicationContext(), "Productos", Toast.LENGTH_LONG ).show();
        }
        if (id == R.id.servicios){
            intercambio = getSupportFragmentManager().beginTransaction();
            intercambio.replace(R.id.contenedor_fragments, subPantalla2).commit();
            Toast.makeText(getApplicationContext(), "Servicios", Toast.LENGTH_LONG ).show();
        }
        if (id == R.id.sucursales){
            intercambio = getSupportFragmentManager().beginTransaction();
            intercambio.replace(R.id.contenedor_fragments, subPantalla3).commit();
            Toast.makeText(getApplicationContext(), "Sucursales", Toast.LENGTH_LONG ).show();
        }
        if (id == R.id.inicio){
            intercambio = getSupportFragmentManager().beginTransaction();
            intercambio.replace(R.id.contenedor_fragments, subPantallaInicio).commit();
            Toast.makeText(getApplicationContext(), "Inicio", Toast.LENGTH_LONG ).show();
        }
        if (id == R.id.favoritos){
            intercambio = getSupportFragmentManager().beginTransaction();
            intercambio.replace(R.id.contenedor_fragments, subPantallaF).commit();
            Toast.makeText(getApplicationContext(), "Favoritos", Toast.LENGTH_LONG ).show();
        }

        if (id == R.id.compartir) {
            Intent compartir = new Intent(android.content.Intent.ACTION_SEND);
            compartir.setType("text/plain");
            String mensaje = "Te recomiendo esta App.";
            compartir.putExtra(android.content.Intent.EXTRA_SUBJECT, "DC App");
            compartir.putExtra(android.content.Intent.EXTRA_TEXT, mensaje);
            startActivity(Intent.createChooser(compartir, "Compartir vía"));
            return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }
    //*****************************************************************************************************************
}