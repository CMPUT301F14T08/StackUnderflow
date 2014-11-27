package cs.ualberta.CMPUT301F14T08.stackunderflow.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import cs.ualberta.CMPUT301F14T08.stackunderflow.R;
/**
 * Shows then the map so users will be able to view the map. When they click on the map they will drop a pointer that will denote their location.
 * @author Cmput301 Winter 2014 Group 8
 */
public class MapActivity extends Activity {

	public GoogleMap googleMap = null;
	public Marker m = null;
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_fragment);

		googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		googleMap.setMyLocationEnabled(true);
		googleMap.getMyLocation();
		googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

			@Override
			public void onMapClick(LatLng position) {
				m=null;
				googleMap.clear();
				m = googleMap.addMarker(new MarkerOptions().position(position));
				Log.d("winrar", ""+m.getPosition());
			}
		});
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {//, MenuInflater inflater) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map_menu, menu);

		return true;
	}
	public boolean onOptionsItemSelected(MenuItem item) {

		int duration = Toast.LENGTH_LONG;
		CharSequence text = "";
		Toast toast = null;
		switch (item.getItemId()) {
		case R.id.my_location: 
			if(m==null){
				toast = Toast.makeText(this, "We cannot find your GPS location", duration);
				toast.show();
			}
			else{
				//TODO: PASS THE LOCATION OF THE USER INTO THE POST
				//to get the location of the user googleMap.getMyLocation().getLatitude() amd googleMap.getMyLocation().getLongitude()
				this.finish();

			}
			return true;

		case R.id.marker: 
			if(m==null){
				toast = Toast.makeText(this, "You need to set a marker", duration);
				toast.show();
			}
			else{
				//TODO: PASS LOCATION OF THE MARKER INTO THE POST
				//to get the location of the marker use m.getPosition()
				this.finish();

			}
			return true;

		default:				
			text = "Implement menu item";
			toast = Toast.makeText(this, text, duration);
			toast.show();			
			return false;
		} 
	}
}




