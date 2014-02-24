package com.saboonchi.sthlmnext;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapFragment; 
import com.google.android.gms.maps.model.LatLng; 
import com.google.android.gms.maps.model.Marker; 
import com.google.android.gms.maps.model.MarkerOptions; 


import android.app.Fragment;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NearestMapFragment extends Fragment {

	GoogleMap map;
	
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
    	
    	View v = inflater.inflate(R.layout.fragment_nearest_map, container, false);
    	
    	// Check if GoogleMap is available
    	setUpMapIfNeeded();
    	
    	return v;
 
    }
    
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (map == null) {
            map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
            map.setMyLocationEnabled(true);
            //map.getMyLocation();
            // Check if we were successful in obtaining the map.
            if (map != null) {
                // The Map is verified. It is now safe to manipulate the map.
            	map.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
					
					@Override
					public void onMyLocationChange(Location arg0) {
						// TODO Auto-generated method stub
						
						LatLng latlong = new LatLng(arg0.getLatitude(),
				                arg0.getLongitude());
						
						
						map.moveCamera(CameraUpdateFactory.newLatLng(latlong));
				        map.animateCamera(CameraUpdateFactory.zoomTo(13));
						
						map.addMarker(new MarkerOptions().position(new LatLng(arg0.getLatitude(), arg0.getLongitude())).title("You are here")).showInfoWindow();
					}
				});
            }
        }
    }

}
