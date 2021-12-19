package usa.sesion15.dcjd.Vista;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import org.osmdroid.config.Configuration;
import org.osmdroid.library.BuildConfig;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.util.ArrayList;

import usa.sesion15.dcjd.R;

public class Fragment_Sucursales extends Fragment {

        View v;


        private MapView myOpenMapView;
        private MapController myMapController;

        GeoPoint Bogota, Tunja, Medellin ;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            v = inflater.inflate(R.layout.fragment__sucursales, container, false);
            //-----------------------------------------------------------------------------

            myOpenMapView = (MapView) v.findViewById(R.id.openmapview);

            /* ---- necesitamos establecer el valor del agente de usuario en la configuración ------- */
            Configuration.getInstance().setUserAgentValue(BuildConfig.APPLICATION_ID);

            /*   punto de geolocalizacion de ejemplo */
            Bogota = new GeoPoint(4.6351, -74.0703);
            Tunja= new GeoPoint(5.548177614271547, -73.3533236170172);
            Medellin = new GeoPoint(6.2472703939725855, -75.56404881040109);

            myOpenMapView.setBuiltInZoomControls(true);

            myMapController = (MapController) myOpenMapView.getController();
            myMapController.setCenter(Bogota);
            myMapController.setZoom(6);

            myOpenMapView.setMultiTouchControls(true);

            /* -------------------------------------------------------------------------------------------------- */
            final MyLocationNewOverlay myLocationoverlay = new MyLocationNewOverlay(new GpsMyLocationProvider(getContext()), myOpenMapView);
            myOpenMapView.getOverlays().add(myLocationoverlay); //No añadir si no quieres una marca
            myLocationoverlay.enableMyLocation();

            myLocationoverlay.runOnFirstFix(new Runnable() {
                public void run() {
                    myMapController.animateTo(myLocationoverlay.getMyLocation());
                }
            });
            /* -------------------------------------------------------------------------------------------------- */

            /* MARCAS EN EL MAPA */

            ArrayList<OverlayItem> puntos = new ArrayList<OverlayItem>();
            puntos.add(new OverlayItem("Bogota", "Tienda DC Bogotá", Bogota));
            puntos.add(new OverlayItem("Medellin", "Tienda DC Medellin", Medellin));
            puntos.add(new OverlayItem("Tunja", "Tienda DC Tunja", Tunja));
            ItemizedIconOverlay.OnItemGestureListener<OverlayItem> tap = new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
                @Override
                public boolean onItemLongPress(int arg0, OverlayItem arg1) {
                    return false;
                }
                @Override
                public boolean onItemSingleTapUp(int index, OverlayItem item) {
                    return true;
                }
            };

            ItemizedOverlayWithFocus<OverlayItem> capa = new ItemizedOverlayWithFocus<OverlayItem>(getContext(), puntos, tap);
            capa.setFocusItemsOnTap(true);
            myOpenMapView.getOverlays().add(capa);



            //-----------------------------------------------------------------------------
            return v;
        }

    }