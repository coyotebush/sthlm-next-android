package com.saboonchi.sthlmnext;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class NearestListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
    	
        View view = inflater.inflate(R.layout.fragment_nearest_list, container, false);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, new String[] { "Kista","Husby", "Akallaaaa" });
        ((ListView) view).setAdapter(adapter);
        ((ListView) view).setClickable(true);
        ((ListView) view).setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
            	Intent intent = new Intent(getActivity(), DestinationActivity.class);
            	intent.putExtra("station", "Kista");
                startActivity(intent);
            }
        });
        return view;
    }
    
    
}
