package eci.cosw.ecingnovation.myuniapp.ui.fragments;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import eci.cosw.ecingnovation.myuniapp.R;
import eci.cosw.ecingnovation.myuniapp.network.APIClient;
import eci.cosw.ecingnovation.myuniapp.network.model.InterestPoint;
import eci.cosw.ecingnovation.myuniapp.network.services.MapService;
import eci.cosw.ecingnovation.myuniapp.storage.Storage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapViewFragment extends Fragment implements OnMapReadyCallback{
    private MapView mapView;
    private GoogleMap gmap;

    private static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";

    public MapViewFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toolbar tb = view.findViewById(R.id.toolbar);

        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
        }

        mapView = view.findViewById(R.id.map_view);
        mapView.onCreate(mapViewBundle);
        mapView.getMapAsync(this);
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAP_VIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAP_VIEW_BUNDLE_KEY, mapViewBundle);
        }

        mapView.onSaveInstanceState(mapViewBundle);
    }
    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onPause() {
        mapView.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gmap = googleMap;
        gmap.setMinZoomPreference(12);
        LatLng escuela = new LatLng(4.7827524, -74.0427825);
        gmap.moveCamera(CameraUpdateFactory.newLatLng(escuela));
        gmap.animateCamera(CameraUpdateFactory.zoomTo(17.25f));
        addMarkers();
    }

    private void addMarkers() {
        Storage storage = new Storage(getContext());
        MapService mapsService = APIClient.getMapService(storage.getToken());
        Call<List<InterestPoint>> call = mapsService.getAllInterestPoints();
        call.enqueue(new Callback<List<InterestPoint>>() {
            @Override
            public void onResponse(Call<List<InterestPoint>> call, Response<List<InterestPoint>> response) {
                ArrayList<InterestPoint> listPoints = (ArrayList<InterestPoint>) response.body();
                addMarkersToMap(listPoints);
                if (!response.isSuccessful()) {
                    System.out.println("[ERROR] There was a an error at addMarkers() -> onResponse... ");
                }
            }

            @Override
            public void onFailure(Call<List<InterestPoint>> call, Throwable t) {
                System.out.println("onFailure: " + t.getMessage());
            }
        });
    }

    private void addMarkersToMap(ArrayList<InterestPoint> listPoints) {
        for (InterestPoint point : listPoints) {
            gmap.addMarker(new MarkerOptions()
                    .position(new LatLng(point.getLat(), point.getLng()))
                    .title(point.getTitle())
            );
        }
    }
}
