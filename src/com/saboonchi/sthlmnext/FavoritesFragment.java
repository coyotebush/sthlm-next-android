package com.saboonchi.sthlmnext;

import android.content.Intent;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.saboonchi.sthlmnext.provider.SampleData;

public class FavoritesFragment extends ListFragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ListAdapter adapter = new SimpleCursorAdapter(getActivity(),
				R.layout.list_item_2_column, SampleData.sampleFavorites(),
				new String[] { "name", "line", "time" }, new int[] {
						R.id.text_main, R.id.text_sub, R.id.text_right }, 0);
		setListAdapter(adapter);

	}
	
	@Override
	public void onListItemClick(ListView listView, View arg1, int position,
			long arg3) {
		
		Intent intent = new Intent(getActivity(), DepartureActivity.class);
		MatrixCursor cursor = (MatrixCursor) listView.getItemAtPosition(position);
		String str = cursor.getString(cursor.getColumnIndex("destination"));
		intent.putExtra("destination", str);
		startActivity(intent);
	}


}
