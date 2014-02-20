package com.saboonchi.sthlmnext;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class DestinationActivity extends Activity {

	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_destination);
		//View view = inflater.inflate(R.layout.fragment_nearest_list, container, false);
        
		TextView view = (TextView) findViewById(R.id.);
		view.setText("XXXXXXXXXXXXXX");
		
		//View view = inflater.inflate(R.layout.fragment_nearest_list, container, false);
				/*this.getWindow().getDecorView().findViewById(android.R.id.content)
				or

				this.findViewById(android.R.id.content)
				or

				this.findViewById(android.R.id.content).getRootView() */
				
				//View view = this.findViewById(android.R.id.content);
		        
				//ListView listview = (ListView) findViewById(R.id.destination_list);
		       // ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, new String[] { "Kista","Husby", "Akallaaaa" });
				
		        //((ListView) view).setAdapter(adapter);

		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.destination, menu);
		return true;
	}

}
