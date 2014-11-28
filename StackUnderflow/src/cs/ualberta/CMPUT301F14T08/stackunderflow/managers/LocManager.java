package cs.ualberta.CMPUT301F14T08.stackunderflow.managers;

import java.io.IOException;
import java.util.List;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

public class LocManager{
	
	public static String getLocationString(Context context, LatLng location){
		if (location == null) return "none";
    	Geocoder geocoder = new Geocoder(context);
    	List<Address> addresses = null;
        try {
            /*
             * Return 1 address.
             */
            addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1);
        } catch (IOException e1) {
        Log.e("LocationSampleActivity",
                "IO Exception in getFromLocation()");
        e1.printStackTrace();
        return ("IO Exception trying to get address");
        } catch (IllegalArgumentException e2) {
        // Error message to post in the log
        String errorString = "Illegal arguments " +
                Double.toString(location.latitude) +
                " , " +
                Double.toString(location.longitude) +
                " passed to address service";
        Log.e("LocationSampleActivity", errorString);
        e2.printStackTrace();
        return errorString;
        }
        
        if (addresses != null && addresses.size() > 0) {
            // Get the first address
            Address address = addresses.get(0);
            /*
             * Format the first line of address (if available),
             * city, and country name.
             */
            String addressText = address.getLocality();
            return addressText;
        } else {
            return null;
        }
    }

}
