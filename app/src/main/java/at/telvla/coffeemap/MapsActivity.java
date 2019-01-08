package at.telvla.coffeemap;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback{

    private GoogleMap mMap;
    SupportMapFragment mapFrag;
    LocationRequest mLocationRequest;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    FusedLocationProviderClient mFusedLocationClient;
    Retrofit CallServer;
    API api;
    LinearLayout linbox;
    TextView Title;
    TextView Addres;
    TextView Phone;
    TextView Time_work;
    List<Info> list;
    ImageButton close;
    ImageButton current;
    ImageButton get_directions;
    String id_current;
    String title;
    Context context;
    Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        Title = (TextView) findViewById(R.id.title);
        Addres = (TextView) findViewById(R.id.addres);
        Phone = (TextView) findViewById(R.id.phone);
        Time_work = (TextView) findViewById(R.id.time_work);

        linbox = (LinearLayout) findViewById(R.id.linbox);
        linbox.setVisibility(View.GONE);

        close = (ImageButton) findViewById(R.id.close);
        current = (ImageButton) findViewById(R.id.current);
        get_directions = (ImageButton) findViewById(R.id.get_directions);

        context = this;
        mActivity = this;

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linbox.setVisibility(View.GONE);
            }
        });

        current.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                linbox.setVisibility(View.GONE);
                Intent intent_current = new Intent(MapsActivity.this, CurrentActivity.class);

                intent_current.putExtra("id_current", id_current);
                intent_current.putExtra("title", list.get(Integer.valueOf(id_current)).getName());
                intent_current.putExtra("address", list.get(Integer.valueOf(id_current)).getAddress());
                intent_current.putExtra("phone", list.get(Integer.valueOf(id_current)).getPhone());
                intent_current.putExtra("time_work", list.get(Integer.valueOf(id_current)).getTime_work());
                intent_current.putExtra("link_img1", list.get(Integer.valueOf(id_current)).getLink_img1());
                intent_current.putExtra("link_img2", list.get(Integer.valueOf(id_current)).getLink_img2());
                intent_current.putExtra("link_img3", list.get(Integer.valueOf(id_current)).getLink_img3());
                intent_current.putExtra("link_img4", list.get(Integer.valueOf(id_current)).getLink_img4());

                startActivity(intent_current);
            }
        });

        get_directions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        //stop location updates when Activity is no longer active
        if (mFusedLocationClient != null) {
            mFusedLocationClient.removeLocationUpdates(mLocationCallback);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(120000); // two minute interval
        mLocationRequest.setFastestInterval(120000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                id_current = marker.getId().substring(1);

                Title.setText(list.get(Integer.valueOf(id_current)).getName());
                Addres.setText(list.get(Integer.valueOf(id_current)).getAddress());
                Phone.setText(list.get(Integer.valueOf(id_current)).getPhone());
                Time_work.setText("10.00-21.00");
                linbox.setVisibility(View.VISIBLE);

                return false;
            }
        });

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                //Location Permission already granted
                mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
                mMap.setMyLocationEnabled(true);
            } else {
                //Request Location Permission
                checkLocationPermission();
            }
        }
        else {
            mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
            mMap.setMyLocationEnabled(true);
        }
    }

    LocationCallback mLocationCallback = new LocationCallback(){
        @Override
        public void onLocationResult(LocationResult locationResult) {
            for (Location location : locationResult.getLocations()) {
                Log.i("locat_test", "Location: " + location.getLatitude() + " " + location.getLongitude());
                mLastLocation = location;
                if (mCurrLocationMarker != null) {
                    mCurrLocationMarker.remove();
                }

                //Place current location marker
                /*LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.title("Current Position");
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
                mCurrLocationMarker = mMap.addMarker(markerOptions);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 11));*/

                List<LatLng> places = new ArrayList<>();
                CallServer = ApiClient.getClient();
                api = CallServer.create(API.class);

                Call<List<Info>> call = api.GetAllNewsJson(1);
                call.enqueue(new Callback<List<Info>>() {
                    @Override
                    public void onResponse(Call<List<Info>> call, Response<List<Info>> response) {


                        list = response.body();

                        for (int i = 0; i < list.size(); i++) {

                            LatLng sydney = new LatLng(list.get(i).getLongs(), list.get(i).getLats());
                            mMap.addMarker(new MarkerOptions().position(sydney).title(list.get(i).getName()));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 11));

                            new AgentAsyncTask(
                                    mActivity,
                                    list.get(i).getId(),
                                    list.get(i).getName(),
                                    list.get(i).getAddress(),
                                    list.get(i).getPhone(),
                                    list.get(i).getTime_work(),
                                    list.get(i).getLink_img1(),
                                    list.get(i).getLink_img2(),
                                    list.get(i).getLink_img3(),
                                    list.get(i).getLink_img4(),
                                    list.get(i).getLongs(),
                                    list.get(i).getLats()).execute();

                        }
                    }

                    @Override
                    public void onFailure(Call<List<Info>> call, Throwable t) {
                    }
                });
            }
        };

    };

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(this)
                        .setTitle("Location Permission Needed")
                        .setMessage("This app needs the Location permission, please accept to use location functionality")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(MapsActivity.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION );
                            }
                        })
                        .create()
                        .show();
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION );
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
                        mMap.setMyLocationEnabled(true);
                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}
