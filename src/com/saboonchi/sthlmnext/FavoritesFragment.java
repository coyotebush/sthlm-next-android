package com.saboonchi.sthlmnext;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

import com.saboonchi.sthlmnext.database.FavoritesDBAdapter;
import com.saboonchi.sthlmnext.model.Destination;
import com.saboonchi.sthlmnext.widget.FavoritesCursorAdapter;

public class FavoritesFragment extends ListFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new LoadFavoritesTask().execute();
    }

    protected class LoadFavoritesTask extends AsyncTask<Void, Void, Cursor> {

        @Override
        protected Cursor doInBackground(Void... params) {
            return FavoritesDBAdapter.getInstance(getActivity()).getAllFavorites();
        }

        @Override
        protected void onPostExecute(Cursor result) {
            setListAdapter(new FavoritesCursorAdapter(getActivity(), result));
        }
    }

    @Override
    public void onListItemClick(ListView listView, View arg1, int position, long arg3) {

        Intent intent = new Intent(getActivity(), DepartureActivity.class);
        Cursor cursor = (Cursor) listView.getItemAtPosition(position);
        Destination dest = new Destination(
                cursor.getString(cursor.getColumnIndex(FavoritesDBAdapter.COL_STATIONID)),
                cursor.getString(cursor.getColumnIndex(FavoritesDBAdapter.COL_STATIONNAME)),
                cursor.getString(cursor.getColumnIndex(FavoritesDBAdapter.COL_DESTINATIONNAME)),
                cursor.getString(cursor.getColumnIndex(FavoritesDBAdapter.COL_LINETYPE)),
                cursor.getString(cursor.getColumnIndex(FavoritesDBAdapter.COL_LINENUMBER)));
        intent.putExtra("destination", dest);
        startActivity(intent);
    }

}
