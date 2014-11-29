package cs.ualberta.CMPUT301F14T08.stackunderflow.activities;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import cs.ualberta.CMPUT301F14T08.stackunderflow.R;
import cs.ualberta.CMPUT301F14T08.stackunderflow.managers.LocManager;
/**
 * Shows then the map so users will be able to view the map. When they click on the map they will drop a pointer that will denote their location.
 * @author Cmput301 Winter 2014 Group 8
 */
public class MapActivity extends Activity {

	private GoogleMap mGoogleMap = null;
	private Marker mMarker = null;
	private Button mMarkerButton;
	private Button mGPSButton;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_fragment);
		final Activity activity = this;

		mGoogleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		mGoogleMap.setMyLocationEnabled(true);
		mGoogleMap.getMyLocation();
		mGoogleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

			@Override
			public void onMapClick(LatLng position) {
				mMarker=null;
				mGoogleMap.clear();
				mMarker = mGoogleMap.addMarker(new MarkerOptions().position(position));
				Log.d("winrar", ""+mMarker.getPosition());
			}
		});
		
		mMarkerButton = (Button) findViewById(R.id.map_fragment_marker_button);
		mMarkerButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(mMarker==null){
					errorToast("You need to set a marker");
				}
				else{
					double latitude = mMarker.getPosition().latitude;
					double longitude = mMarker.getPosition().longitude;
					if(LocManager.getLocationString(activity, new LatLng(latitude, longitude)) != null){
						Intent msg = new Intent();
		                msg.putExtra("latitude", latitude);
		                msg.putExtra("longitude", longitude);
		
		                setResult(Activity.RESULT_OK, msg);
						activity.finish();
					}
					else{
						errorToast("No location found at given marker point");
					}

				}
				
			}
		});
		
		mGPSButton = (Button) findViewById(R.id.map_fragment_gps_button);
		mGPSButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Location myLocation = mGoogleMap.getMyLocation();
				if(myLocation == null){
					errorToast("Cannot find your GPS location");
				}
				else{
					Intent msg = new Intent();
	                msg.putExtra("latitude", myLocation.getLatitude());
	                msg.putExtra("longitude", myLocation.getLongitude());

	                setResult(Activity.RESULT_OK, msg);
					activity.finish();

				}
			}
		});
		
	}
	
	public void errorToast(String errorText){
		Toast toast = Toast.makeText(this, errorText, Toast.LENGTH_LONG);
		toast.setGravity(Gravity.BOTTOM, 0, findViewById(R.id.map_fragment_buttons_layout).getHeight()+5);
		toast.show();
	}
}