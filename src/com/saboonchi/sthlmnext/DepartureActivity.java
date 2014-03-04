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

import com.saboonchi.sthlmnext.R.menu;
import com.saboonchi.sthlmnext.database.FavoritesDBAdapter;
import com.saboonchi.sthlmnext.model.Destination;
import com.saboonchi.sthlmnext.provider.SampleData;

public class DepartureActivity extends ListActivity {
    protected Destination dest;
    protected MenuItem favoritesItem;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		Intent intent = getIntent();
		ListAdapter adapter = null;
		MatrixCursor matrixCursor = null;

		if (intent.getStringExtra("destination").equals("Akalla")) {

			matrixCursor = SampleData.sampleDeparture_Akalla();

		} else if (intent.getStringExtra("destination").equals("Brommaplan")) {

			matrixCursor = SampleData.sampleDeparture_Brommaplan();

		} else if (intent.getStringExtra("destination").equals("Danderyds sjukhus")) {

			matrixCursor = SampleData.sampleDeparture_Danderydssjukhus();

		} else if (intent.getStringExtra("destination").equals("Kungsträgatan")) {

			matrixCursor = SampleData.sampleDeparture_Kungsträgatan();

		} else if (intent.getStringExtra("destination").equals("Mörby Station")) {

			matrixCursor = SampleData.sampleDeparture_MörbyStation();

		} else if (intent.getStringExtra("destination").equals("Täby Centrum")) {

			matrixCursor = SampleData.sampleDeparture_TäbyCentrum();

		} else if (intent.getStringExtra("destination").equals("Järfella")) {

			matrixCursor = SampleData.sampleDeparture_Järfella();

		} else if (intent.getStringExtra("destination")
				.equals("Upplands väsby")) {

			matrixCursor = SampleData.sampleDeparture_Upplandsväsby();
		}

		if (matrixCursor != null) {

			adapter = new SimpleCursorAdapter(this,
					R.layout.list_item_2_column, matrixCursor, new String[] {
							"destination", "line", "time" }, new int[] {
							R.id.text_main, R.id.text_sub, R.id.text_right }, 0);

			setListAdapter(adapter);
		}

		dest = new Destination(1, "Slussen", "Hässelby strand", "T", 19);

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
	    favoritesItem.setIcon(isFavorite ? R.drawable.ic_action_important : R.drawable.ic_action_not_important);
	}

	@Override
	public void onListItemClick(ListView listView, View view, int position, long id) {
	    new DepartureNotificationDialogFragment().show(getFragmentManager(), "dialog");
	}

	protected class CheckFavoriteTask extends AsyncTask<Void, Void, Boolean> {
	    @Override
	    protected Boolean doInBackground(Void... params) {
	        return FavoritesDBAdapter.getInstance(DepartureActivity.this).isFavorite(dest);
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
	        FavoritesDBAdapter adapter = FavoritesDBAdapter.getInstance(DepartureActivity.this);
	        if (isFavorite) {
	            adapter.addFavorite(dest);
	        }
	        else {
	            adapter.removeFavorite(dest);
	        }
	        return null;
	    }
	}
}
