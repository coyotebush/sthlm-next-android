package com.saboonchi.sthlmnext;

import com.saboonchi.sthlmnext.provider.SampleData;

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
		ListAdapter adapter = null;

		if (intent.getStringExtra("station").equals("Kista")) {

			adapter = new SimpleCursorAdapter(this,
					R.layout.list_item_2_column,
					SampleData.sampleDestination_Kista(), new String[] {
							"destination", "line", "time" }, new int[] {
							R.id.text_main, R.id.text_sub, R.id.text_right }, 0);

		} else if (intent.getStringExtra("station").equals("Husby")) {
			adapter = new SimpleCursorAdapter(this,
					R.layout.list_item_2_column,
					SampleData.sampleDestination_Husby(), new String[] {
							"destination", "line", "time" }, new int[] {
							R.id.text_main, R.id.text_sub, R.id.text_right }, 0);
		
		}else if (intent.getStringExtra("station").equals("Helenelund")) {
			adapter = new SimpleCursorAdapter(this,
					R.layout.list_item_2_column,
					SampleData.sampleDestination_Helenelund(), new String[] {
							"destination", "line", "time" }, new int[] {
							R.id.text_main, R.id.text_sub, R.id.text_right }, 0);
		}

		if (adapter != null){
			setListAdapter(adapter);
		}else{
			intent = new Intent(this, MainActivity.class);
			//intent.putExtra("station", "Kista");
			startActivity(intent);
		}
			
	}

	@Override
	public void onListItemClick(ListView listView, View arg1, int position,
			long arg3) {
		Intent intent = new Intent(this, DepartureActivity.class);
		MatrixCursor cursor = (MatrixCursor) listView.getItemAtPosition(position);
		String str = cursor.getString(cursor.getColumnIndex("destination"));
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
