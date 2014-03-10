package com.saboonchi.sthlmnext;


import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class NearestListFragment extends ListFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
	}

    public void setCursor(Cursor cursor) {
            ListAdapter adapter = new SimpleCursorAdapter(getActivity(),
                    R.layout.list_item_2_column, cursor,
                    new String[] { "name", "types", "distance" },
                    new int[] { R.id.text_main, R.id.text_sub, R.id.text_right }, 0);
            setListAdapter(adapter);
    }

	@Override
	public void onListItemClick(ListView listView, View arg1, int position,
			long arg3) {
		
		Intent intent = new Intent(getActivity(), DestinationActivity.class);
		MatrixCursor cursor = (MatrixCursor) listView.getItemAtPosition(position);
		String str = cursor.getString(cursor.getColumnIndex("name"));
		String stationId = cursor.getString(cursor.getColumnIndex("_id"));
		intent.putExtra("station", str);
		intent.putExtra("StationID", stationId);
		startActivity(intent);
	}

}
