package com.example.steven.riddler;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends FragmentActivity implements GoogleMap.OnMarkerClickListener, OnMapReadyCallback {

    private GoogleMap mMap;

    private Marker mScienceWorld;
    private Marker mStanleyPark;
    private Marker mArtGallery;
    private Marker mUBC;


    private LatLng scienceWorld = new LatLng(49.2734, -123.1038);
    private LatLng beaverLake = new LatLng(49.304623, -123.139047);
    private LatLng ubc = new LatLng(49.263682, -123.246178);
    private LatLng artGallery = new LatLng(49.283185, -123.120482);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mScienceWorld = mMap.addMarker(new MarkerOptions().position(scienceWorld).title("Science World"));
        mScienceWorld.setTag("ScienceActivity");

        mStanleyPark = mMap.addMarker(new MarkerOptions().position(beaverLake).title("Stanley Park"));
        mStanleyPark.setTag("ParkActivity");

        mUBC = mMap.addMarker(new MarkerOptions().position(ubc).title("UBC"));
        mUBC.setTag("SchoolActivity");

        mArtGallery = mMap.addMarker(new MarkerOptions().position(artGallery).title("Vancouver Art Gallery"));
        mArtGallery.setTag("ArtActivity");

        mMap.setMinZoomPreference(10);
        mMap.animateCamera(CameraUpdateFactory.zoomTo(12));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(scienceWorld));

        mMap.setOnMarkerClickListener(this);


    }
    @Override
    public boolean onMarkerClick(final Marker marker) {
        String activityToStart = (String) marker.getTag();
        switch(activityToStart){
            case "ArtActivity":
                Intent intent = new Intent(this, ArtActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }


        return false;
    }


}
