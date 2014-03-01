package com.saboonchi.sthlmnext;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapFragment; 
import com.google.android.gms.maps.model.LatLng; 
import com.google.android.gms.maps.model.Marker; 
import com.google.android.gms.maps.model.MarkerOptions; 
import com.saboonchi.sthlmnext.provider.ResRobotApi;


import android.app.Fragment;
import android.database.MatrixCursor;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;

public class NearestMapFragment extends Fragment {

	GoogleMap map;
	
	// This is just a controller variable for drawing the map
	private int i = 1;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
    	
    	View v = inflater.inflate(R.layout.fragment_nearest_map, container, false);
    	    	
    	// Use MainActivity's LocationManager and fetch location
	    MainActivity ma = (MainActivity) getActivity();
	    Location location = ma.locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
	    new GetStationsTask().execute(location);
    	
    	// Check if GoogleMap is available
    	//setUpMapIfNeeded();
    	
    	return v;
 
    }
    
    private class GetStationsTask extends AsyncTask<Location, Void, MatrixCursor> {
        @Override
        protected MatrixCursor doInBackground(Location... locations) {
            return ResRobotApi.findStationsNear(locations[0]);
        }

        @Override
        protected void onPostExecute(MatrixCursor cursor) {
        	setUpMapIfNeeded(cursor);

        }
    }
    
    
    private void setUpMapIfNeeded(MatrixCursor list) {
        // Do a null check to confirm that we have not already instantiated the map.
        
    	if (map == null) {
            map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
            map.setMyLocationEnabled(true);
            	
            /**
             * 
             * Here a cursor adapter which draws markers to the map
             * 
             */
                        
            
            
            // Check if we were successful in obtaining the map.
            if (map != null) {
                // The Map is verified. It is now safe to manipulate the map.
            	map.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
					
					@Override
					public void onMyLocationChange(Location arg0) {
						// TODO Auto-generated method stub
						
						LatLng latlong = new LatLng(arg0.getLatitude(),
				                arg0.getLongitude());
						
						// Zoom to user's location only when map is opened for the first time
						if (i==1) {
								map.moveCamera(CameraUpdateFactory.newLatLng(latlong));
						        map.animateCamera(CameraUpdateFactory.zoomTo(13));
						        i=2;
						}
					}
				});
            }
        }
    }

}
