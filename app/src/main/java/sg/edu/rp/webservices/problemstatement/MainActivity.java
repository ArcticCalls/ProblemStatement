package sg.edu.rp.webservices.problemstatement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btn1, btn2, btn3;
    Spinner spin;
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment) fm.findFragmentById(R.id.map);
        spin = findViewById(R.id.spinner);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);


        mapFragment.getMapAsync(new OnMapReadyCallback(){
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;
                LatLng Singapore = new LatLng(1.352083, 103.819836);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(Singapore,
                        15));


                int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION);

                if (permissionCheck == PermissionChecker.PERMISSION_GRANTED) {
                    map.setMyLocationEnabled(true);
                } else {
                    Log.e("GMap - Permission", "GPS access has not been granted");
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
                }

                UiSettings ui = map.getUiSettings();
                ui.setCompassEnabled(true);
                ui.setZoomControlsEnabled(true);

                LatLng poi_North = new LatLng(1.4413677852710576, 103.77224705786413);
                Marker north = map.addMarker(new
                        MarkerOptions()
                        .position(poi_North)
                        .title("Hq North")
                        .snippet("Block 333, Admiralty Ave 3, 765654 Operating hours: 10am-5pm\n" +
                                "Tel:65433456")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                LatLng poi_Central = new LatLng(1.2979403767034838 , 103.84751778671382);
                Marker central = map.addMarker(new
                        MarkerOptions()
                        .position(poi_Central)
                        .title("Hq Central")
                        .snippet("Block 3A, Orchard Ave 3, 134542 \n" +
                                "Operating hours: 11am-8pm\n" +
                                "Tel:67788652")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                LatLng poi_East = new LatLng(1.350051874296315 , 103.93441447123747);
                Marker East = map.addMarker(new
                        MarkerOptions()
                        .position(poi_East)
                        .title("Hq East")
                        .snippet("Block 555, Tampines Ave 3, 287788 \n" +
                                "Operating hours: 9am-5pm\n" +
                                "Tel:66776677\"\n")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        String markerTitle = marker.getTitle();
                        Toast.makeText(MainActivity.this, markerTitle, Toast.LENGTH_SHORT).show();
                        return false;
                    }
                });

            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LatLng poi_North = new LatLng(1.4413677852710576, 103.77224705786413);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_North,
                        15));
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LatLng poi_Central = new LatLng(1.2979403767034838 , 103.84751778671382);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_Central,
                        15));
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LatLng poi_East = new LatLng(1.350051874296315 , 103.93441447123747);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_East,
                        15));
            }
        });

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int mapswitch = spin.getSelectedItemPosition();
                switch (mapswitch){
                    case 0:
                        LatLng poi_North = new LatLng(1.4413677852710576, 103.77224705786413);
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_North,
                                15));
                        break;
                    case 1:
                        LatLng poi_Central = new LatLng(1.2979403767034838 , 103.84751778671382);
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_Central,
                                15));
                        break;
                    case 2:
                        LatLng poi_East = new LatLng(1.350051874296315 , 103.93441447123747);
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_East,
                                15));
                        break;
                    default:
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}