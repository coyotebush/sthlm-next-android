package com.saboonchi.sthlmnext;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.saboonchi.sthlmnext.provider.SampleData;

public class NearestListFragment extends ListFragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ListAdapter adapter = new SimpleCursorAdapter(getActivity(),
				R.layout.list_item_2_column, SampleData.sampleStations(),
				new String[] { "name", "type", "distance" }, new int[] {
						R.id.text_main, R.id.text_sub, R.id.text_right }, 0);
		setListAdapter(adapter);
	}

	@Override
	public void onListItemClick(ListView listView, View arg1, int position,
			long arg3) {
		Intent intent = new Intent(getActivity(), DestinationActivity.class);
		intent.putExtra("station", "Kista");
		startActivity(intent);
	}

}