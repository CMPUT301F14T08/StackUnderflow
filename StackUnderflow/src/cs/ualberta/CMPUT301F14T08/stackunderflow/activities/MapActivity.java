package cs.ualberta.CMPUT301F14T08.stackunderflow.activities;

import android.app.Activity;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

import cs.ualberta.CMPUT301F14T08.stackunderflow.R;

public class MapActivity extends Activity {
	
	private GoogleMap googleMap;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mapfragment);
	}
}
