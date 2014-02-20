package com.saboonchi.sthlmnext;

import com.saboonchi.sthlmnext.provider.SampleData;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class DestinationActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.activity_destination);
		ListAdapter adapter = new SimpleCursorAdapter(this,
				R.layout.list_item_2_column, SampleData.sampleDestination(),
				new String[] {"destination", "line", "time" }, new int[] {
						R.id.text_main, R.id.text_sub, R.id.text_right }, 0);
		setListAdapter(adapter);
		// View view = inflater.inflate(R.layout.fragment_nearest_list,
		// container, false);

		// TextView view = (TextView) findViewById(R.id.);
		// view.setText("XXXXXXXXXXXXXX");

		// View view = inflater.inflate(R.layout.fragment_nearest_list,
		// container, false);
		/*
		 * this.getWindow().getDecorView().findViewById(android.R.id.content) or
		 * 
		 * this.findViewById(android.R.id.content) or
		 * 
		 * this.findViewById(android.R.id.content).getRootView()
		 */

		// View view = this.findViewById(android.R.id.content);

		// ListView listview = (ListView) findViewById(R.id.destination_list);
		// ArrayAdapter<String> adapter = new
		// ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, new
		// String[] { "Kista","Husby", "Akallaaaa" });

		// ((ListView) view).setAdapter(adapter);

	}
	
	@Override
	public void onListItemClick(ListView listView, View arg1, int position,
			long arg3) {
		Intent intent = new Intent(this, DepartureActivity.class);
		intent.putExtra("station", "Kista");
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.destination, menu);
		return true;
	}

}
