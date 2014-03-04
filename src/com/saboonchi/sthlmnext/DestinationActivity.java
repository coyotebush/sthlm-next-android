package com.saboonchi.sthlmnext;

import com.saboonchi.sthlmnext.provider.ResRobotApi;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.database.MatrixCursor;
import android.view.Menu;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class DestinationActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Intent intent = getIntent();
		setTitle(intent.getStringExtra("station"));
		String stationId = intent.getStringExtra("StationID");
		new GetStationsTask().execute(stationId);

	}

	private class GetStationsTask extends AsyncTask<String, Void, MatrixCursor> {
		@Override
		protected MatrixCursor doInBackground(String... stationId) {
			return ResRobotApi.getDestinationList(stationId[0]);
		}

		@Override
		protected void onPostExecute(MatrixCursor cursor) {
			ListAdapter adapter = new SimpleCursorAdapter(
					DestinationActivity.this,
					R.layout.list_item_2_column,
					cursor,
					new String[] { "direction", "number", "time" },
					new int[] { R.id.text_main, R.id.text_sub, R.id.text_right },
					0);
			setListAdapter(adapter);
		}
	}

	@Override
	public void onListItemClick(ListView listView, View arg1, int position,
			long arg3) {
		Intent intent = new Intent(this, DepartureActivity.class);
		MatrixCursor cursor = (MatrixCursor) listView
				.getItemAtPosition(position);
		String str = cursor.getString(cursor.getColumnIndex("direction"));
		intent.putExtra("destination", str);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.destination, menu);
		return true;
	}

}
