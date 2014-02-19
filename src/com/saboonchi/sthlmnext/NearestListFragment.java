package com.saboonchi.sthlmnext;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.saboonchi.sthlmnext.provider.SampleData;

public class NearestListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nearest_list, container, false);
        SimpleAdapter adapter = new SimpleAdapter(getActivity(),
                SampleData.sampleStations(), R.layout.list_item_2_column,
                new String[] { "name", "type", "distance" },
                new int[] { R.id.text_main, R.id.text_sub, R.id.text_right });
        ((ListView) view).setAdapter(adapter);
        return view;
    }

}
