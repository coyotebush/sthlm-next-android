package com.saboonchi.sthlmnext;

import com.saboonchi.sthlmnext.provider.SampleData;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.database.MatrixCursor;
import android.view.Menu;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;

public class DepartureActivity extends ListActivity {

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

		} else if (intent.getStringExtra("destination").equals("Kungstr�gatan")) {

			matrixCursor = SampleData.sampleDeparture_Kungstr�gatan();

		} else if (intent.getStringExtra("destination").equals("M�rby Station")) {

			matrixCursor = SampleData.sampleDeparture_M�rbyStation();

		} else if (intent.getStringExtra("destination").equals("T�by Centrum")) {

			matrixCursor = SampleData.sampleDeparture_T�byCentrum();

		} else if (intent.getStringExtra("destination").equals("J�rfella")) {

			matrixCursor = SampleData.sampleDeparture_J�rfella();

		} else if (intent.getStringExtra("destination")
				.equals("Upplands v�sby")) {

			matrixCursor = SampleData.sampleDeparture_Upplandsv�sby();
		}

		if (matrixCursor != null) {

			adapter = new SimpleCursorAdapter(this,
					R.layout.list_item_2_column, matrixCursor, new String[] {
							"destination", "line", "time" }, new int[] {
							R.id.text_main, R.id.text_sub, R.id.text_right }, 0);

			setListAdapter(adapter);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.departure, menu);
		return true;
	}

}
