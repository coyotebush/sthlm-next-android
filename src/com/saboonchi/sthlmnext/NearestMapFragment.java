package com.saboonchi.sthlmnext;



import java.util.HashMap;

import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.saboonchi.sthlmnext.provider.ResRobotApi;

public class NearestMapFragment extends Fragment {

	GoogleMap map;
	
	//private HashMap<Marker, HashMap<String,Integer>> outer_data;
    private HashMap<Marker, HashMap<String, String>> outer_data = new HashMap<Marker, HashMap<String,String>>();

	// This is just a controller variable for drawing the map
	private int i = 1;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
    	
    	View v = inflater.inflate(R.layout.fragment_nearest_map, container, false);

    	return v;
 
    }
    
    public void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        
    	if (map == null) {
            map = ((SupportMapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
            map.setMyLocationEnabled(true);
            	            
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
						        map.animateCamera(CameraUpdateFactory.zoomTo(15));
						        i=2;
						}
					}
				});
            }
        }
    	
    	map.setOnMarkerClickListener(new OnMarkerClickListener() {

			@Override
			public boolean onMarkerClick(Marker arg0) {
				// TODO Auto-generated method stub

				return false;
			}
        });
    	
    	map.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {
			
			@Override
			public void onInfoWindowClick(Marker arg0) {
				// TODO Auto-generated method stub
				if (arg0.isInfoWindowShown()) {
					HashMap<String,String> marker_data = new HashMap<String,String>();
					marker_data = outer_data.get(arg0);
					String id = marker_data.get("ID");
					Intent intent = new Intent(getActivity(), DestinationActivity.class);
					intent.putExtra("station", arg0.getTitle());
					intent.putExtra("StationID", id);
					startActivity(intent);

				}
			}
		});
    }
    
    public void drawMap(Cursor c) {
            // Here a cursor draws markers to the map
        map.clear();
            if (c!=null) {
	            if(c.moveToFirst()) {
	            	do {
	            		Marker marker = map.addMarker(new MarkerOptions()
		            		.position(new LatLng(c.getDouble(3),c.getDouble(2)))
		            		.title(c.getString(1))
		            		.alpha((float) 0.6)	
	            			);
            			HashMap<String, String> inner_data = new HashMap<String, String>();
            			inner_data.put("ID",c.getString(0));
            			outer_data.put(marker, inner_data);
	            	} while (c.moveToNext());
	        	}
            }
    }
    
    
    
}
