package usa.sesion15.dcjd.Vista;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import usa.sesion15.dcjd.Modelo.Adaptador;
import usa.sesion15.dcjd.Modelo.Entidad;
import usa.sesion15.dcjd.R;


public class Fragment_Productos extends Fragment {


    int [] imagen = {R.drawable.pr3, R.drawable.pr4, R.drawable.pr1};

    String TAG = "Fragment_Productos";

    View v;

    ListView listaProductos;
    Adaptador adaptador;

    TextView prueba;

    // CONEXION A LA BASE DE DATOS: instancia APEX de SQL en Oracle Cloud


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment__productos, container, false);
        //-----------------------------------------------------------------------------
        listaProductos = (ListView) v.findViewById(R.id.lista_productos);
        adaptador = new Adaptador(getTablaProductos(), getContext());

        listaProductos.setAdapter(adaptador);

        prueba = (TextView) v.findViewById(R.id.prueba);
        //-----------------------------------------------------------------------------
        return v;
    }

    private ArrayList<Entidad> getTablaProductos(){
        ArrayList<Entidad> listaProductos = new ArrayList<>();

        /* ================================================================================================== */
        String url = "https://g1f89b7fc4c2d84-db202112190003.adb.sa-saopaulo-1.oraclecloudapps.com/ords/admin/dcjd/productos";


        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //***********************************************************
                try {
                    JSONArray jsonArray = response.getJSONArray("items");
                    for(int i = 0; i < jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String imagen = jsonObject.getString("imagen");
                        String titulo = jsonObject.getString("titulo");
                        String descripcion = jsonObject.getString("descripcion");
                        listaProductos.add(new Entidad(imagen, titulo, descripcion));
                        prueba.setText(titulo + '\n');

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //***********************************************************
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(jsonObjectRequest);
        /* ================================================================================================== */

        return listaProductos;
    }

}