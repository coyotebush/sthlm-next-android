package com.saboonchi.sthlmnext;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.widget.SimpleAdapter;

import com.saboonchi.sthlmnext.provider.SampleData;

public class FavoritesFragment extends ListFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SimpleAdapter adapter = new SimpleAdapter(getActivity(),
                SampleData.sampleFavorites(), R.layout.list_item_2_column,
                new String[] { "name", "line", "time" },
                new int[] { R.id.text_main, R.id.text_sub, R.id.text_right });
        setListAdapter(adapter);
    }

}
