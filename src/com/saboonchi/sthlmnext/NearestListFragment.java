package com.saboonchi.sthlmnext;

import android.content.Intent;
import android.database.MatrixCursor;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;
import com.saboonchi.sthlmnext.provider.ResRobotApi;

public class NearestListFragment extends ListFragment implements GooglePlayServicesClient.ConnectionCallbacks {

    protected LocationClient locationClient;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        locationClient = ((MainActivity) getActivity()).getLocationClient();
        locationClient.registerConnectionCallbacks(this);
    }

    protected class GetStationsTask extends AsyncTask<Location, Void, MatrixCursor> {
        @Override
        protected MatrixCursor doInBackground(Location... locations) {
            return ResRobotApi.findStationsNear(locations[0]);
        }

        @Override
        protected void onPostExecute(MatrixCursor cursor) {
            ListAdapter adapter = new SimpleCursorAdapter(getActivity(),
                    R.layout.list_item_2_column, cursor,
                    new String[] { "name", "_id", "distance" },
                    new int[] { R.id.text_main, R.id.text_sub, R.id.text_right }, 0);
            setListAdapter(adapter);
        }
    }

	@Override
	public void onListItemClick(ListView listView, View arg1, int position,
			long arg3) {
		
		Intent intent = new Intent(getActivity(), DestinationActivity.class);
		MatrixCursor cursor = (MatrixCursor) listView.getItemAtPosition(position);
		String str = cursor.getString(cursor.getColumnIndex("name"));
		str = "Kista"; // FIXME
		intent.putExtra("station", str);
		startActivity(intent);
	}

    @Override
    public void onConnected(Bundle arg0) {
        new GetStationsTask().execute(locationClient.getLastLocation());
        
    }

    @Override
    public void onDisconnected() {
        // TODO Auto-generated method stub
        
    }

}
