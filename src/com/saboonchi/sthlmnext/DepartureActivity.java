package com.saboonchi.sthlmnext;

import android.app.ListActivity;
import android.content.Intent;
import android.database.MatrixCursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.saboonchi.sthlmnext.database.FavoritesDBAdapter;
import com.saboonchi.sthlmnext.model.Destination;
import com.saboonchi.sthlmnext.provider.ResRobotApi;

public class DepartureActivity extends ListActivity {
	protected Destination dest;
	protected MenuItem favoritesItem;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		dest = new Destination("7412883", "Kista T-bana", "Akalla T-bana", "T",
				"11");

		Intent intent = getIntent();
		setTitle(intent.getStringExtra("station"));
		new GetStationsTask().execute(dest);

	}

	private class GetStationsTask extends
			AsyncTask<Destination, Void, MatrixCursor> {
		@Override
		protected MatrixCursor doInBackground(Destination... des) {
			return ResRobotApi.getDepartureList(des[0]);
		}

		@Override
		protected void onPostExecute(MatrixCursor cursor) {
			ListAdapter adapter = new SimpleCursorAdapter(
					DepartureActivity.this,
					R.layout.list_item_2_column,
					cursor,
					new String[] { "direction", "number", "time" },
					new int[] { R.id.text_main, R.id.text_sub, R.id.text_right },
					0);
			setListAdapter(adapter);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.departure, menu);
		favoritesItem = menu.findItem(R.id.action_favorite);
		new CheckFavoriteTask().execute();
		return true;
	}

	protected boolean isFavorite = false;

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_favorite:
			isFavorite = !isFavorite;
			updateFavoritesItem();
			new ToggleFavoriteTask().execute(isFavorite);
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	protected void updateFavoritesItem() {
		favoritesItem.setIcon(isFavorite ? R.drawable.ic_action_important
				: R.drawable.ic_action_not_important);
	}

	@Override
	public void onListItemClick(ListView listView, View view, int position,
			long id) {
		new DepartureNotificationDialogFragment().show(getFragmentManager(),
				"dialog");
	}

	protected class CheckFavoriteTask extends AsyncTask<Void, Void, Boolean> {
		@Override
		protected Boolean doInBackground(Void... params) {
			return FavoritesDBAdapter.getInstance(DepartureActivity.this)
					.isFavorite(dest);
		}

		@Override
		protected void onPostExecute(Boolean result) {
			isFavorite = result;
			updateFavoritesItem();
		}
	}

	protected class ToggleFavoriteTask extends AsyncTask<Boolean, Void, Void> {
		@Override
		protected Void doInBackground(Boolean... params) {
			boolean isFavorite = params[0];
			FavoritesDBAdapter adapter = FavoritesDBAdapter
					.getInstance(DepartureActivity.this);
			if (isFavorite) {
				adapter.addFavorite(dest);
			} else {
				adapter.removeFavorite(dest);
			}
			return null;
		}
	}
}
