package com.saboonchi.sthlmnext;

import com.saboonchi.sthlmnext.provider.SampleData;

import android.os.Bundle;
import android.app.ListActivity;
import android.view.Menu;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;

public class DepartureActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		ListAdapter adapter = new SimpleCursorAdapter(this,
				R.layout.list_item_2_column, SampleData.sampleDeparture(),
				new String[] { "destination", "time" }, new int[] {
						R.id.text_main,  R.id.text_right }, 0);
		setListAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.departure, menu);
		return true;
	}

}
