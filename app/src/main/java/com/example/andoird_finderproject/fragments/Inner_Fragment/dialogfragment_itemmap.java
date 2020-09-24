package com.example.andoird_finderproject.fragments.Inner_Fragment;

import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.andoird_finderproject.R;
import com.example.andoird_finderproject.global.global;
import com.example.andoird_finderproject.models.item;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;


public class dialogfragment_itemmap extends DialogFragment implements OnMapReadyCallback {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getParentFragmentManager().setFragmentResultListener("item_withCoordinate", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String key, @NonNull Bundle bundle) {
                // We use a String here, but any type that can be put in a Bundle is supported
                final item result = (item) bundle.getSerializable("item");
                // Do something with the result...
                if (result != null) {
                    float Latitude = Float.parseFloat(result.getShopcoordinate().getLatitude());
                    float Longitude = Float.parseFloat(result.getShopcoordinate().getLongitude());
                    ShopName = result.getShopcoordinate().getMarker();
                    shopLocation = result.getShoplocation();
                    shopCoordinates = new LatLng(Latitude, Longitude);
                }
            }
        });
    }

    MapView mapView;
    GoogleMap mMap;
    Button btn_map_dismiss;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dialogitemmap, container, false);
        int width = getResources().getDimensionPixelSize(R.dimen.dialogFragmentWidth);
        int height = getResources().getDimensionPixelSize(R.dimen.dialogFragmentHeight);
        getDialog().getWindow().setLayout(width, height);

        mapView = (MapView) view.findViewById(R.id.item_map);
        btn_map_dismiss = view.findViewById(R.id.btn_dismiss_map);
        if (mapView != null) {
            mapView.onCreate(savedInstanceState);
            mapView.getMapAsync(this);
        }
        btn_map_dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    LatLng shopCoordinates;
    String ShopName;
    String shopLocation;

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom((shopCoordinates), 13));
        Marker shopMarker = mMap.addMarker(new MarkerOptions().position(shopCoordinates).title(ShopName).snippet(shopLocation));
        shopMarker.showInfoWindow(); //default shows the marker info
        mMap.getUiSettings().setZoomControlsEnabled(true);
        if (ContextCompat.checkSelfPermission(getContext(), ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(true);
        }
    }
}