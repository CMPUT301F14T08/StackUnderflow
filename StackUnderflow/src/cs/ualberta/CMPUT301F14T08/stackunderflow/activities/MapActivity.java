package cs.ualberta.CMPUT301F14T08.stackunderflow.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import cs.ualberta.CMPUT301F14T08.stackunderflow.R;
/**
 * Shows then the map so users will be able to view the map. When they click on the map they will drop a pointer that will denote their location.
 * @author Cmput301 Winter 2014 Group 8
 */
public class MapActivity extends Activity {
	
	private GoogleMap googleMap;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mapfragment);
	}
    public void onMapClick(LatLng point) {

        MarkerOptions marker = new MarkerOptions().position(
                new LatLng(point.latitude, point.longitude)).title("New Marker");

        googleMap.addMarker(marker);
        Log.d("winrar",""+point.latitude+"---"+ point.longitude);
    System.out.println(point.latitude+"---"+ point.longitude);  
    }
}
