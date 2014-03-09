package com.saboonchi.sthlmnext;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class AboutActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
				
		TextView authors = (TextView) findViewById(R.id.authors);
		authors.setText("Authors:\nCorey Ford\nNiko Kangas\nNima Saboonchi");
		
	}
}
